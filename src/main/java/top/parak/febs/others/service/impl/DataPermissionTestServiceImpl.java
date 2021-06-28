package top.parak.febs.others.service.impl;

import top.parak.febs.common.entity.QueryRequest;
import top.parak.febs.others.entity.DataPermissionTest;
import top.parak.febs.others.mapper.DataPermissionTestMapper;
import top.parak.febs.others.service.IDataPermissionTestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataPermissionTestServiceImpl extends ServiceImpl<DataPermissionTestMapper, DataPermissionTest> implements IDataPermissionTestService {

    @Override
    public IPage<DataPermissionTest> findDataPermissionTests(QueryRequest request, DataPermissionTest dataPermissionTest) {
        LambdaQueryWrapper<DataPermissionTest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(DataPermissionTest::getCreateTime);
        Page<DataPermissionTest> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }
}
