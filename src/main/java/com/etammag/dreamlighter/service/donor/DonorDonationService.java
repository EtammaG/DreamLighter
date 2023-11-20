package com.etammag.dreamlighter.service.donor;

import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.dreamlighter.entity.donor.KidDonationDto;
import com.etammag.dreamlighter.entity.donor.KidThingDto;
import com.etammag.dreamlighter.entity.donor.ProjectDonationDto;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

public interface DonorDonationService {

    void addMoney(Long kidId, int amount);

    void addThing(Long kidId, String name);

    void addProject(Long projectId, int amount);

    DonationStaDto getStatistic();

    IPageInfo<KidDonationDto> getMoney(IPage iPage);

    IPageInfo<KidThingDto> getThing(IPage iPage);

    IPageInfo<ProjectDonationDto> getProject(IPage iPage);
}
