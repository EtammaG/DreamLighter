package com.etammag.dreamlighter.service.common.impl;

import com.etammag.dreamlighter.entity.common.DesDto;
import com.etammag.dreamlighter.mapper.donor.DonationStatisticMapper;
import com.etammag.dreamlighter.mapper.donor.mp.KidDonationMapper;
import com.etammag.dreamlighter.mapper.donor.mp.ProjectDonationMapper;
import com.etammag.dreamlighter.service.common.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotServiceImpl implements HotService {

    private final DonationStatisticMapper donationStatisticMapper;

    private final KidDonationMapper kidDonationMapper;
    private final ProjectDonationMapper projectDonationMapper;

    @Autowired
    public HotServiceImpl(DonationStatisticMapper donationStatisticMapper, KidDonationMapper kidDonationMapper, ProjectDonationMapper projectDonationMapper) {
        this.donationStatisticMapper = donationStatisticMapper;
        this.kidDonationMapper = kidDonationMapper;
        this.projectDonationMapper = projectDonationMapper;
    }

    @Override
    public DesDto getSysDes() {
        return new DesDto(
                kidDonationMapper.selectTotalAmount() + projectDonationMapper.selectTotalAmount(),
                donationStatisticMapper.selectNumOfKid()
        );
    }

}
