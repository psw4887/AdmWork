package com.nhnacademy.service;

import com.nhnacademy.domain.vo.MovementRequest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public interface HouseholdMovementAddressService {

    void movementRegister(int hNum, MovementRequest request);

    void movementModify(int hNum, LocalDate date, MovementRequest request);

    void movementDelete(int hNum, LocalDate date) throws ParseException;
}
