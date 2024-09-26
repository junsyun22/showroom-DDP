
package org.example.showroom.talk.controller;

import org.example.showroom.openfeign.AiConnection;
import org.example.showroom.openfeign.ChatAnswerDTO;
import org.example.showroom.openfeign.ChatQuestDTO;
import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.service.TalkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(TalkController.class);
    private final TalkService talkService;
    private final AiConnection aiConnection;

    @Autowired
    public TalkController(TalkService talkService, AiConnection aiConnection) {
        this.talkService = talkService;
        this.aiConnection = aiConnection;
    }

    @PostMapping
    public ResponseEntity<TalkResponseDto> getTest5(@RequestBody ChatQuestDTO chatQuestDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails) {

        try {
            // Clean the input strings to remove control characters
            chatQuestDTO.setUserId(cleanString(chatQuestDTO.getUserId()));
            chatQuestDTO.setQuestion(cleanString(chatQuestDTO.getQuestion()));
            chatQuestDTO.setAreaSize(cleanString(chatQuestDTO.getAreaSize()));
            chatQuestDTO.setHousemateNum(cleanString(chatQuestDTO.getHousemateNum()));
            log.info("Get Question. userId: {}, question: {}", chatQuestDTO.getUserId(), chatQuestDTO.getQuestion());


            String email = userDetails.getUsername();

            // Call AI service and get the response
            TalkResponseDto aiResponse = aiConnection.postSomeData(chatQuestDTO);

            // Create a new TalkResponseDto with the AI response and user details
            TalkResponseDto responseDto = new TalkResponseDto();
            responseDto.setUserId(email);
            responseDto.setAnswer(chatQuestDTO.getQuestion());
            responseDto.setAreaSize(chatQuestDTO.getAreaSize());
            responseDto.setHousemateNum(chatQuestDTO.getHousemateNum());
            // Set the AI's answer
            responseDto.setAnswer(aiResponse.getAnswer());
            log.info("Get Answer. userId: {}, answer: {}",chatQuestDTO.getUserId(), aiResponse.getAnswer());

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
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
        // Remove control characters and backslashes
        return input.replaceAll("[\\p{Cntrl}\\\\]", "");
    }
}
