package top.parak.febs.others.mapper;

import top.parak.febs.common.annotation.DataPermission;
import top.parak.febs.others.entity.DataPermissionTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
