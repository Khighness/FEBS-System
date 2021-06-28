package top.parak.febs.monitor.controller;

import top.parak.febs.monitor.endpoint.FebsMetricsEndpoint;
import top.parak.febs.monitor.entity.JvmInfo;
import top.parak.febs.monitor.entity.ServerInfo;
import top.parak.febs.monitor.entity.TomcatInfo;
import top.parak.febs.monitor.helper.FebsActuatorHelper;
import top.parak.febs.common.entity.FebsConstant;
import top.parak.febs.common.utils.FebsUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Controller("monitorView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "monitor")
@RequiredArgsConstructor
public class ViewController {

    private final FebsActuatorHelper actuatorHelper;

    @GetMapping("online")
    @RequiresPermissions("online:view")
    public String online() {
        return FebsUtil.view("monitor/online");
    }

    @GetMapping("log")
    @RequiresPermissions("log:view")
    public String log() {
        return FebsUtil.view("monitor/log");
    }

    @GetMapping("loginlog")
    @RequiresPermissions("loginlog:view")
    public String loginLog() {
        return FebsUtil.view("monitor/loginLog");
    }

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    public String httptrace() {
        return FebsUtil.view("monitor/httpTrace");
    }

    @GetMapping("jvm")
    @RequiresPermissions("jvm:view")
    public String jvmInfo(Model model) {
        List<FebsMetricsEndpoint.FebsMetricResponse> jvm = actuatorHelper.getMetricResponseByType("jvm");
        JvmInfo jvmInfo = actuatorHelper.getJvmInfoFromMetricData(jvm);
        model.addAttribute("jvm", jvmInfo);
        return FebsUtil.view("monitor/jvmInfo");
    }

    @GetMapping("tomcat")
    @RequiresPermissions("tomcat:view")
    public String tomcatInfo(Model model) {
        List<FebsMetricsEndpoint.FebsMetricResponse> tomcat = actuatorHelper.getMetricResponseByType("tomcat");
        TomcatInfo tomcatInfo = actuatorHelper.getTomcatInfoFromMetricData(tomcat);
        model.addAttribute("tomcat", tomcatInfo);
        return FebsUtil.view("monitor/tomcatInfo");
    }

    @GetMapping("server")
    @RequiresPermissions("server:view")
    public String serverInfo(Model model) {
        List<FebsMetricsEndpoint.FebsMetricResponse> jdbcInfo = actuatorHelper.getMetricResponseByType("jdbc");
        List<FebsMetricsEndpoint.FebsMetricResponse> systemInfo = actuatorHelper.getMetricResponseByType("system");
        List<FebsMetricsEndpoint.FebsMetricResponse> processInfo = actuatorHelper.getMetricResponseByType("process");

        ServerInfo serverInfo = actuatorHelper.getServerInfoFromMetricData(jdbcInfo, systemInfo, processInfo);
        model.addAttribute("server", serverInfo);
        return FebsUtil.view("monitor/serverInfo");
    }

    @GetMapping("swagger")
    public String swagger() {
        return FebsUtil.view("monitor/swagger");
    }
}
