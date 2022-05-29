package com.nhnacademy.repository;

import com.nhnacademy.domain.ResidentDTO;
import com.nhnacademy.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    void deleteBySerialNumber(int sNum);
    Page<ResidentDTO> getAllBy(Pageable pageable);
}
