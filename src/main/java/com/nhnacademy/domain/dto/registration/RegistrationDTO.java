package com.nhnacademy.domain.dto.registration;

import com.nhnacademy.entity.HouseholdCompositionResident;
import com.nhnacademy.entity.HouseholdMovementAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    String cNum;

    LocalDate date;

    List<HouseholdCompositionResident> residents;

    List<HouseholdMovementAddress> addresses;
}
