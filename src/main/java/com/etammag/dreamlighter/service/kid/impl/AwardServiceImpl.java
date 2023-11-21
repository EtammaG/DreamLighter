package com.etammag.dreamlighter.service.kid.impl;

import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.exception.CustomException;
import com.etammag.icommon.utils.redis.CacheUtil;
import com.etammag.dreamlighter.entity.kid.AwardDto;
import com.etammag.dreamlighter.entity.kid.AwardExchangeDto;
import com.etammag.dreamlighter.entity.kid.db.*;
import com.etammag.dreamlighter.mapper.kid.AwardInfoMapper;
import com.etammag.dreamlighter.mapper.kid.mp.AwardExchangeMapper;
import com.etammag.dreamlighter.mapper.kid.mp.AwardLikeMapper;
import com.etammag.dreamlighter.mapper.kid.mp.AwardTypeMapper;
import com.etammag.dreamlighter.mapper.kid.mp.KidMapper;
import com.etammag.dreamlighter.service.kid.AwardService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private final String redisPrefix;

    private final AwardTypeMapper awardTypeMapper;
    private final AwardLikeMapper awardLikeMapper;
    private final AwardExchangeMapper awardExchangeMapper;
    private final AwardInfoMapper awardInfoMapper;

    private final KidMapper kidMapper;

    private final StringRedisTemplate stringRedisTemplate;
    private final CacheUtil cacheUtil;

    @Autowired
    public AwardServiceImpl(
            @Value("${dream-lighter.redis.kid.award}") String redisPrefix,
            AwardTypeMapper awardTypeMapper, AwardLikeMapper awardLikeMapper, AwardExchangeMapper awardExchangeMapper, AwardInfoMapper awardInfoMapper, KidMapper kidMapper, StringRedisTemplate stringRedisTemplate, CacheUtil cacheUtil) {
        this.redisPrefix = redisPrefix;
        this.awardTypeMapper = awardTypeMapper;
        this.awardLikeMapper = awardLikeMapper;
        this.awardExchangeMapper = awardExchangeMapper;
        this.awardInfoMapper = awardInfoMapper;
        this.kidMapper = kidMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.cacheUtil = cacheUtil;
    }

    @Override
    public List<AwardType> getAllType() {
        String key = redisPrefix + "type";
        return cacheUtil.tryValueList(
                awardTypeMapper::selectAll,
                key,
                null,
                AwardType.class
        );
    }

    @Override
    public IPageInfo<AwardDto> search(IPage iPage, Long typeId, String name) {
        return awardInfoMapper.searchP(iPage, BaseInfoContext.get().getId(), name, typeId);
    }

    @Override
    public void like(long awardId) {
        AwardLike awardLike = new AwardLike(awardId, BaseInfoContext.get().getId());
        if (awardLikeMapper.cat(awardLike) != null) throw new CustomException("不能重复收藏");
        awardLikeMapper.insert(awardLike);
    }

    @Override
    public IPageInfo<Award> getLike(IPage iPage) {
        return awardInfoMapper.selectLikeP(iPage, BaseInfoContext.get().getId());
    }

    @Override
    public void exchange(long awardId) {
        Kid kid = kidMapper.selectById(BaseInfoContext.get().getId());
        awardExchangeMapper.insert(
                new AwardExchange(awardId, kid.getId(), kid.getAddress(), LocalDateTime.now()));
    }

    @Override
    public IPageInfo<AwardExchangeDto> getExchange(IPage iPage) {
        return awardInfoMapper.selectExchangesP(iPage, BaseInfoContext.get().getId());
    }

}
