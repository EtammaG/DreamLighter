package com.etammag.dreamlighter.service.donor;


import com.etammag.dreamlighter.entity.donor.DonationStaDto;

import java.util.List;

public interface DonorDonationRankService {
    List<DonationStaDto> all();

    List<DonationStaDto> month();

    List<DonationStaDto> week();

    void update(Long donorId);
}
