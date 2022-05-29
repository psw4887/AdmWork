package com.nhnacademy.service;

import com.nhnacademy.domain.vo.HouseholdRegisterRequest;

public interface HouseholdService {

    void householdRegister(HouseholdRegisterRequest request);

    void householdDelete(int hNum);
}
