package org.example.showroom.member.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.showroom._core.error.ApplicationException;
import org.example.showroom._core.error.ErrorCode;
import org.example.showroom._core.jwt.JWTTokenProvider;
import org.example.showroom._core.utils.ClientUtils;
import org.example.showroom.member.domain.Authority;
import org.example.showroom.member.domain.Gender;
import org.example.showroom.member.domain.Member;
import org.example.showroom.member.dto.MemberRequestDTO;
import org.example.showroom.member.dto.MemberResponseDTO;
import org.example.showroom.member.dto.SignUpResponseDTO;
import org.example.showroom.member.repository.MemberRepository;
import org.example.showroom.refreshToken.domain.RefreshToken;
import org.example.showroom.refreshToken.repository.RefreshTokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTTokenProvider jwtTokenProvider;

    /*
        기본 회원 가입
     */
    @Transactional
    public SignUpResponseDTO signUp(MemberRequestDTO.signUpDTO requestDTO) {


//        // 비밀번호 확인
//        checkValidPassword(requestDTO.password(), passwordEncoder.encode(requestDTO.confirmPassword()));

        // 회원 생성
        Member member = newMember(requestDTO);

        // 회원 저장
        memberRepository.save(member);

        log.info("회원 가입 성공: ID {}", member.getEmail());

        return SignUpResponseDTO.builder()
                .userName(member.getName())
                .userId(member.getEmail())
                .build();

    }

    /*
        기본 로그인
     */
    public MemberResponseDTO.authTokenDTO login(HttpServletRequest httpServletRequest, MemberRequestDTO.loginDTO requestDTO) {

        String ipAddress = ClientUtils.getClientIp(httpServletRequest);

        // 1. 이메일 확인
        Member member = findMemberByEmail(requestDTO.email())
                .orElseThrow(() -> {
                    log.warn("로그인 실패: 존재하지 않는 ID {} (IP: {})", requestDTO.email(), ipAddress);
                    return new ApplicationException(ErrorCode.EMPTY_EMAIL_MEMBER);
                });

        log.info("로그인 시도: ID {} (IP: {})", requestDTO.email(), ipAddress);

        // 2. 비밀번호 확인
        try {
            checkValidPassword(requestDTO.password(), member.getPassword());
        } catch (ApplicationException e) {
            log.warn("로그인 실패: 잘못된 비밀번호 (ID: {}, IP: {})", requestDTO.email(), ipAddress); // 비밀번호 오류로 로그인 실패 로그
            throw e;
        }

        log.info("로그인 성공: ID {} (IP: {})", requestDTO.email(), ipAddress);

        return getAuthTokenDTO(requestDTO.email(), requestDTO.password(), httpServletRequest);
    }

    // 비밀번호 확인
    private void checkValidPassword(String rawPassword, String encodedPassword) {

        log.info("{} {}", rawPassword, encodedPassword);

        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
        }
    }

    protected Optional<Member> findMemberByEmail(String email) {
        log.info("회원 확인 : {}", email);

        return memberRepository.findByEmail(email);
    }

    // 회원 생성
    protected Member newMember(MemberRequestDTO.signUpDTO requestDTO) {
        return Member.builder()
                .name(requestDTO.name())
                .email(requestDTO.email())
                .password(passwordEncoder.encode(requestDTO.password()))
                .authority(Authority.USER)
                .userArea(requestDTO.userArea())
                .userFamily(requestDTO.userFamily())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 토큰 발급
    protected MemberResponseDTO.authTokenDTO getAuthTokenDTO(String email, String password, HttpServletRequest httpServletRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        AuthenticationManager manager = authenticationManagerBuilder.getObject();
        Authentication authentication = manager.authenticate(usernamePasswordAuthenticationToken);

        // 이메일로 회원 정보 가져오기
        Member member = findMemberByEmail(email)
                .orElseThrow(() -> new ApplicationException(ErrorCode.EMPTY_EMAIL_MEMBER));

        // 단일 권한 추출 (가정: 단일 권한만 부여됨)
        Authority authority = Authority.NONE; // 기본값
        if (!authentication.getAuthorities().isEmpty()) {
            authority = Authority.valueOf(authentication.getAuthorities().iterator().next().getAuthority());
        }

        // 권한을 SimpleGrantedAuthority로 변환
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority.name());

        // JWT 토큰 생성 시 userId와 userName을 함께 전달
        MemberResponseDTO.authTokenDTO authTokenDTO = jwtTokenProvider.generateToken(
                member.getEmail(),
                member.getName(),
                Collections.singletonList(simpleGrantedAuthority)
        );

        // Refresh Token 저장
        refreshTokenRepository.save(RefreshToken.builder()
                .userName(member.getName())  // 실제 이름을 저장
                .ip(ClientUtils.getClientIp(httpServletRequest))
                .authorities(authority)
                .refreshToken(authTokenDTO.refreshToken())
                .build()
        );

        log.info("JWT 토큰 발급: ID {} (IP: {})", member.getEmail(), ClientUtils.getClientIp(httpServletRequest));

        return authTokenDTO;
    }




    // 토큰 재발급
    public MemberResponseDTO.authTokenDTO reissueToken(HttpServletRequest httpServletRequest) {
        // Request Header 에서 JWT Token 추출
        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        // 토큰 유효성 검사
        if(token == null || !jwtTokenProvider.validateToken(token)) {
            throw new ApplicationException(ErrorCode.FAILED_VALIDATE_ACCESS_TOKEN);
        }

        // type 확인
        if(!jwtTokenProvider.isRefreshToken(token)) {
            throw new ApplicationException(ErrorCode.IS_NOT_REFRESH_TOKEN);
        }

        // RefreshToken 조회
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByRefreshToken(token);
        if(refreshToken.isEmpty()) {
            throw new ApplicationException(ErrorCode.FAILED_GET_RERFRESH_TOKEN);
        }

        // 최초 로그인한 ip와 같은지 확인
        String currentIp = ClientUtils.getClientIp(httpServletRequest);
        if (!currentIp.equals(refreshToken.get().getIp())) {
            log.error("비정상적인 IP 감지: 이전 IP {} / 현재 IP {}", refreshToken.get().getIp(), currentIp); // 비정상 IP 감지 로그
            throw new ApplicationException(ErrorCode.DIFFERENT_IP_ADDRESS);
        }

        log.info("정상적인 토큰 재발급: ID {} (IP: {})", refreshToken.get().getUserName(), currentIp);

        // 저장된 RefreshToken 정보를 기반으로 JWT Token 생성
        MemberResponseDTO.authTokenDTO authTokenDTO = jwtTokenProvider.generateToken(
                String.valueOf(refreshToken.get().getId()),  // userId로 사용
                refreshToken.get().getUserName(),  // userName으로 사용
                Collections.singletonList(new SimpleGrantedAuthority(refreshToken.get().getAuthorities().name()))
        );

        // RefreshToken Update
        refreshTokenRepository.save(RefreshToken.builder()
                .ip(currentIp)
                .authorities(refreshToken.get().getAuthorities())
                .refreshToken(authTokenDTO.refreshToken())
                .build()
        );

        return authTokenDTO;
    }

    /*
        로그아웃
     */
    public void logout(HttpServletRequest httpServletRequest) {

        log.info("로그아웃 - Refresh Token 확인");

        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        if(token == null || !jwtTokenProvider.validateToken(token)) {
            throw new ApplicationException(ErrorCode.FAILED_VALIDATE__REFRESH_TOKEN);
        }

        // RefreshToken 조회 및 null 체크
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> {
                    log.error("로그아웃 실패: Refresh Token 을 얻을 수 없습니다. (토큰: {}, IP: {})", token, ClientUtils.getClientIp(httpServletRequest));
                    return new ApplicationException(ErrorCode.FAILED_GET_RERFRESH_TOKEN);
                });

        refreshTokenRepository.delete(refreshToken);

        log.info("로그아웃 성공: IDvv {} (IP: {})", refreshToken.getUserName(), ClientUtils.getClientIp(httpServletRequest));
    }
}
