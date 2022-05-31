package com.nhnacademy.domain.dto.birth;

import com.nhnacademy.entity.BirthDeathReportResident;
import com.nhnacademy.entity.Resident;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BirthDTO {
    LocalDate date;

    String birthName;

    String gender;

    LocalDateTime birthTime;

    String birthPlace;

    String address;

    Resident father;

    Resident mother;

    String issuerName;

    String issuerRegistration;

    String issuerRelation;

    String email;

    String phoneNum;

    public BirthDTO(Resident resident, BirthDeathReportResident issue, Resident father, Resident mother, Resident issuer) {
        this.date = issue.getBirthDeathReportDate();
        this.birthName = resident.getName();
        this.gender = resident.getGenderCode();
        this.birthTime = resident.getBirthDate();
        this.birthPlace = resident.getBirthPlaceCode();
        this.address = resident.getRegistrationBaseAddress();
        this.father = father;
        this.mother = mother;
        this.issuerName = issuer.getName();
        this.issuerRegistration = issuer.getRegistrationNumber();
        this.issuerRelation = issue.getBirthReportQualificationsCode();
        this.email = issue.getEmail();
        this.phoneNum = issue.getPhoneNumber();
    }
}
