package com.nhnacademy.service;

import com.nhnacademy.domain.vo.FamilyRelationshipModifyRequest;
import com.nhnacademy.domain.vo.FamilyRelationshipRegisterRequest;

public interface FamilyRelationShipService {

    void FamilyRelationShipRegister(int sNum, FamilyRelationshipRegisterRequest RegisterRequest);

    void FamilyRelationShipModify(int sNum, int fNum, FamilyRelationshipModifyRequest ModifyRequest);

    void FamilyRelationShipDelete(int sNum, int fNum);

    String relationNameConverter(String relation);
}
