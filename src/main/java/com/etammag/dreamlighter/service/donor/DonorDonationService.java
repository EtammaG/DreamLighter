package com.etammag.dreamlighter.service.donor;

import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.dreamlighter.entity.donor.KidDonationDto;
import com.etammag.dreamlighter.entity.donor.KidThingDto;
import com.etammag.dreamlighter.entity.donor.ProjectDonationDto;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

public interface DonorDonationService {

    void addMoney(Long donorId, Long kidId, int amount);

    void addThing(Long donorId, Long kidId, String name);

    void addProject(Long donorId, Long projectId, int amount);

    DonationStaDto getStatistic(Long donorId);

    IPageInfo<KidDonationDto> getMoney(IPage iPage, Long donorId);

    IPageInfo<KidThingDto> getThing(IPage iPage, Long donorId);

    IPageInfo<ProjectDonationDto> getProject(IPage iPage, Long donorId);
}
