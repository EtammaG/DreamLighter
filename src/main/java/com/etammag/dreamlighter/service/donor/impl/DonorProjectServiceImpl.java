package com.etammag.dreamlighter.service.donor.impl;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.entity.donor.ProjectSimDto;
import com.etammag.dreamlighter.entity.donor.db.Project;
import com.etammag.dreamlighter.entity.donor.db.ProjectType;
import com.etammag.dreamlighter.mapper.donor.ProjectInfoMapper;
import com.etammag.dreamlighter.mapper.donor.mp.ProjectMapper;
import com.etammag.dreamlighter.mapper.donor.mp.ProjectTypeMapper;
import com.etammag.dreamlighter.service.donor.DonorProjectService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonorProjectServiceImpl implements DonorProjectService {

    private final String redisPrefix;
    private final String typeKey;

    private final ProjectTypeMapper projectTypeMapper;
    private final ProjectInfoMapper projectInfoMapper;
    private final ProjectMapper projectMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DonorProjectServiceImpl(
            @Value("${dream-lighter.redis.donor.project}") String redisPrefix,
            ProjectTypeMapper projectTypeMapper, ProjectInfoMapper projectInfoMapper, ProjectMapper projectMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.projectTypeMapper = projectTypeMapper;
        this.projectInfoMapper = projectInfoMapper;
        this.projectMapper = projectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.typeKey = redisPrefix + "type";
    }

    @Override
    public List<ProjectType> getAllType() {
        return JSON.parseArray(stringRedisTemplate.opsForValue().get(typeKey), ProjectType.class);
    }

    @Override
    public IPageInfo<ProjectSimDto> getSimByType(IPage iPage, Long typeId) {
        return projectInfoMapper.selectSimByTypeP(iPage, typeId);
    }

    @Override
    public IPageInfo<ProjectSimDto> getSim(IPage iPage) {
        return projectInfoMapper.selectSimP(iPage);
    }

    @Override
    public Project getById(String id) {
        return projectMapper.selectById(id);
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        // todo 使用缓存就不能在controller使用分页了
        updateCacheType();
    }

    private void updateCacheType() {
        List<ProjectType> types = projectTypeMapper.selectAll();
        stringRedisTemplate.opsForValue().set(typeKey, JSON.toJSONString(types));
    }

}
