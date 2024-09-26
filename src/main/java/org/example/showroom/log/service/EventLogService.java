package org.example.showroom.log.service;

import lombok.RequiredArgsConstructor;
import org.example.showroom.log.entity.EventLog;
import org.example.showroom.log.repository.EventLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    public void logEvent(String eventType, String userEmail, String ipAddress) {
        EventLog log = new EventLog(eventType, userEmail, ipAddress, LocalDateTime.now());
        eventLogRepository.save(log);
    }

    // 회원가입 로그만 가져오기
    public List<EventLog> getSignUpLogs() {
        return eventLogRepository.findByEventType("회원가입");
    }

    // 로그인 로그만 가져오기
    public List<EventLog> getLoginLogs() {
        return eventLogRepository.findByEventType("로그인");
    }

    // 로그아웃 로그만 가져오기
    public List<EventLog> getLogoutLogs() {
        return eventLogRepository.findByEventType("로그아웃");
    }

    // AI 로그만 가져오기 (AI 질문, AI 응답, AI 요청 실패)
    public List<EventLog> getAiLogs() {
        return eventLogRepository.findByEventTypeIn(List.of("AI 질문", "AI 응답", "AI 요청 실패"));
    }
    public void logEventWithDetails(String eventType, String userEmail, String ipAddress, String question, String answer) {
        EventLog log = new EventLog(eventType, userEmail, ipAddress, LocalDateTime.now(), question, answer);
        eventLogRepository.save(log);
    }
}
