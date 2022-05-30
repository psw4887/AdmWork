package com.nhnacademy.service.impl;

import com.nhnacademy.domain.dto.family.FamilyCertFamilyDTO;
import com.nhnacademy.domain.dto.family.FamilyCertResidentDTO;
import com.nhnacademy.domain.dto.family.FamilyCertificateDTO;
import com.nhnacademy.domain.dto.family.ResidentCertFamilyDTO;
import com.nhnacademy.entity.CertificateIssue;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.repository.CertificateIssueRepository;
import com.nhnacademy.repository.FamilyRelationShipRepository;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.CertificateService;
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
    private final BirthDeathReportResidentRepository bRepository;

    private static final Random random = new Random();

    public CertificateServiceImpl(ResidentRepository rRepository, FamilyRelationShipRepository fRepository,
                                  CertificateIssueRepository cRepository, BirthDeathReportResidentRepository bRepository) {
        this.rRepository = rRepository;
        this.fRepository = fRepository;
        this.cRepository = cRepository;
        this.bRepository = bRepository;
    }

    @Override
    public List<CertificateIssue> getCertificateList(int sNUm, Pageable pageable) {
        return cRepository.getAllByResident(rRepository.findById(sNUm).orElseThrow(ResidentNotFoundException::new), pageable).getContent();
    }

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
        familyCertResidentDTO.setRelation("본인");

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
        String before = String.valueOf(random.nextInt(999)+9000);
        String center = String.valueOf(random.nextInt(10000)+9999);
        String after = String.valueOf(random.nextInt(10000000)+9999999);
        String cNum = before + center + after;
        rDto = sortRelation(rDto);

        CertificateIssue cert = new CertificateIssue(Long.valueOf(cNum), resident, "가족관계증명서", LocalDate.now());
        addCertificate(cert);
        return new FamilyCertificateDTO(cNum, LocalDate.now(), resident.getRegistrationBaseAddress(), rDto);
    }

    @Override
    @Transactional
    public void addCertificate(CertificateIssue cert) {
        cRepository.save(cert);
    }

    private List<FamilyCertResidentDTO> sortRelation(List<FamilyCertResidentDTO> list) {
        List<FamilyCertResidentDTO> sortList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sortList.add(null);
        }
        for(FamilyCertResidentDTO dto : list) {
            switch (dto.getRelation()) {
                case "본인": {
                    sortList.set(0, dto);
                    break;
                }
                case "부": {
                    sortList.set(1, dto);
                    break;
                }
                case "모": {
                    sortList.set(2, dto);
                    break;
                }
                case "배우자": {
                    sortList.set(3, dto);
                    break;
                }
                case "자녀": {
                    sortList.set(4, dto);
                    break;
                }
            }
        }
        for (int i =0; i<5; i++) {
            sortList.remove(null);
        }
        return sortList;
    }
}
