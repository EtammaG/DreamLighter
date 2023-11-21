package com.etammag.dreamlighter.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.icommon.exception.CustomException;
import com.etammag.icommon.entity.BaseInfo;
import com.etammag.dreamlighter.entity.user.IUserDetails;
import com.etammag.dreamlighter.entity.user.db.UserDonor;
import com.etammag.dreamlighter.mapper.user.mp.UserDonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("donorDetailsServiceImpl")
public class DonorDetailsServiceImpl implements UserDetailsService {

    private final UserDonorMapper userDonorMapper;

    @Autowired
    public DonorDetailsServiceImpl(UserDonorMapper userDonorMapper) {
        this.userDonorMapper = userDonorMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserDonor> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserDonor::getUsername, username);
        UserDonor user = userDonorMapper.selectOne(wrapper);
        if (user == null) throw new CustomException("账号或密码错误");
        return new IUserDetails(
                new BaseInfo(user.getDonorId(), 1),
                new ArrayList<String>(2) {{
                    add("DONOR");
                    add("LOGIN");
                }},
                user.getPassword(),
                user.getUsername(),
                true,
                true,
                true,
                true
        );
    }
}
