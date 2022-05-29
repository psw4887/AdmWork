package com.nhnacademy.repository;

import com.nhnacademy.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends
        JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressPK> {
}
