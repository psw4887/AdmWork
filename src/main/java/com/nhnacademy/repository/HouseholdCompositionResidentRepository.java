package com.nhnacademy.repository;

import com.nhnacademy.entity.HouseholdCompositionResident;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository extends
        JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPK>,
        HouseholdCompositionResidentRepositoryCustom {

    @Query("select h.resident.serialNumber from HouseholdCompositionResident as h " +
            "where h.resident.serialNumber = ?1 and " +
            "h.relationshipCode = ?2")
    Integer getHouseSerialNumberByResidentNumber(int sNum, String bon);

    @Override
    BooleanExpression checkHouseholderMember(int hNum);
}
