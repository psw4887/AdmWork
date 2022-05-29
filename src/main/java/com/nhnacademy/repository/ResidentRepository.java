package com.nhnacademy.repository;

import com.nhnacademy.domain.dto.ResidentDTO;
import com.nhnacademy.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Page<ResidentDTO> getAllBy(Pageable pageable);
}
