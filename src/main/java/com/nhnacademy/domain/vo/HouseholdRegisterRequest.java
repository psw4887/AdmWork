package com.nhnacademy.domain.vo;

import lombok.Data;

@Data
public class HouseholdRegisterRequest {

    Integer houseHolder;

    String reason;

    String nowAddress;
}
