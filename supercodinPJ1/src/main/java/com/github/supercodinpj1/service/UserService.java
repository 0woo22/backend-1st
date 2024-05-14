package com.github.supercodinpj1.service;

import com.github.supercodinpj1.domain.User;
import com.github.supercodinpj1.dto.UserDto;
import com.github.supercodinpj1.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service    //비즈니스 로직을 처리하는 service 클래스
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CryptoService cryptoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signupService(UserDto userDto) {

        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        //이메일 중복 확인
        if (byEmail.isPresent()) {
            return "이미 가입된 이메일 입니다.";
        }
        //비밀번호 유효성 검사
        if (userDto.getPassword().matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$")) {
            return "비밀번호는 영문과 숫자만 가능하며, 8자리 이상이어야 합니다.";}
           else {
            userRepository.save(com.github.supercodinpj1.domain.User.builder().email(userDto.getEmail())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .build()).getEmail();

            return "회원가입이 완료되었습니다";
        }
    }

    public String loginService(UserDto userDto) {

        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());

        if(byEmail.isPresent()) {
            String password = byEmail.get().getPassword();
            boolean matches = cryptoService.passwordEncoder().matches(userDto.getPassword(), password);
            if (matches){
                return jwtService.encode(userDto);
            }  else return null;
        } else return null;

        //return "로그인에 성공했습니다.";

    }

    public String userLogout(String token) {
        return getString(token);
    }

    public String getString(String token) {
        if (jwtService.isTokenExpired(token)) {
            return "만료된 토큰입니다";
        }
        if (!jwtService.isPresent(token)) {
            return "가입되지 않은 정보입니다. 토큰을 다시 한 번 확인해주세요";

        } else return "로그아웃 하였습니다.";
    }


}
