package top.parak.febs.generator.entity;

import lombok.Data;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Data
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
