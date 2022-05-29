package com.nhnacademy.service;

import com.nhnacademy.domain.vo.MovementRequest;

import java.util.Date;

public interface HouseholdMovementAddressService {

    void movementRegister(int hNum, MovementRequest request);

    void movementModify(int hNum, Date date, MovementRequest request);

    void movementDelete(int hNum, Date date);
}
