package com.nhnacademy.service;

import com.nhnacademy.domain.BirthDTO;
import com.nhnacademy.domain.BirthRequest;
import com.nhnacademy.domain.DeathDTO;
import com.nhnacademy.domain.DeathRequest;
import com.nhnacademy.entity.BirthDeathReportResident;

import java.util.List;

public interface BirthDeathReportResidentService {

    List<BirthDTO> getBirthReport(int sNum);

    List<DeathDTO> getDeathReport(int sNum);

    void birthRegister(int sNum, BirthRequest request);

    void birthModify(int sNum, int tNum, BirthRequest request);

    void birthDelete(int sNum, int tNum);

    void deathRegister(int sNum, DeathRequest request);

    void deathModify(int sNum, int tNum, DeathRequest request);

    void deathDelete(int sNum, int tNum);
}