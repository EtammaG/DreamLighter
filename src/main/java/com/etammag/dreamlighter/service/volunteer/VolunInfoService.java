package com.etammag.dreamlighter.service.volunteer;

import com.etammag.dreamlighter.entity.volunteer.VolunMisDto;
import com.etammag.dreamlighter.entity.volunteer.db.Volunteer;

public interface VolunInfoService {
    Volunteer getVolunteerInfo();

    VolunMisDto getVolunMisInfo();
}
