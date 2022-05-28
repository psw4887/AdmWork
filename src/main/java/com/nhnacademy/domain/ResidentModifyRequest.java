package com.nhnacademy.domain;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResidentModifyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String genderCode;
}
