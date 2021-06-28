package top.parak.febs.common.properties;

import lombok.Data;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Data
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
}
