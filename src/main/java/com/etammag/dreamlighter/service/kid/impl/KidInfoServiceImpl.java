package com.etammag.dreamlighter.service.kid.impl;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.entity.donor.db.KidDonation;
import com.etammag.dreamlighter.entity.donor.db.KidThing;
import com.etammag.dreamlighter.entity.kid.*;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.entity.kid.db.School;
import com.etammag.dreamlighter.entity.kid.db.Type;
import com.etammag.dreamlighter.mapper.donor.mp.DonorMapper;
import com.etammag.dreamlighter.mapper.donor.mp.KidDonationMapper;
import com.etammag.dreamlighter.mapper.donor.mp.KidThingMapper;
import com.etammag.dreamlighter.mapper.kid.KidInfoMapper;
import com.etammag.dreamlighter.mapper.kid.mp.KidMapper;
import com.etammag.dreamlighter.mapper.kid.mp.SchoolMapper;
import com.etammag.dreamlighter.mapper.kid.mp.TypeMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.kid.KidInfoService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KidInfoServiceImpl implements KidInfoService {

    private final String redisPrefix;
    private final String typeKey;

    private final TypeMapper typeMapper;
    private final KidMapper kidMapper;
    private final SchoolMapper schoolMapper;
    private final KidInfoMapper kidInfoMapper;

    private final DonorMapper donorMapper;

    private final KidThingMapper kidThingMapper;
    private final KidDonationMapper kidDonationMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public KidInfoServiceImpl(
            @Value("${dream-lighter.redis.kid.i}") String redisPrefix,
            TypeMapper typeMapper, KidMapper kidMapper, SchoolMapper schoolMapper, KidInfoMapper kidInfoMapper, DonorMapper donorMapper, KidThingMapper kidThingMapper, KidDonationMapper kidDonationMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.typeMapper = typeMapper;
        this.kidMapper = kidMapper;
        this.schoolMapper = schoolMapper;
        this.kidInfoMapper = kidInfoMapper;
        this.donorMapper = donorMapper;
        this.kidThingMapper = kidThingMapper;
        this.kidDonationMapper = kidDonationMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.typeKey = redisPrefix + "type";
    }

    private KidRecDto toKidRecDto(Kid kid) {
        String award = null;
        Long donorId = null;
        KidDonation kidDonation = kidDonationMapper.selectOneByKidId(kid.getId());
        if (kidDonation == null) {
            KidThing kidThing = kidThingMapper.selectOneByKidId(kid.getId());
            if (kidThing != null) {
                award = kidThing.getThingName();
                donorId = kidThing.getDonorId();
            }
        } else {
            award = kidDonation.getAmount() + "元";
            donorId = kidDonation.getDonorId();
        }
        String recent = donorId == null
                ? "暂无"
                : kid.getName()
                + "小朋友通过自己的努力，获得了来自"
                + donorMapper.selectById(donorId).getNickname() + "的" + award + "捐助";
        return new KidRecDto(kid.getName(), kid.getPhoto(), recent);
    }

    @Override
    public List<KidRecDto> getRandomRecs() {
        return kidMapper.selectRandom(10).stream().map(this::toKidRecDto).collect(Collectors.toList());
    }

    @Override
    public List<Type> getAllType() {
        return JSON.parseArray(stringRedisTemplate.opsForValue().get(typeKey), Type.class);
    }

    @Override
    public KidDto getById(Long id) {
        Kid kid = kidMapper.selectById(id);
        School school = schoolMapper.selectById(kid.getSchoolId());
        KidDto kidDto = new KidDto();
        BeanUtils.copyProperties(kid, kidDto);
        kidDto.setSchool(school.getName());
        return kidDto;
    }

    @Override
    public IPageInfo<KidSimDto> getSimByType(IPage iPage, Long typeId) {
        return kidInfoMapper.selectSimByTypeIdP(iPage, typeId);
    }

    @Override
    public IPageInfo<KidSimDto> getSim(IPage iPage) {
        return kidInfoMapper.selectSimP(iPage);
    }

    @Override
    public KidMisDto getMis() {
        Kid kid = kidMapper.selectById(BaseInfoContext.get().getId());
        List<Map<String, Object>> maps = kidInfoMapper.selectMisInfo(kid.getId());
        int subMissionWaiting = 0;
        int subMissionDone = 0;
        int optMissionWaiting = 0;
        int optMissionDone = 0;
        for (Map<String, Object> map : maps) {
            Integer type = (Integer) map.get("type");
            Long replyId = (Long) map.get("reply_id");
            if (type == 0 && replyId == null) optMissionWaiting++;
            if (type == 1 && replyId == null) subMissionWaiting++;
            if (type == 0 && replyId != null) optMissionDone++;
            if (type == 1 && replyId != null) subMissionDone++;
        }
        return new KidMisDto(
                kid.getPhoto(),
                kid.getTotalPoint(),
                subMissionWaiting,
                subMissionDone,
                optMissionWaiting,
                optMissionDone
        );
    }

    @Override
    public KidMalDto getMal() {
        return kidInfoMapper.selectMalInfo(BaseInfoContext.get().getId());
    }

    @Override
    public KidMeeDto getMee() {
        Kid kid = kidMapper.selectById(BaseInfoContext.get().getId());
        KidMeeDto kidMeeDto = new KidMeeDto();
        BeanUtils.copyProperties(kid, kidMeeDto);
        Integer a = kidDonationMapper.selectTotalAmountByKidId(kid.getId());
        kidMeeDto.setObtainedMoney(a == null ? 0 : a);
        kidMeeDto.setRank(kidInfoMapper.selectRankById(kid.getId()));
        kidMeeDto.setWeekrank(kidInfoMapper.selectWeekrankById(kid.getId()));
        return kidMeeDto;
    }

    @Override
    public KidRecDto getRec() {
        return toKidRecDto(kidMapper.selectById(BaseInfoContext.get().getId()));
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        // 有关当前用户的所有信息是否应该存入BaseContext中，一个人的信息不会很多
        updateCacheType();
    }

    private void updateCacheType() {
        List<Type> types = typeMapper.selectAll();
        stringRedisTemplate.opsForValue().set(typeKey, JSON.toJSONString(types));
    }

}
