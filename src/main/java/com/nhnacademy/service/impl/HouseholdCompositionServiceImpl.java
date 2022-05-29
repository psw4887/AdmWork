package com.nhnacademy.service.impl;

import com.nhnacademy.domain.vo.HouseholdCompositionRegisterRequest;
import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.HouseholdCompositionResident;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.HouseHoldNotFoundException;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.repository.HouseholdRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.HouseholdCompositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service("householdCompositionService")
public class HouseholdCompositionServiceImpl implements HouseholdCompositionService {
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdCompositionServiceImpl(HouseholdCompositionResidentRepository householdCompositionResidentRepository, HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public void householdCompositionRegister(int sNum, int hNum, HouseholdCompositionRegisterRequest request) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        Household household = householdRepository.findById(hNum).orElseThrow(HouseHoldNotFoundException::new);
        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        HouseholdCompositionResident.HouseholdCompositionResidentPK pk = new HouseholdCompositionResident.HouseholdCompositionResidentPK();

        pk.setHouseholdSerialNumber(hNum);
        pk.setResidentSerialNumber(sNum);

        householdCompositionResident.setHousehold(household);
        householdCompositionResident.setResident(resident);
        householdCompositionResident.setReportDate(getTime());
        householdCompositionResident.setRelationshipCode(request.getRelationShip());
        householdCompositionResident.setCompositionChangeReasonCode(request.getReason());

        householdCompositionResidentRepository.save(householdCompositionResident);
    }

    @Transactional
    @Override
    public void householdCompositionDelete(int sNum, int hNum) {
        HouseholdCompositionResident.HouseholdCompositionResidentPK pk = new HouseholdCompositionResident.HouseholdCompositionResidentPK();
        pk.setHouseholdSerialNumber(hNum);
        pk.setResidentSerialNumber(sNum);

        householdCompositionResidentRepository.deleteById(pk);
    }

    private Timestamp getTime() {
        return new Timestamp(new Date().getTime());
    }
}
