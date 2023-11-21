package com.etammag.dreamlighter;

import com.etammag.dreamlighter.mapper.donor.DonationMapper;
import com.etammag.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DreamLighterApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DonationMapper donationMapper;

    @Test
    void contextLoads() {
        IPage iPage = new IPage(1, 3, null);
        PageHelper.startPage(iPage);
        System.out.println(donationMapper.selectKidDonation(1L));
    }

}
