package com.etammag.dreamlighter.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.dreamlighter.common.exception.CustomException;
import com.etammag.dreamlighter.entity.current.BaseInfo;
import com.etammag.dreamlighter.entity.user.IUserDetails;
import com.etammag.dreamlighter.entity.user.db.UserVolunteer;
import com.etammag.dreamlighter.mapper.user.mp.UserVolunteerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("volunteerDetailsServiceImpl")
public class VolunteerDetailsServiceImpl implements UserDetailsService {

    private final UserVolunteerMapper userVolunteerMapper;

    @Autowired
    public VolunteerDetailsServiceImpl(UserVolunteerMapper userVolunteerMapper) {
        this.userVolunteerMapper = userVolunteerMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserVolunteer> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserVolunteer::getUsername, username);
        UserVolunteer user = userVolunteerMapper.selectOne(wrapper);
        if(user == null) throw new CustomException("账号或密码错误");
        return new IUserDetails(
                new BaseInfo(user.getVolunteerId(), 3),
                new ArrayList<String>(2) {{
                    add("VOLUNTEER");
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
