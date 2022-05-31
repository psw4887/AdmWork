package com.nhnacademy.domain.dto.birth.family;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class FamilyCertResidentDTO {

    String name;

    String registrationNumber;

    String genderCode;

    LocalDateTime birthDate;

    String relation;
}
