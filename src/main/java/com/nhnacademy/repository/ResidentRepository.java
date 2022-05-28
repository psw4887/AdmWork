package com.nhnacademy.repository;

import com.nhnacademy.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    void deleteBySerialNumber(int sNum);
}
