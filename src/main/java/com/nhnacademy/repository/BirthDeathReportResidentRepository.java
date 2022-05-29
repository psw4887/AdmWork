package com.nhnacademy.repository;

import com.nhnacademy.domain.dto.BirthDTO;
import com.nhnacademy.domain.dto.DeathDTO;
import com.nhnacademy.entity.BirthDeathReportResident;
import com.nhnacademy.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BirthDeathReportResidentRepository extends
        JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPK> {

    @Query("select b.birthDeathReportResidentPK as birthCode, b.birthDeathReportDate as birthDate from BirthDeathReportResident as b where b.resident = ?1")
    List<BirthDTO> findBirthReportByResident(Resident resident);

    @Query("select b.birthDeathReportResidentPK as deathCode, b.birthDeathReportDate as deathDate from BirthDeathReportResident as b where b.resident = ?1")
    List<DeathDTO> findDeathReportByResident(Resident resident);
}
