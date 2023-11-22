package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

public interface VolunKidService {
    IPageInfo<Kid> pageAll(IPage iPage);
}
