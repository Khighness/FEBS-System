package top.parak.febs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Data
@TableName("t_test")
public class Test implements Serializable {

    private static final long serialVersionUID = -2510534697977648681L;
    private String name;
    private int age;

}
