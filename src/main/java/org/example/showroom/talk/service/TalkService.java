package org.example.showroom.talk.service;

import org.example.showroom.openfeign.AiConnection;
import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.entity.Talk;
import org.example.showroom.talk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TalkService {

    private final TalkRepository talkRepository;
    private final AiConnection aiConnection;

    @Autowired
    public TalkService(TalkRepository talkRepository, AiConnection aiConnection) {
        this.talkRepository = talkRepository;
        this.aiConnection = aiConnection;
    }

    public TalkResponseDto saveTalk(TalkRequestDto talkRequestDto) {
        Talk talk = new Talk(
                null,
                talkRequestDto.getUserId(),
                talkRequestDto.getQuestion(),
                talkRequestDto.getAreaSize(),
                talkRequestDto.getHousemateNum(),
                LocalDateTime.now()
        );

        Talk savedTalk = talkRepository.save(talk);
        return mapToResponseDto(savedTalk);
    }

    public List<TalkResponseDto> getAllTalks() {
        return talkRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteTalk(Long id) {
        talkRepository.deleteById(id);
    }

    private TalkResponseDto mapToResponseDto(Talk talk) {
        TalkResponseDto responseDto = new TalkResponseDto();
        responseDto.setId(talk.getId());
        responseDto.setUserId(talk.getMemberId());  // Corrected from setUserId to setMemberId
        responseDto.setQuestion(talk.getQuestion());
        responseDto.setAreaSize(talk.getAreaSize());
        responseDto.setHousemateNum(talk.getHousemateNum());
        responseDto.setCreatedAt(talk.getCreatedAt());
        return responseDto;
    }
}
