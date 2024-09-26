package org.example.showroom.talk.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.showroom.log.service.EventLogService;
import org.example.showroom.openfeign.AiConnection;
import org.example.showroom.openfeign.ChatQuestDTO;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.service.TalkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talks")
public class TalkController {

    private static final Logger log = LoggerFactory.getLogger(TalkController.class);
    private final TalkService talkService;
    private final AiConnection aiConnection;
    private final EventLogService eventLogService;  // EventLogService 추가

    @Autowired
    public TalkController(TalkService talkService, AiConnection aiConnection, EventLogService eventLogService) {
        this.talkService = talkService;
        this.aiConnection = aiConnection;
        this.eventLogService = eventLogService;  // EventLogService 주입
    }

    @PostMapping
    public ResponseEntity<TalkResponseDto> getTest5(@RequestBody ChatQuestDTO chatQuestDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails,
                                                    HttpServletRequest request) {

        try {
            // Clean the input strings to remove control characters
            chatQuestDTO.setUserId(cleanString(chatQuestDTO.getUserId()));
            chatQuestDTO.setQuestion(cleanString(chatQuestDTO.getQuestion()));
            chatQuestDTO.setAreaSize(cleanString(chatQuestDTO.getAreaSize()));
            chatQuestDTO.setHousemateNum(cleanString(chatQuestDTO.getHousemateNum()));

            String email = userDetails.getUsername();
            String ipAddress = getClientIp(request);  // IP 주소 추출

            // Call AI service and get the response
            TalkResponseDto aiResponse = aiConnection.postSomeData(chatQuestDTO);

            // AI 응답 기록 (DB에 저장)
            log.info("AI 응답: 이메일: {}, userId: {}, answer: {}", email, chatQuestDTO.getUserId(), aiResponse.getAnswer());
            eventLogService.logEventWithDetails("AI 응답", email, ipAddress, chatQuestDTO.getQuestion(), aiResponse.getAnswer());

            // 응답 DTO 생성
            TalkResponseDto responseDto = new TalkResponseDto();
            responseDto.setUserId(email);
            responseDto.setAnswer(aiResponse.getAnswer());
            responseDto.setAreaSize(chatQuestDTO.getAreaSize());
            responseDto.setHousemateNum(chatQuestDTO.getHousemateNum());

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            // 예외 발생 시에도 로그 기록
            log.error("AI 요청 실패: 이메일: {}, 에러: {}", userDetails.getUsername(), e.getMessage());
            eventLogService.logEventWithDetails("AI 요청 실패", userDetails.getUsername(), getClientIp(request), chatQuestDTO.getQuestion(), null);
            return ResponseEntity.badRequest().body(new TalkResponseDto());
        }
    }

    @GetMapping
    public ResponseEntity<List<TalkResponseDto>> getAllTalks() {
        List<TalkResponseDto> talks = talkService.getAllTalks();
        return ResponseEntity.ok(talks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalk(@PathVariable Long id) {
        talkService.deleteTalk(id);
        return ResponseEntity.noContent().build();
    }

    private String cleanString(String input) {
        if (input == null) {
            return null;
        }
        // 제어 문자를 제거
        return input.replaceAll("[\\p{Cntrl}\\\\]", "");
    }

    // 클라이언트의 IP 주소를 추출하는 메서드
    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
