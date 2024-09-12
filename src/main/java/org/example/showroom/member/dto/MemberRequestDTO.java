package org.example.showroom.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MemberRequestDTO {

    // 기본 회원 가입
    public record signUpDTO(
            @NotBlank(message = "이름을 입력해 주세요.")
            @JsonProperty("userName")
            String name,

            @Email(message = "올바른 이메일 주소를 입력해 주세요.")
            @NotBlank(message = "이메일을 입력해 주세요.")
            @JsonProperty("userId")
            String email,

            @Schema(description = "패스워드", defaultValue = "1234")
            @JsonProperty("userPassword")
            String password,

            @NotNull(message = "거주 평수를 입력해 주세요.")
            @JsonProperty("userArea")
            Integer userArea,

            @NotNull(message = "가구 인수를 입력해 주세요.")
            @JsonProperty("userFamilly")
            Integer userFamily
    ) {
    }

    // 기본 로그인
    public record loginDTO(
            @Email(message = "올바른 이메일 주소를 입력해 주세요.")
            @NotBlank(message = "이메일을 입력해 주세요.")
            @JsonProperty("userId")
            String email,

            @JsonProperty("userPassword")
            String password
    ) {
    }
}