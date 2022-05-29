package com.nhnacademy.repository;

import com.nhnacademy.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends
        JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPK> {
}
