package com.nhnacademy.service.impl;

import com.nhnacademy.domain.dto.birth.BirthDTO;
import com.nhnacademy.domain.dto.death.DeathDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertFamilyDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertResidentDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertificateDTO;
import com.nhnacademy.domain.dto.birth.family.ResidentCertFamilyDTO;
import com.nhnacademy.domain.dto.registration.RegistrationDTO;
import com.nhnacademy.entity.*;
import com.nhnacademy.exception.HouseHoldNotFoundException;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.*;
import com.nhnacademy.service.CertificateService;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {

    private final ResidentRepository rRepository;
    private final FamilyRelationShipRepository fRepository;
    private final CertificateIssueRepository cRepository;
    private final HouseholdRepository hRepository;
    private final HouseholdCompositionResidentRepository hcRepository;
    private final HouseholdMovementAddressRepository hmRepository;
    private final BirthDeathReportResidentRepository bRepository;

    private static final Random random = new Random();

    public CertificateServiceImpl(ResidentRepository rRepository, FamilyRelationShipRepository fRepository,
                                  CertificateIssueRepository cRepository, HouseholdRepository hRepository, HouseholdCompositionResidentRepository hcRepository, HouseholdMovementAddressRepository hmRepository, BirthDeathReportResidentRepository bRepository) {
        this.rRepository = rRepository;
        this.fRepository = fRepository;
        this.cRepository = cRepository;
        this.hRepository = hRepository;
        this.hcRepository = hcRepository;
        this.hmRepository = hmRepository;
        this.bRepository = bRepository;
    }

    @Override
    public List<CertificateIssue> getCertificateList(int sNUm, Pageable pageable) {
        return cRepository.getAllByResident(rRepository.findById(sNUm).orElseThrow(ResidentNotFoundException::new), pageable).getContent();
    }

    @Transactional
    @Override
    public FamilyCertificateDTO getFamilyCertificate(int sNum) {
        Resident resident = rRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        List<FamilyCertFamilyDTO> fDto = fRepository.getAllByResident(resident);
        List<FamilyCertResidentDTO> rDto = new ArrayList<>();

        FamilyCertResidentDTO familyCertResidentDTO = new FamilyCertResidentDTO();
        familyCertResidentDTO.setName(resident.getName());
        familyCertResidentDTO.setBirthDate(resident.getBirthDate());
        familyCertResidentDTO.setGenderCode(resident.getGenderCode());
        familyCertResidentDTO.setRegistrationNumber(resident.getRegistrationNumber());
        familyCertResidentDTO.setRelation("??????");

        rDto.add(familyCertResidentDTO);

        for (FamilyCertFamilyDTO dto : fDto) {
            ResidentCertFamilyDTO residentDTO = rRepository.getAllBySerialNumber(dto.getFamilyRelationShipPK().getFamilyResidentSerialNumber());
            FamilyCertResidentDTO resiDTO = new FamilyCertResidentDTO();

            resiDTO.setName(residentDTO.getName());
            resiDTO.setRegistrationNumber(residentDTO.getRegistrationNumber());
            resiDTO.setGenderCode(residentDTO.getGenderCode());
            resiDTO.setBirthDate(residentDTO.getBirthDate());
            resiDTO.setRelation(dto.getFamilyRelationShipCode());

            rDto.add(resiDTO);

        }
        String before = String.valueOf(random.nextInt(8999)+1000);
        String center = String.valueOf(random.nextInt(10000)+9999);
        String after = String.valueOf(random.nextInt(10000000)+9999999);

        String cNum = before + center + after;
        rDto = sortRelation(rDto);

        CertificateIssue cert = new CertificateIssue(Long.valueOf(cNum), resident, "?????????????????????", LocalDate.now());
        addCertificate(cert);
        return new FamilyCertificateDTO(cNum, LocalDate.now(), resident.getRegistrationBaseAddress(), rDto);
    }

    @Transactional
    @Override
    public RegistrationDTO getRegistrationCertificate(int sNum) {
        Resident resident = rRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        if(Objects.isNull(hcRepository.getHouseSerialNumberByResidentNumber(sNum))) {
            return null;
        }
        Integer hNum = hcRepository.getHouseSerialNumberByResidentNumber(sNum);
        Household household = hRepository.findById(hNum).orElseThrow(HouseHoldNotFoundException::new);
        List<HouseholdCompositionResident> list = hcRepository.getAllByHousehold(household);
        List<HouseholdMovementAddress> moveList = hmRepository.getAllByHouseholdOrderByHouseholdMovementAddressPKDesc(household);

        String before = String.valueOf(random.nextInt(999)+9000);
        String center = String.valueOf(random.nextInt(10000)+9999);
        String after = String.valueOf(random.nextInt(10000000)+9999999);
        String cNum = before + center + after;

        CertificateIssue cert = new CertificateIssue(Long.valueOf(cNum), resident, "??????????????????", LocalDate.now());
        addCertificate(cert);

        return new RegistrationDTO(cNum, LocalDate.now(), sortHouseholdRelation(list), moveList);
    }

    @Override
    @Transactional
    public BirthDTO getBirthCertificate(int sNum) {
        Resident resident = rRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        BirthDeathReportResident issue = bRepository.findByBirthTargetResident(sNum);
        Integer fathersNum = fRepository.findFatherByBaseSNum(sNum);
        Resident father = rRepository.findById(fathersNum).orElseThrow(ResidentNotFoundException::new);
        Integer mothersNum = fRepository.findMotherByBaseSNum(sNum);
        Resident mother = rRepository.findById(mothersNum).orElseThrow(ResidentNotFoundException::new);
        Resident issuer = rRepository.findById(issue.getBirthDeathReportResidentPK().getReportResidentSerialNumber()).orElseThrow(ResidentNotFoundException::new);

        String before = String.valueOf(random.nextInt(999)+4000);
        String center = String.valueOf(random.nextInt(10000)+9999);
        String after = String.valueOf(random.nextInt(10000000)+9999999);
        String cNum = before + center + after;

        CertificateIssue cert = new CertificateIssue(Long.valueOf(cNum), resident, "???????????????", LocalDate.now());
        addCertificate(cert);

        return new BirthDTO(resident, issue, father, mother, issuer);
    }

    @Override
    @Transactional
    public DeathDTO getDeathCertificate(int sNum) {
        Resident resident = rRepository.findById(sNum).orElseThrow(ResidentNotFoundException::new);
        BirthDeathReportResident issue = bRepository.findByDeathTargetResident(sNum);
        Resident issuer = rRepository.findById(issue.getBirthDeathReportResidentPK().getReportResidentSerialNumber()).orElseThrow(ResidentNotFoundException::new);

        String before = String.valueOf(random.nextInt(999)+7000);
        String center = String.valueOf(random.nextInt(10000)+9999);
        String after = String.valueOf(random.nextInt(10000000)+9999999);
        String cNum = before + center + after;

        CertificateIssue cert = new CertificateIssue(Long.valueOf(cNum), resident, "???????????????", LocalDate.now());
        addCertificate(cert);

        return new DeathDTO(resident, issue, issuer);
    }

    @Override
    @Transactional
    public void addCertificate(CertificateIssue cert) {
        cRepository.save(cert);
    }

    private List<FamilyCertResidentDTO> sortRelation(List<FamilyCertResidentDTO> list) {
        List<FamilyCertResidentDTO> sortList = new ArrayList<>();
        int child = 4;
        for (int i = 0; i < 10; i++) {
            sortList.add(null);
        }
        for(FamilyCertResidentDTO dto : list) {
            switch (dto.getRelation()) {
                case "??????": {
                    sortList.set(0, dto);
                    break;
                }
                case "???": {
                    sortList.set(1, dto);
                    break;
                }
                case "???": {
                    sortList.set(2, dto);
                    break;
                }
                case "?????????": {
                    sortList.set(3, dto);
                    break;
                }
                case "??????": {
                    sortList.set(child, dto);
                    child++;
                    break;
                }
            }
        }
        for (int i =0; i<10; i++) {
            sortList.remove(null);
        }
        return sortList;
    }

    private List<HouseholdCompositionResident> sortHouseholdRelation(List<HouseholdCompositionResident> list) {
        List<HouseholdCompositionResident> sortList = new ArrayList<>();
        int child = 4;
        int with = 5;
        for (int i = 0; i < 15; i++) {
            sortList.add(null);
        }
        for(HouseholdCompositionResident dto : list) {
            switch (dto.getRelationshipCode()) {
                case "??????": {
                    sortList.set(0, dto);
                    break;
                }
                case "???": {
                    sortList.set(1, dto);
                    break;
                }
                case "???": {
                    sortList.set(2, dto);
                    break;
                }
                case "?????????": {
                    sortList.set(3, dto);
                    break;
                }
                case "??????": {
                    sortList.set(child, dto);
                    child++;
                    with++;
                    break;
                }
                case "?????????": {
                    sortList.set(with, dto);
                    with++;
                    break;
                }
            }
        }
        for (int i =0; i<15; i++) {
            sortList.remove(null);
        }
        return sortList;
    }
}
