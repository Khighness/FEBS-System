package top.parak.febs.common.aspect;

import top.parak.febs.common.annotation.Limit;
import top.parak.febs.common.entity.LimitType;
import top.parak.febs.common.exception.LimitAccessException;
import top.parak.febs.common.utils.HttpContextUtil;
import top.parak.febs.common.utils.IpUtil;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LimitAspect extends BaseAspectSupport {

    private final RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(top.parak.febs.common.annotation.Limit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        Method method = resolveMethod(point);
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        String ip = IpUtil.getIpAddr(request);
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        switch (limitType) {
            case IP:
                key = ip;
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix() + "_", key, ip));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        log.info("IP:{} 第 {} 次访问key为 {}，描述为 [{}] 的接口", ip, count, keys, name);
        if (count != null && count.intValue() <= limitCount) {
            return point.proceed();
        } else {
            throw new LimitAccessException("接口访问超出频率限制");
        }
    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     * <p>KEYS[1] 访问键
     * <p>ARGV[1] 参数1 时间范围
     * <p>ARGV[2] 参数2 访问上限
     * <p>1. 获取key的访问次数
     * <p>2. 如果访问次数不为空且大于最大次数，那么直接返回访问次数
     * <p>3. 将当前key的访问次数自增一次
     * <p>4. 如果当前key的访问次数为1，说明是第一次访问，设置超时时间
     * <p>5. 返回key的访问次数
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
