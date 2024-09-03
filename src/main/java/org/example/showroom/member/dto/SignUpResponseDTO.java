package org.example.showroom.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDTO {
    private String userName;
    private String userId;
}
