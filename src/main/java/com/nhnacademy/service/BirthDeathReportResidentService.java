package com.nhnacademy.service;

import com.nhnacademy.domain.dto.BirthDTO;
import com.nhnacademy.domain.vo.BirthRequest;
import com.nhnacademy.domain.dto.DeathDTO;
import com.nhnacademy.domain.vo.DeathRequest;

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
