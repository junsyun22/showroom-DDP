package org.example.showroom.log.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;  // 예: 회원가입, 로그인, 로그아웃
    private String userEmail;
    private String ipAddress;
    private LocalDateTime eventTime;

    public EventLog(String eventType, String userEmail, String ipAddress, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.userEmail = userEmail;
        this.ipAddress = ipAddress;
        this.eventTime = eventTime;
    }
}
