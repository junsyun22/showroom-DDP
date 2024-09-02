
package org.example.showroom.talk.service;

import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.entity.Talk;
import org.example.showroom.talk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TalkService {

    private final TalkRepository talkRepository;

    @Autowired
    public TalkService(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    public TalkResponseDto saveTalk(TalkRequestDto talkRequestDto) {
        Talk talk = new Talk(talkRequestDto.getMemberId(), talkRequestDto.getText());
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
        responseDto.setMemberId(talk.getMemberId());
        responseDto.setText(talk.getText());
        responseDto.setCreatedAt(talk.getCreatedAt());
        return responseDto;
    }
}
