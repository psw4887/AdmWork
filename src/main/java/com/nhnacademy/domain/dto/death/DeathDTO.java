package com.nhnacademy.domain.dto.death;

import com.nhnacademy.entity.BirthDeathReportResident;
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

    String deathAddress;

    String issuerName;

    String issuerRegistration;

    String issuerRelation;

    String email;

    String phoneNum;

    public DeathDTO(Resident resident, BirthDeathReportResident issue, Resident issuer) {
        this.date = issue.getBirthDeathReportDate();
        this.deathName = resident.getName();
        this.deathRegistration = resident.getRegistrationNumber();
        this.deathDate = resident.getDeathDate();
        this.deathPlace = resident.getDeathPlaceCode();
        this.deathAddress = resident.getDeathPlaceAddress();
        this.issuerName = issuer.getName();
        this.issuerRegistration = issuer.getRegistrationNumber();
        this.issuerRelation = issue.getDeathReportQualificationsCode();
        this.email = issue.getEmail();
        this.phoneNum = issue.getPhoneNumber();
    }
}
