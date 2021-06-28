package top.parak.febs.monitor.configure;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Configuration
public class FebsMonitorConfigure {

    @Bean
    public HttpTraceRepository inMemoryHttpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}
