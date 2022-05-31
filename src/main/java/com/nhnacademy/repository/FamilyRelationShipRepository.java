package com.nhnacademy.repository;

import com.nhnacademy.domain.dto.birth.family.FamilyCertFamilyDTO;
import com.nhnacademy.entity.FamilyRelationShip;
import com.nhnacademy.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FamilyRelationShipRepository extends
        JpaRepository<FamilyRelationShip, FamilyRelationShip.FamilyRelationShipPK> {

    @Transactional
    @Modifying
    @Query("delete from FamilyRelationShip as f " +
        "where f.familyRelationShipPK.baseResidentSerialNumber = ?1 " +
        "or f.familyRelationShipPK.familyResidentSerialNumber = ?1")
    void findForDeleteFamily(int sNum);

    @Query("select f.familyRelationShipPK.familyResidentSerialNumber from FamilyRelationShip as f" +
        " where f.familyRelationShipPK.baseResidentSerialNumber = ?1 " +
        "and f.familyRelationShipCode = '부' ")
    Integer findFatherByBaseSNum(int sNum);

    @Query("select f.familyRelationShipPK.familyResidentSerialNumber from FamilyRelationShip as f" +
        " where f.familyRelationShipPK.baseResidentSerialNumber = ?1 " +
        "and f.familyRelationShipCode = '모' ")
    Integer findMotherByBaseSNum(int sNum);

    List<FamilyCertFamilyDTO> getAllByResident(Resident resident);
}
