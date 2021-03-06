package com.nhnacademy.service.impl;

import com.nhnacademy.domain.vo.BdRequest;
import com.nhnacademy.domain.vo.BirthRequest;
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

import java.time.LocalDate;
import java.util.Objects;

@Service("birthDeathReportResidentService")
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public void birthRegister(int sNum, BirthRequest request) {
        BirthDeathReportResident report = new BirthDeathReportResident();
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident.BirthDeathReportResidentPK pk = new BirthDeathReportResident.BirthDeathReportResidentPK();
        pk.setResidentSerialNumber(sNum);
        pk.setBirthDeathTypeCode("출생");
        pk.setReportResidentSerialNumber(request.getReportResident());

        report.setBirthDeathReportResidentPK(pk);
        report.setResident(resident);
        report.setBirthDeathReportDate(LocalDate.now());
        report.setBirthReportQualificationsCode(request.getRelationship());
        if(Objects.nonNull(request.getEmail())) {
            report.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getPhoneNumber())) {
            report.setPhoneNumber(request.getPhoneNumber());
        }

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void birthModify(int sNum, int tNum, BdRequest request) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(tNum, sNum);
        pk.setBirthDeathTypeCode("출생");

        BirthDeathReportResident report = getReport(pk);

        report.setBirthDeathReportDate(LocalDate.now());
        report.setBirthReportQualificationsCode(request.getRelationship());

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
        pk.setResidentSerialNumber(sNum);
        pk.setBirthDeathTypeCode("사망");
        pk.setReportResidentSerialNumber(request.getReportResident());

        report.setBirthDeathReportResidentPK(pk);
        report.setResident(resident);
        report.setBirthDeathReportDate(LocalDate.now());
        report.setBirthReportQualificationsCode(request.getRelationship());
        if(Objects.nonNull(request.getEmail())) {
            report.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getPhoneNumber())) {
            report.setPhoneNumber(request.getPhoneNumber());
        }

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void deathModify(int sNum, int tNum, BdRequest request) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(sNum, tNum);
        pk.setBirthDeathTypeCode("사망");

        BirthDeathReportResident report = getReport(pk);

        report.setBirthDeathReportDate(LocalDate.now());
        report.setBirthReportQualificationsCode(request.getRelationship());

        birthDeathReportResidentRepository.save(report);
    }

    @Transactional
    @Override
    public void deathDelete(int sNum, int tNum) {
        BirthDeathReportResident.BirthDeathReportResidentPK pk = getPK(tNum, sNum);
        pk.setBirthDeathTypeCode("사망");

        birthDeathReportResidentRepository.deleteById(pk);
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
