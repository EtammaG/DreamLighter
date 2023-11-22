package com.etammag.dreamlighter.service.kid;


import com.etammag.dreamlighter.entity.kid.AwardDto;
import com.etammag.dreamlighter.entity.kid.AwardExchangeDto;
import com.etammag.dreamlighter.entity.kid.db.Award;
import com.etammag.dreamlighter.entity.kid.db.AwardType;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.List;

public interface AwardService {
    List<AwardType> getAllType();

    IPageInfo<AwardDto> search(IPage iPage, Long kidId, Long typeId, String name);

    void like(Long kidId, long awardId);

    IPageInfo<Award> getLike(IPage iPage, Long kidId);

    void exchange(Long kidId, long awardId);

    IPageInfo<AwardExchangeDto> getExchange(IPage iPage, Long kidId);

}
