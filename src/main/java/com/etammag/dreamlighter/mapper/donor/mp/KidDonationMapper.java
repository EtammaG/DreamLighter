package com.etammag.dreamlighter.mapper.donor.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.donor.db.KidDonation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KidDonationMapper extends BaseMapper<KidDonation> {

    KidDonation selectOneByKidId(Long kidId);

    Integer selectTotalAmount();

    Integer selectTotalAmountByKidId(Long kidId);
}
