package com.nhnacademy.service.impl;

import com.nhnacademy.domain.FamilyRelationshipModifyRequest;
import com.nhnacademy.domain.FamilyRelationshipRegisterRequest;
import com.nhnacademy.entity.FamilyRelationShip;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.FamilyNotFoundException;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.FamilyRelationShipRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.FamilyRelationShipService;
import org.springframework.stereotype.Service;

@Service("familyRelationShipService")
public class FamilyRelationShipServiceImpl implements FamilyRelationShipService {

    private final FamilyRelationShipRepository familyRelationShipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationShipServiceImpl(FamilyRelationShipRepository familyRelationShipRepository, ResidentRepository residentRepository) {
        this.familyRelationShipRepository = familyRelationShipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public void FamilyRelationShipRegister(int sNum, FamilyRelationshipRegisterRequest registerRequest) {
        FamilyRelationShip relationShip = new FamilyRelationShip();
        FamilyRelationShip.FamilyRelationShipPK pk = new FamilyRelationShip.FamilyRelationShipPK();

        Resident resident = residentRepository.findById(sNum).orElseThrow(
                ResidentNotFoundException::new);

        pk.setFamilyResidentSerialNumber(registerRequest.getFamilySerialNumber());
        pk.setBaseResidentSerialNumber(sNum);

        relationShip.setFamilyRelationShipPK(pk);
        relationShip.setResident(resident);
        relationShip.setFamilyRelationShipCode(registerRequest.getRelationShip());

        familyRelationShipRepository.save(relationShip);
    }

    @Override
    public void FamilyRelationShipModify(int sNum, int fNum, FamilyRelationshipModifyRequest modifyRequest) {
        FamilyRelationShip.FamilyRelationShipPK pk = new FamilyRelationShip.FamilyRelationShipPK();

        pk.setFamilyResidentSerialNumber(fNum);
        pk.setBaseResidentSerialNumber(sNum);

        FamilyRelationShip relationShip = familyRelationShipRepository.findById(pk).orElseThrow(
                FamilyNotFoundException::new);

        relationShip.setFamilyRelationShipCode(modifyRequest.getRelationShip());
    }

    @Override
    public void FamilyRelationShipDelete(int sNum, int fNum) {
        FamilyRelationShip.FamilyRelationShipPK pk = new FamilyRelationShip.FamilyRelationShipPK();

        pk.setFamilyResidentSerialNumber(fNum);
        pk.setBaseResidentSerialNumber(sNum);

        familyRelationShipRepository.deleteByFamilyRelationShipPK(pk);
    }
}
