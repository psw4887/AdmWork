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
import org.springframework.transaction.annotation.Transactional;

@Service("familyRelationShipService")
public class FamilyRelationShipServiceImpl implements FamilyRelationShipService {

    private final FamilyRelationShipRepository familyRelationShipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationShipServiceImpl(FamilyRelationShipRepository familyRelationShipRepository, ResidentRepository residentRepository) {
        this.familyRelationShipRepository = familyRelationShipRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
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
        String relation = relationNameConverter(registerRequest.getRelationShip());
        relationShip.setFamilyRelationShipCode(relation);

        familyRelationShipRepository.save(relationShip);
    }

    @Transactional
    @Override
    public void FamilyRelationShipModify(int sNum, int fNum, FamilyRelationshipModifyRequest modifyRequest) {
        FamilyRelationShip.FamilyRelationShipPK pk = new FamilyRelationShip.FamilyRelationShipPK();

        pk.setFamilyResidentSerialNumber(fNum);
        pk.setBaseResidentSerialNumber(sNum);

        FamilyRelationShip relationShip = familyRelationShipRepository.findById(pk).orElseThrow(
                FamilyNotFoundException::new);
        String relation = relationNameConverter(modifyRequest.getRelationShip());
        relationShip.setFamilyRelationShipCode(relation);
    }

    @Transactional
    @Override
    public void FamilyRelationShipDelete(int sNum, int fNum) {
        FamilyRelationShip.FamilyRelationShipPK pk = new FamilyRelationShip.FamilyRelationShipPK();

        pk.setFamilyResidentSerialNumber(fNum);
        pk.setBaseResidentSerialNumber(sNum);

        familyRelationShipRepository.deleteByFamilyRelationShipPK(pk);
    }

    @Override
    public String relationNameConverter(String relation) {
        switch(relation) {
            case "father" :
                relation = "부";
                break;
            case "mother" :
                relation = "모";
                break;
            case "spouse" :
                relation = "배우자";
                break;
            case "child" :
                relation = "자녀";
                break;
            default:
                relation = "타인";
                break;
        }
        return relation;
    }
}
