package com.etammag.dreamlighter.mapper.donor.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.donor.db.ProjectDonation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectDonationMapper extends BaseMapper<ProjectDonation> {

    Integer selectTotalAmount();
}
