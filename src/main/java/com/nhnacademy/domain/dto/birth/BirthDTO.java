package com.nhnacademy.domain.dto.birth;

import com.nhnacademy.entity.Resident;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BirthDTO {
    LocalDate date;

    String birthName;

    String gender;

    String birthPlace;

    String address;

    Resident father;

    Resident mother;

    String issuerName;

    String issuerRegistration;

    String issuerRelation;

    String email;

    String phoneNum;
}
