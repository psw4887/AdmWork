package com.nhnacademy.domain.dto.family;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamilyCertificateDTO {
    String cNum;

    LocalDate date;

    String address;

    List<FamilyCertResidentDTO> residents;
}
