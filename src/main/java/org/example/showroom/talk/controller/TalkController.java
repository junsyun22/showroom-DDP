
package org.example.showroom.talk.controller;

import org.example.showroom.openfeign.AiConnection;
import org.example.showroom.openfeign.ChatAnswerDTO;
import org.example.showroom.openfeign.ChatQuestDTO;
import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/talks")
public class TalkController {

    private final TalkService talkService;
    private final AiConnection aiConnection;
    @Autowired
    public TalkController(TalkService talkService, AiConnection aiConnection) {
        this.talkService = talkService;
        this.aiConnection = aiConnection;
    }

//    @PostMapping
//    public ResponseEntity<TalkResponseDto> createTalk(@RequestBody TalkRequestDto talkRequestDto,
//                                                      @AuthenticationPrincipal UserDetails userDetails) {
//        // 현재 인증된 사용자의 username 가져오기
//        String email = userDetails.getUsername();
//
//        // 사용자의 정보를 사용하여 대화 저장 로직 처리
//        TalkResponseDto savedTalk = talkService.saveTalk(talkRequestDto, email);
//        return ResponseEntity.ok(savedTalk);
//    }
    @PostMapping
    public ResponseEntity<TalkResponseDto> getTest5(@RequestBody ChatQuestDTO chatQuestDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();

        System.out.println("test call!: " + userDetails.toString());
        // Call AI service and get the response
        TalkResponseDto aiResponse = aiConnection.postSomeData(chatQuestDTO);

        // Create a new TalkResponseDto with the AI response and user details
        TalkResponseDto responseDto = new TalkResponseDto();
        responseDto.setUserId(email);
        responseDto.setAnswer(chatQuestDTO.getQuestion());
        responseDto.setAreaSize(chatQuestDTO.getAreaSize());
        responseDto.setHousemateNum(chatQuestDTO.getHousemateNum());
        responseDto.setCreatedAt(LocalDateTime.now());

        // Set the AI's answer
        responseDto.setAnswer(aiResponse.getAnswer());

        return ResponseEntity.ok(responseDto);
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
}
