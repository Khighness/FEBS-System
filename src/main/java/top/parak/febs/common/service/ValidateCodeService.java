package top.parak.febs.common.service;

import top.parak.febs.common.entity.ImageType;
import top.parak.febs.common.properties.FebsProperties;
import top.parak.febs.common.properties.ValidateCodeProperties;
import top.parak.febs.common.entity.FebsConstant;
import top.parak.febs.common.exception.FebsException;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Service
@RequiredArgsConstructor
public class ValidateCodeService {

    private final RedisService redisService;
    private final FebsProperties properties;

    /**
     * 在响应中写入验证码图片
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String key = session.getId();
        ValidateCodeProperties code = properties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set(FebsConstant.CODE_PREFIX  + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 检验验证码
     */
    public void check(String key, String value) throws FebsException {
        Object codeInRedis = redisService.get(FebsConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new FebsException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new FebsException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new FebsException("验证码不正确");
        }
    }

    /**
     * 根据验证码配置创建验证码
     */
    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha;
        if (StringUtils.equalsIgnoreCase(code.getType(), ImageType.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    /**
     * 设置返回数据头
     */
    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ImageType.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
