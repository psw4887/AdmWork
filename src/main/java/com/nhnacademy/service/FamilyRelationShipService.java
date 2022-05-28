package com.nhnacademy.service;

import com.nhnacademy.domain.FamilyRelationshipModifyRequest;
import com.nhnacademy.domain.FamilyRelationshipRegisterRequest;

public interface FamilyRelationShipService {

    void FamilyRelationShipRegister(int sNum, FamilyRelationshipRegisterRequest RegisterRequest);

    void FamilyRelationShipModify(int sNum, int fNum, FamilyRelationshipModifyRequest ModifyRequest);

    void FamilyRelationShipDelete(int sNum, int fNum);
}
