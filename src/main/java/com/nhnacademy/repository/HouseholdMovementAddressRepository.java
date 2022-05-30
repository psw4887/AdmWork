package com.nhnacademy.repository;

import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdMovementAddressRepository extends
        JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressPK> {

    List<HouseholdMovementAddress> getAllByHousehold(Household household);
}
