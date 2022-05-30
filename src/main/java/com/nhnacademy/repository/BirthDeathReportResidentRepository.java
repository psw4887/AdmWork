package com.nhnacademy.repository;

import com.nhnacademy.entity.BirthDeathReportResident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BirthDeathReportResidentRepository extends
        JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPK> {

    @Query("select b from BirthDeathReportResident as b" +
        " where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '출생'")
    List<BirthDeathReportResident> isExistBirth(int sNum);

    @Query("select b from BirthDeathReportResident as b" +
        " where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '사망'")
    List<BirthDeathReportResident> isExistDeath(int sNum);
}
