package org.example.showroom.log.controller;

import org.example.showroom.log.entity.EventLog;
import org.example.showroom.log.service.EventLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {

    private final EventLogService eventLogService;

    public LogController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    // 회원가입 로그만 가져오는 API
    @GetMapping("/signup")
    public ResponseEntity<List<EventLog>> getSignUpLogs() {
        List<EventLog> logs = eventLogService.getSignUpLogs();
        return ResponseEntity.ok(logs);
    }

    // 로그인 로그만 가져오는 API
    @GetMapping("/login")
    public ResponseEntity<List<EventLog>> getLoginLogs() {
        List<EventLog> logs = eventLogService.getLoginLogs();
        return ResponseEntity.ok(logs);
    }

    // 로그아웃 로그만 가져오는 API
    @GetMapping("/logout")
    public ResponseEntity<List<EventLog>> getLogoutLogs() {
        List<EventLog> logs = eventLogService.getLogoutLogs();
        return ResponseEntity.ok(logs);
    }
}
