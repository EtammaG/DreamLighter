package com.etammag.dreamlighter.service.donor;

import com.etammag.dreamlighter.entity.donor.ProjectSimDto;
import com.etammag.dreamlighter.entity.donor.db.Project;
import com.etammag.dreamlighter.entity.donor.db.ProjectType;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.List;

public interface DonorProjectService {
    List<ProjectType> getAllType();

    IPageInfo<ProjectSimDto> getSimByType(IPage iPage, Long typeId);

    IPageInfo<ProjectSimDto> getSim(IPage iPage);

    Project getById(String id);

}
