package com.nhnacademy.service;

import com.nhnacademy.domain.HouseholdRegisterRequest;

public interface HouseholdService {

    void householdRegister(HouseholdRegisterRequest request);

    void householdDelete(int hNum);
}
