package com.nhnacademy.repository;

import com.nhnacademy.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Household as h where h.resident.serialNumber = ?1")
    void findDeleteHouseHold(int sNum);
}
