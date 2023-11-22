package com.etammag.dreamlighter.service.volunteer.impl;

import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.mapper.kid.mp.KidMapper;
import com.etammag.dreamlighter.service.volunteer.VolunKidService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunKidServiceImpl implements VolunKidService {

    private final KidMapper kidMapper;

    @Autowired
    public VolunKidServiceImpl(KidMapper kidMapper) {
        this.kidMapper = kidMapper;
    }

    @Override
    public IPageInfo<Kid> pageAll(IPage iPage, Long volunId) {
        return kidMapper.selectByVolunIdP(iPage, volunId);
    }
}
