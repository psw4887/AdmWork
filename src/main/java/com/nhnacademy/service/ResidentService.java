package com.nhnacademy.service;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.domain.ResidentView;
import java.util.List;
import org.springframework.data.domain.Pageable;


public interface ResidentService {
    List<ResidentView> allResidents(Pageable pageable);

    void residentRegister(ResidentRegisterRequest residentRegisterRequest);

    void residentModify(int sNum, ResidentModifyRequest residentModifyRequest);

    void residentDelete(int sNum);
}
