package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.kid.KidRecDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;

import java.util.List;

public interface VolunteerKidService {
    KidRecDto getKidRandomRec();

    List<Kid> getAllKidInfo();
}
