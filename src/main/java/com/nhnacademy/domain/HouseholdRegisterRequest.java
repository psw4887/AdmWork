package com.nhnacademy.domain;

import lombok.Data;

@Data
public class HouseholdRegisterRequest {

    Integer houseHolder;

    String reason;

    String nowAddress;
}
