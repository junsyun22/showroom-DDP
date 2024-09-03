package org.example.showroom.refreshToken.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.showroom.member.domain.Authority;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자를 자동으로 생성
    private Long id;

    private String userName;

    private String ip;

    private String refreshToken;

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장
    private Authority authorities;

    @Builder
    public RefreshToken(String userName, String ip, Authority authorities, String refreshToken) {
        this.userName = userName;
        this.ip = ip;
        this.authorities = authorities;
        this.refreshToken = refreshToken;
    }
}
