package org.example.showroom.log.entity;

import jakarta.persistence.*;
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
    @Column(length = 4000)  // 길이가 길어질 수 있으므로, 적절한 길이 설정
    private String question;

    @Column(length = 4000)  // 길이가 길어질 수 있으므로, 적절한 길이 설정
    private String answer;

    public EventLog(String eventType, String userEmail, String ipAddress, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.userEmail = userEmail;
        this.ipAddress = ipAddress;
        this.eventTime = eventTime;
    }

    public EventLog(String eventType, String userEmail, String ipAddress, LocalDateTime eventTime, String question, String answer) {
        this.eventType = eventType;
        this.userEmail = userEmail;
        this.ipAddress = ipAddress;
        this.eventTime = eventTime;
        this.question = question;
        this.answer = answer;
    }
}
