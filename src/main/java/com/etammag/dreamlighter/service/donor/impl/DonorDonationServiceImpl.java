package com.etammag.dreamlighter.service.donor.impl;

import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.dreamlighter.entity.donor.KidDonationDto;
import com.etammag.dreamlighter.entity.donor.KidThingDto;
import com.etammag.dreamlighter.entity.donor.ProjectDonationDto;
import com.etammag.dreamlighter.entity.donor.db.KidDonation;
import com.etammag.dreamlighter.entity.donor.db.KidThing;
import com.etammag.dreamlighter.entity.donor.db.ProjectDonation;
import com.etammag.dreamlighter.mapper.donor.DonationMapper;
import com.etammag.dreamlighter.mapper.donor.DonationStatisticMapper;
import com.etammag.dreamlighter.mapper.donor.mp.KidDonationMapper;
import com.etammag.dreamlighter.mapper.donor.mp.KidThingMapper;
import com.etammag.dreamlighter.mapper.donor.mp.ProjectDonationMapper;
import com.etammag.dreamlighter.service.donor.DonorDonationRankService;
import com.etammag.dreamlighter.service.donor.DonorDonationService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DonorDonationServiceImpl implements DonorDonationService {

    private final KidDonationMapper kidDonationMapper;
    private final KidThingMapper kidThingMapper;
    private final ProjectDonationMapper projectDonationMapper;

    private final DonationStatisticMapper donationStatisticMapper;
    private final DonationMapper donationMapper;

    private final DonorDonationRankService donorDonationRankService;

    @Autowired
    public DonorDonationServiceImpl(KidDonationMapper kidDonationMapper, KidThingMapper kidThingMapper, ProjectDonationMapper projectDonationMapper, DonationStatisticMapper donationStatisticMapper, DonationMapper donationMapper, DonorDonationRankService donorDonationRankService) {
        this.kidDonationMapper = kidDonationMapper;
        this.kidThingMapper = kidThingMapper;
        this.projectDonationMapper = projectDonationMapper;
        this.donationStatisticMapper = donationStatisticMapper;
        this.donationMapper = donationMapper;
        this.donorDonationRankService = donorDonationRankService;
    }

    @Override
    @Transactional
    public void addMoney(Long donorId, Long kidId, int amount) {
        kidDonationMapper.insert(new KidDonation(
                donorId,
                kidId,
                amount,
                LocalDateTime.now()));
        donorDonationRankService.update(donorId);
    }

    @Override
    @Transactional
    public void addThing(Long donorId, Long kidId, String name) {
        kidThingMapper.insert(new KidThing(
                donorId,
                kidId,
                name,
                LocalDateTime.now()));
    }

    @Override
    @Transactional
    public void addProject(Long donorId, Long projectId, int amount) {
        projectDonationMapper.insert(new ProjectDonation(
                projectId,
                donorId,
                amount,
                LocalDateTime.now()
        ));
        donorDonationRankService.update(donorId);
    }

    @Override
    public DonationStaDto getStatistic(Long donorId) {
        return donationStatisticMapper.selectSta(donorId);
    }

    @Override
    public IPageInfo<KidDonationDto> getMoney(IPage iPage, Long donorId) {
        return donationMapper.selectKidDonationP(iPage, donorId);
    }

    @Override
    public IPageInfo<KidThingDto> getThing(IPage iPage, Long donorId) {
        return donationMapper.selectKidThingP(iPage, donorId);
    }

    @Override
    public IPageInfo<ProjectDonationDto> getProject(IPage iPage, Long donorId) {
        return donationMapper.selectProjectDonationP(iPage, donorId);
    }

}
