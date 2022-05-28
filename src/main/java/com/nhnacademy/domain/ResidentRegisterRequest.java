package com.nhnacademy.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResidentRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String genderCode;

    @NotBlank
    private Date birth;

    @NotBlank
    private String birthPlace;

    @NotBlank
    private String baseAddress;
}
