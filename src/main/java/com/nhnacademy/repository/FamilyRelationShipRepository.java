package com.nhnacademy.repository;

import com.nhnacademy.entity.FamilyRelationShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationShipRepository extends
        JpaRepository<FamilyRelationShip, FamilyRelationShip.FamilyRelationShipPK> {

    void deleteByFamilyRelationShipPK(FamilyRelationShip.FamilyRelationShipPK familyRelationShipPK);
}
