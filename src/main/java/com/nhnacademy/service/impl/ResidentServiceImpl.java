package com.nhnacademy.service.impl;

import com.nhnacademy.domain.ResidentJoinRequest;
import com.nhnacademy.domain.vo.ResidentModifyRequest;
import com.nhnacademy.domain.vo.ResidentRegisterRequest;
import com.nhnacademy.domain.ResidentView;
import com.nhnacademy.domain.dto.ResidentDTO;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.repository.FamilyRelationShipRepository;
import com.nhnacademy.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.repository.HouseholdRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.ResidentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final FamilyRelationShipRepository familyRepository;
    private final BirthDeathReportResidentRepository birthDeathRepository;
    private final HouseholdCompositionResidentRepository compositionResidentRepository;
    private final HouseholdRepository holdRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentServiceImpl(ResidentRepository residentRepository,
                               FamilyRelationShipRepository familyRepository,
                               BirthDeathReportResidentRepository birthDeathRepository,
                               HouseholdCompositionResidentRepository compositionResidentRepository,
                               HouseholdRepository holdRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.familyRepository = familyRepository;
        this.birthDeathRepository = birthDeathRepository;
        this.compositionResidentRepository = compositionResidentRepository;
        this.holdRepository = holdRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ResidentView> allResidents(Pageable pageable, String name) {
        List<ResidentView> views = new ArrayList<>();
        Resident resident = residentRepository.findByUserId(name).orElseThrow(ResidentNotFoundException::new);
        Integer hNum = compositionResidentRepository.getHouseSerialNumberByResidentNumber(resident.getSerialNumber());
        List<ResidentDTO> list = residentRepository.getListByUser(pageable, hNum).getContent();
        for (ResidentDTO dto:list) {
            ResidentView view = new ResidentView();
            view.setSNum(dto.getSerialNumber());
            view.setName(dto.getName());
            view.setGender(dto.getGenderCode());
            view.setIsBirth(!birthDeathRepository.isExistBirth(dto.getSerialNumber()).isEmpty());
            view.setIsDeath(!birthDeathRepository.isExistDeath(dto.getSerialNumber()).isEmpty());
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

        residentRepository.save(resident);
    }

    @Transactional
    @Override
    public void residentDelete(int sNum) {
        Integer hNum = compositionResidentRepository.getHouseSerialNumberByResidentNumber(sNum, "??????");
        if (Objects.isNull(hNum) || Objects.isNull(compositionResidentRepository.checkHouseholderMember(hNum))) {
            residentRepository.deleteById(sNum);
            familyRepository.findForDeleteFamily(sNum);
            birthDeathRepository.findForDeleteReport(sNum);
            compositionResidentRepository.goodByeHouseMember(sNum);
        } else {
            List<Integer> list = compositionResidentRepository.getListByhNum(hNum);
            if(list.size() == 1 && list.get(0) == sNum) {
                residentRepository.deleteById(sNum);
                familyRepository.findForDeleteFamily(sNum);
                birthDeathRepository.findForDeleteReport(sNum);
                compositionResidentRepository.goodByeHouseMember(sNum);
                holdRepository.findDeleteHouseHold(sNum);
            }
            log.error("??????????????? ?????? ????????? ??????????????????.");
        }
    }

    @Transactional
    @Override
    public void residentRegisterForLogin(int sNum, ResidentJoinRequest residentJoinRequest) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        resident.setUserId(residentJoinRequest.getId());
        resident.setUserPw(passwordEncoder.encode(residentJoinRequest.getPw()));
        resident.setUserEmail(residentJoinRequest.getEmail());

        residentRepository.save(resident);
    }
}
