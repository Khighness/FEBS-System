package top.parak.febs.generator.service.impl;

import top.parak.febs.common.entity.FebsConstant;
import top.parak.febs.common.entity.QueryRequest;
import top.parak.febs.common.utils.SortUtil;
import top.parak.febs.generator.entity.Table;
import top.parak.febs.generator.entity.Column;
import top.parak.febs.generator.mapper.GeneratorMapper;
import top.parak.febs.generator.service.IGeneratorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements IGeneratorService {

    private final GeneratorMapper generatorMapper;

    @Override
    public List<String> getDatabases(String databaseType) {
        return generatorMapper.getDatabases(databaseType);
    }

    @Override
    public IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName) {
        Page<Table> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return generatorMapper.getTables(page, tableName, databaseType, schemaName);
    }

    @Override
    public List<Column> getColumns(String databaseType, String schemaName, String tableName) {
        return generatorMapper.getColumns(databaseType, schemaName, tableName);
    }
}
