package top.parak.febs.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Slf4j
@Component
public class TestTask {

    public void test1(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }

    public void test2() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

}
