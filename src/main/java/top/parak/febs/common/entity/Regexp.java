package top.parak.febs.common.entity;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description: 正则表达式
 */

public interface Regexp {

    /**
     * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
     */
    String MOBILE_REG = "[1]\\d{10}";

}
