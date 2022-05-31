package com.nhnacademy.domain.dto.birth.family;

import java.time.LocalDateTime;

public interface ResidentCertFamilyDTO {

    String getName();

    String getRegistrationNumber();

    String getGenderCode();

    LocalDateTime getBirthDate();
}
