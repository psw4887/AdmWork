package com.nhnacademy.service;

import com.nhnacademy.domain.ResidentJoinRequest;
import com.nhnacademy.domain.vo.ResidentModifyRequest;
import com.nhnacademy.domain.vo.ResidentRegisterRequest;
import com.nhnacademy.domain.ResidentView;
import java.util.List;
import org.springframework.data.domain.Pageable;


public interface ResidentService {
    List<ResidentView> allResidents(Pageable pageable, String name);

    void residentRegister(ResidentRegisterRequest residentRegisterRequest);

    void residentModify(int sNum, ResidentModifyRequest residentModifyRequest);

    void residentDelete(int sNum);

    void residentRegisterForLogin(int sNum, ResidentJoinRequest residentJoinRequest);
}
