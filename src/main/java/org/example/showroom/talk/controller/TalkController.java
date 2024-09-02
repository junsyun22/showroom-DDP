
package org.example.showroom.talk.controller;

import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talks")
public class TalkController {

    private final TalkService talkService;

    @Autowired
    public TalkController(TalkService talkService) {
        this.talkService = talkService;
    }

    @PostMapping
    public ResponseEntity<TalkResponseDto> createTalk(@RequestBody TalkRequestDto talkRequestDto) {
        TalkResponseDto savedTalk = talkService.saveTalk(talkRequestDto);
        return ResponseEntity.ok(savedTalk);
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
