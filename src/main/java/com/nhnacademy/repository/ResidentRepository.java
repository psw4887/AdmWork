package com.nhnacademy.repository;

import com.nhnacademy.domain.dto.ResidentDTO;
import com.nhnacademy.domain.dto.birth.family.ResidentCertFamilyDTO;
import com.nhnacademy.entity.Resident;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    @Query("select r from Resident as r inner join HouseholdCompositionResident as h " +
            "on r.serialNumber = h.householdCompositionResidentPK.residentSerialNumber " +
            "and h.householdCompositionResidentPK.householdSerialNumber = :hNum")
    Page<ResidentDTO> getListByUser(Pageable pageable, @Param("hNum") int hNum);

    ResidentCertFamilyDTO getAllBySerialNumber(int sNum);

    Optional<Resident> findByUserId(String username);

    Page<ResidentDTO> getAllBy(Pageable pageable, int hNum);
}
