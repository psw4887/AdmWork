package com.nhnacademy.repository;

import com.nhnacademy.entity.FamilyRelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyRelationShipRepository extends
        JpaRepository<FamilyRelationShip, FamilyRelationShip.FamilyRelationShipPK> {

    @Transactional
    @Modifying
    @Query("delete from FamilyRelationShip as f " +
        "where f.familyRelationShipPK.baseResidentSerialNumber = ?1 " +
        "or f.familyRelationShipPK.familyResidentSerialNumber = ?1")
    void findForDeleteFamily(int sNum);
}
