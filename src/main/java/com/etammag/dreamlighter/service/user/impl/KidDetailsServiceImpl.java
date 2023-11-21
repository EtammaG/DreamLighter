package com.etammag.dreamlighter.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.icommon.exception.CustomException;
import com.etammag.icommon.entity.BaseInfo;
import com.etammag.dreamlighter.entity.user.IUserDetails;
import com.etammag.dreamlighter.entity.user.db.UserKid;
import com.etammag.dreamlighter.mapper.user.mp.UserKidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("kidDetailsServiceImpl")
public class KidDetailsServiceImpl implements UserDetailsService {

    private final UserKidMapper userKidMapper;

    @Autowired
    public KidDetailsServiceImpl(UserKidMapper userKidMapper) {
        this.userKidMapper = userKidMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserKid> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserKid::getUsername, username);
        UserKid user = userKidMapper.selectOne(wrapper);
        if(user == null) throw new CustomException("账号或密码错误");
        return new IUserDetails(
                new BaseInfo(user.getKidId(), 2),
                new ArrayList<String>(2) {{
                    add("KID");
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
