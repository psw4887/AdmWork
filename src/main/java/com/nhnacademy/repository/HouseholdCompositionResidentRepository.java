package com.nhnacademy.repository;

import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.HouseholdCompositionResident;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdCompositionResidentRepository extends
        JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPK>,
        HouseholdCompositionResidentRepositoryCustom {

    @Query("select h.household.serialNumber from HouseholdCompositionResident as h " +
            "where h.resident.serialNumber = ?1 and " +
            "h.relationshipCode = ?2")
    Integer getHouseSerialNumberByResidentNumber(int sNum, String bon);

    @Query("select h.household.serialNumber from HouseholdCompositionResident as h " +
            "where h.resident.serialNumber = ?1")
    Integer getHouseSerialNumberByResidentNumber(int sNum);

    @Query("select h.resident.serialNumber from HouseholdCompositionResident as h " +
        "where h.household.serialNumber = ?1")
    List<Integer> getListByhNum(int hNum);

    @Override
    BooleanExpression checkHouseholderMember(int hNum);

    List<HouseholdCompositionResident> getAllByHousehold(Household household);

    @Transactional
    @Modifying
    @Query("delete from HouseholdCompositionResident as h where h.resident.serialNumber = ?1")
    void goodByeHouseMember(int sNum);
}
