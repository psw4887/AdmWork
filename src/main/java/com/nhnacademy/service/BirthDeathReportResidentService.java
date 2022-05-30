package com.nhnacademy.service;

import com.nhnacademy.domain.vo.BdRequest;
import com.nhnacademy.domain.vo.BirthRequest;
import com.nhnacademy.domain.vo.DeathRequest;

public interface BirthDeathReportResidentService {

    void birthRegister(int sNum, BirthRequest request);

    void birthModify(int sNum, int tNum, BdRequest request);

    void birthDelete(int sNum, int tNum);

    void deathRegister(int sNum, DeathRequest request);

    void deathModify(int sNum, int tNum, BdRequest request);

    void deathDelete(int sNum, int tNum);
}
