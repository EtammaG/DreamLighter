package com.etammag.dreamlighter.entity.common;

import com.etammag.pagehelper.IPage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ITypePage extends IPage {
    private Long typeId;

    public ITypePage(Integer pageNum, Integer pageSize, String orderBy, Long typeId) {
        super(pageNum, pageSize, orderBy);
        this.typeId = typeId;
    }
}
