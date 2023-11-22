package com.etammag.dreamlighter;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.mapper.donor.DonationMapper;
import com.etammag.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        Kid kid = new Kid();
        kid.setName("a");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        Kid kid1 = JSON.parseObject(JSON.toJSONString(map), Kid.class);
        BeanUtils.copyProperties(kid, kid1);
        System.out.println();
    }

}
