package com.etammag.dreamlighter.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.mapper.kid.mp.KidMapper;
import com.etammag.dreamlighter.mapper.volunteer.VolunteerStatisticMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.VolunToKidMapper;
import com.etammag.dreamlighter.common.BaseInfoContext;
import com.etammag.dreamlighter.service.volunteer.VolunteerKidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VolunteerKidServiceImpl implements VolunteerKidService {
    private final VolunToKidMapper volunToKidMapper;
    private final VolunteerStatisticMapper volunteerStatisticMapper;
    private final KidMapper kidMapper;

    @Autowired
    public VolunteerKidServiceImpl(VolunToKidMapper volunToKidMapper, VolunteerStatisticMapper volunteerStatisticMapper, KidMapper kidMapper) {
        this.volunToKidMapper = volunToKidMapper;
        this.volunteerStatisticMapper = volunteerStatisticMapper;
        this.kidMapper = kidMapper;
    }

    @Override
    public KidRecDto getKidRandomRec() {
        KidRecDto kidRecDto = new KidRecDto();
        Long volunId = BaseInfoContext.get().getId();
        List<Kid> kids = volunteerStatisticMapper.volunKid(volunId);
        List<Long> kidIds = kids.stream().map(Kid::getId).collect(Collectors.toList());
        List<Map<String, Object>> newDonation = volunteerStatisticMapper.newDonation(kidIds);
        Long kidIdMoney = (Long) newDonation.get(0).get("kid_id");
        Timestamp timeMoney = (Timestamp)newDonation.get(0).get("time");

        List<Map<String, Object>> newThingDonation = volunteerStatisticMapper.newThingDonation(kidIds);
        Long kidIdThing = (Long) newThingDonation.get(0).get("kid_id");
        Timestamp timeThing = (Timestamp)newThingDonation.get(0).get("time");

        LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
        if (timeMoney.after(timeThing)){
            kidQuery.eq(Kid::getId,kidIdMoney);
            Kid kid = kidMapper.selectOne(kidQuery);
            kidRecDto.setName(kid.getName());
            kidRecDto.setPhoto(kid.getPhoto());
            kidRecDto.setRecent(kid.getName()+"在通过了自己的努力后，于"+timeMoney
                    +"获得了"
                    +newDonation.get(0).get("nickname")
                    +"的现金资助："
                    +newDonation.get(0).get("amount")+"元");
        }else {
            kidQuery.eq(Kid::getId,kidIdThing);
            Kid kid = kidMapper.selectOne(kidQuery);
            kidRecDto.setName(kid.getName());
            kidRecDto.setPhoto(kid.getPhoto());
            kidRecDto.setRecent(kid.getName()
                    +"在通过了自己的努力后，于"
                    +timeThing
                    +"获得了"
                    +newThingDonation.get(0).get("nickname")
                    +"的物品资助:"
                    +newThingDonation.get(0).get("thing_name"));
        }
        return kidRecDto;
    }

    @Override
    public List<Kid> getAllKidInfo() {
        return volunteerStatisticMapper.volunKid(BaseInfoContext.get().getId());
    }
}
