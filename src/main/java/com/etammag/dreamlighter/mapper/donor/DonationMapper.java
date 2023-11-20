package com.etammag.dreamlighter.mapper.donor;

import com.etammag.dreamlighter.entity.donor.KidDonationDto;
import com.etammag.dreamlighter.entity.donor.KidThingDto;
import com.etammag.dreamlighter.entity.donor.ProjectDonationDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Page
public interface DonationMapper {
    List<KidDonationDto> selectKidDonation(Long donorId);

    List<KidThingDto> selectKidThing(Long donorId);

    List<ProjectDonationDto> selectProjectDonation(Long donorId);
}
