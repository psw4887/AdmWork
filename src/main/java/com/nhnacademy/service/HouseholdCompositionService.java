package com.nhnacademy.service;

import com.nhnacademy.domain.HouseholdCompositionRegisterRequest;

public interface HouseholdCompositionService {

    void householdCompositionRegister(int sNum, int hNum, HouseholdCompositionRegisterRequest request);

    void householdCompositionDelete(int sNum, int hNum);
}
