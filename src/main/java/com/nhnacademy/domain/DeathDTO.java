package com.nhnacademy.domain;

import com.nhnacademy.entity.BirthDeathReportResident;
import lombok.Data;

import java.util.Date;

@Data
public class DeathDTO {

    BirthDeathReportResident.BirthDeathReportResidentPK deathCode;

    Date deathDate;
}
