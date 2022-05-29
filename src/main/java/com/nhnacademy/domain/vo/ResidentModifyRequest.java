package com.nhnacademy.domain.vo;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResidentModifyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String genderCode;
}
