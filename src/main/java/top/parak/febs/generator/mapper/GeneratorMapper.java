package top.parak.febs.generator.mapper;


import top.parak.febs.generator.entity.Column;
import top.parak.febs.generator.entity.Table;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public interface GeneratorMapper {

    List<String> getDatabases(@Param("databaseType") String databaseType);

    <T> IPage<Table> getTables(Page<T> page, @Param("tableName") String tableName, @Param("databaseType") String databaseType, @Param("schemaName") String schemaName);

    List<Column> getColumns(@Param("databaseType") String databaseType, @Param("schemaName") String schemaName, @Param("tableName") String tableName);
}
