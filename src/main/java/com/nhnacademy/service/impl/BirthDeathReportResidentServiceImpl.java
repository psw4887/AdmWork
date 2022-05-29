package com.nhnacademy.service.impl;

import com.nhnacademy.domain.dto.BirthDTO;
import com.nhnacademy.domain.vo.BirthRequest;
import com.nhnacademy.domain.dto.DeathDTO;
import com.nhnacademy.domain.vo.DeathRequest;
import com.nhnacademy.entity.BirthDeathReportResident;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ReportNotFoundException;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.BirthDeathReportResidentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("birthDeathReportResidentService")
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public List<BirthDTO> getBirthReport(int sNum) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        return birthDeathReportResidentRepository.findBirthReportByResident(resident);
    }

    @Override
    public List<DeathDTO> getDeathReport(int sNum) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        return birthDeathReportResidentRepository.findDeathReportByResident(resident);
    }

    @Transactional
    @Override
    public void birthRegister(int sNum, BirthRequest request) {
        BirthDeathReportResident report = new BirthDeathReportResident();
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident.BirthDeathReportResidentPK pk = new BirthDeathReportResident.BirthDeathReportResidentPK();
        pk.setBirthDeathTypeCode("출생");
        pk.setReportResidentSerialNumber(request.getReportResident());
        pk.setResidentSerialNumber(sNum);

        report.setBirthDeathReportResidentPK(pk);
        report.setResident(resident);
        report.setBirthDeathReportDate(getTime());
        report.setBirthReportQualificationsCode(request.getRelationship());
        report.setEmail(request.getEmail());
        report.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void birthModify(int sNum, int tNum, BirthRequest request) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(tNum, sNum);
        pk.setBirthDeathTypeCode("출생");

        BirthDeathReportResident report = getReport(pk);

        report.setBirthDeathReportDate(getTime());
        report.setBirthReportQualificationsCode(request.getRelationship());
        report.setEmail(request.getEmail());
        report.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void birthDelete(int sNum, int tNum) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(tNum, sNum);
        pk.setBirthDeathTypeCode("출생");

        birthDeathReportResidentRepository.deleteById(pk);
    }

    @Transactional
    @Override
    public void deathRegister(int sNum, DeathRequest request) {
        BirthDeathReportResident report = new BirthDeathReportResident();
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident.BirthDeathReportResidentPK pk = new BirthDeathReportResident.BirthDeathReportResidentPK();
        pk.setBirthDeathTypeCode("사망");
        pk.setReportResidentSerialNumber(request.getReportResident());
        pk.setResidentSerialNumber(sNum);

        report.setBirthDeathReportResidentPK(pk);
        report.setResident(resident);
        report.setBirthDeathReportDate(getTime());
        report.setBirthReportQualificationsCode(request.getRelationship());
        report.setEmail(request.getEmail());
        report.setPhoneNumber(request.getPhoneNumber());
    }

    @Transactional
    @Override
    public void deathModify(int sNum, int tNum, DeathRequest request) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(sNum, tNum);
        pk.setBirthDeathTypeCode("사망");

        BirthDeathReportResident report = getReport(pk);

        report.setBirthDeathReportDate(getTime());
        report.setBirthReportQualificationsCode(request.getRelationship());
        report.setEmail(request.getEmail());
        report.setPhoneNumber(request.getPhoneNumber());

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void deathDelete(int sNum, int tNum) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(tNum, sNum);
        pk.setBirthDeathTypeCode("사망");

        birthDeathReportResidentRepository.deleteById(pk);
    }

    private Timestamp getTime() {
        return new Timestamp(new Date().getTime());
    }

    private BirthDeathReportResident.BirthDeathReportResidentPK getPK(int tNum, int sNum) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk =
                new BirthDeathReportResident.BirthDeathReportResidentPK();
        pk.setReportResidentSerialNumber(tNum);
        pk.setResidentSerialNumber(sNum);
        return pk;
    }

    private BirthDeathReportResident getReport(BirthDeathReportResident.BirthDeathReportResidentPK pk) {
        return birthDeathReportResidentRepository.findById(pk).orElseThrow(ReportNotFoundException::new);
    }
}
