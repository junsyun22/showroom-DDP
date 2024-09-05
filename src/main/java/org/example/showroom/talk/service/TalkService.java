package org.example.showroom.talk.service;

import org.example.showroom.member.domain.Member;
import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.entity.Talk;
import org.example.showroom.talk.repository.TalkRepository;
import org.example.showroom.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TalkService {

    private final TalkRepository talkRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TalkService(TalkRepository talkRepository, MemberRepository memberRepository) {
        this.talkRepository = talkRepository;
        this.memberRepository = memberRepository;
    }

    public TalkResponseDto saveTalk(TalkRequestDto talkRequestDto) {
        Talk talk = new Talk(
                talkRequestDto.getMemberId(),
                talkRequestDto.getQuestion(),
                talkRequestDto.getAreaSize(),
                talkRequestDto.getHousemateNum(),
                LocalDateTime.now()
        );

        Talk savedTalk = talkRepository.save(talk);
        return mapToResponseDtoWithMember(savedTalk);
    }

    public List<TalkResponseDto> getAllTalks() {
        List<Talk> talks = talkRepository.findAll();
        return talks.stream()
                .map(this::mapToResponseDtoWithMember)
                .collect(Collectors.toList());
    }

    public void deleteTalk(Long id) {
        talkRepository.deleteById(id);
    }

    private TalkResponseDto mapToResponseDtoWithMember(Talk talk) {
        Optional<Member> memberOptional = memberRepository.findById(talk.getMemberId());
        Member member = memberOptional.orElse(null);

        TalkResponseDto responseDto = new TalkResponseDto();
        responseDto.setId(talk.getId());
        responseDto.setMemberId(talk.getMemberId());
        responseDto.setMemberName(member != null ? member.getName() : "Unknown"); // Member 이름 추가
        responseDto.setQuestion(talk.getQuestion());
        responseDto.setAreaSize(talk.getAreaSize());
        responseDto.setHousemateNum(talk.getHousemateNum());
        responseDto.setCreatedAt(talk.getCreatedAt());
        return responseDto;
    }
}
