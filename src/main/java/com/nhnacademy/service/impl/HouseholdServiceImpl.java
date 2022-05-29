package com.nhnacademy.service.impl;

import com.nhnacademy.domain.HouseholdRegisterRequest;
import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.HouseholdRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.HouseholdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service("householdService")
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public void householdRegister(HouseholdRegisterRequest request) {
        Household household = new Household();

        Resident resident = residentRepository.findById(request.getHouseHolder()).orElseThrow(ResidentNotFoundException::new);

        household.setResident(resident);
        household.setSerialNumber(request.getHouseHolder());
        household.setCompositionDate(getTime());
        household.setCompositionReasonCode(request.getReason());
        household.setCurrentHouseMovementAddress(request.getNowAddress());

        householdRepository.save(household);
    }

    @Transactional
    @Override
    public void householdDelete(int hNum) {
        householdRepository.deleteById(hNum);
    }

    private Timestamp getTime() {
        return new Timestamp(new Date().getTime());
    }
}
