package com.nhnacademy.repository;

import com.nhnacademy.entity.BirthDeathReportResident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BirthDeathReportResidentRepository extends
        JpaRepository<BirthDeathReportResident, BirthDeathReportResident.BirthDeathReportResidentPK> {

    @Query("select b from BirthDeathReportResident as b" +
        " where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '출생'")
    List<BirthDeathReportResident> isExistBirth(int sNum);

    @Query("select b from BirthDeathReportResident as b" +
        " where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '사망'")
    List<BirthDeathReportResident> isExistDeath(int sNum);

    @Query("select b from BirthDeathReportResident b " +
        "where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '출생'")
    BirthDeathReportResident findByBirthTargetResident(int sNum);

    @Query("select b from BirthDeathReportResident b " +
        "where b.birthDeathReportResidentPK.residentSerialNumber = ?1 and b.birthDeathReportResidentPK.birthDeathTypeCode = '사망'")
    BirthDeathReportResident findByDeathTargetResident(int sNum);

    @Transactional
    @Modifying
    @Query("delete from BirthDeathReportResident as b " +
        "where b.birthDeathReportResidentPK.residentSerialNumber = ?1 " +
        "or b.birthDeathReportResidentPK.reportResidentSerialNumber = ?1")
    void findForDeleteReport(int sNum);
}
