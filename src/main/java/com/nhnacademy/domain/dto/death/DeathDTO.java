package com.nhnacademy.domain.dto.death;

import com.nhnacademy.entity.Resident;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeathDTO {
    LocalDate date;

    String deathName;

    String deathRegistration;

    LocalDateTime deathDate;

    String deathPlace;

    Resident deathAddress;

    String issuerName;

    String issuerRegistration;

    String issuerRelation;

    String email;

    String phoneNum;
}
