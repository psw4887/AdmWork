package com.nhnacademy.service.impl;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.domain.ResidentView;
import com.nhnacademy.domain.ResidentDTO;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.ResidentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public List<ResidentView> allResidents(Pageable pageable) {
        List<ResidentView> views = new ArrayList<>();
        List<ResidentDTO> list = residentRepository.getAllBy(pageable).getContent();
        for (ResidentDTO dto:list) {
            ResidentView view = new ResidentView();
            view.setSNum(dto.getSerialNumber());
            view.setName(dto.getName());
            view.setGender(dto.getGenderCode());
            views.add(view);
        }
        return views;
    }

    @Transactional
    @Override
    public void residentRegister(ResidentRegisterRequest registerRequest) {
        Resident resident = new Resident();
        resident.setName(registerRequest.getName());
        resident.setRegistrationNumber(registerRequest.getRegistrationNumber());
        resident.setGenderCode(registerRequest.getGenderCode());
        resident.setBirthDate(registerRequest.getBirth());
        resident.setBirthPlaceCode(registerRequest.getBirthPlace());
        resident.setRegistrationBaseAddress(registerRequest.getBaseAddress());

        residentRepository.save(resident);
    }

    @Transactional
    @Override
    public void residentModify(int sNum, ResidentModifyRequest modifyRequest) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(
            ResidentNotFoundException::new);

        resident.setName(modifyRequest.getName());
        resident.setGenderCode(modifyRequest.getGenderCode());
    }

    @Transactional
    @Override
    public void residentDelete(int sNum) {
        residentRepository.deleteById(sNum);
    }
}
