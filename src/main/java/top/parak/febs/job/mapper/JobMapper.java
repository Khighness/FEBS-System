package top.parak.febs.job.mapper;

import top.parak.febs.job.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public interface JobMapper extends BaseMapper<Job> {

    /**
     * 获取定时任务列表
     *
     * @return 定时任务列表
     */
    List<Job> queryList();
}
