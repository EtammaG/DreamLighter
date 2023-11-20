package com.etammag.dreamlighter.service.kid;


import com.etammag.dreamlighter.entity.kid.*;
import com.etammag.dreamlighter.entity.kid.db.Type;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.List;

public interface KidInfoService {

    List<KidRecDto> getRandomRecs();

    List<Type> getAllType();

    KidDto getById(Long id);

    IPageInfo<KidSimDto> getSimByType(IPage iPage, Long typeId);

    IPageInfo<KidSimDto> getSim(IPage iPage);

    KidMisDto getMis();

    KidMalDto getMal();

    KidMeeDto getMee();

    KidRecDto getRec();

}
