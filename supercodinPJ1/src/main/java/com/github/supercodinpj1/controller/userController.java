//package com.github.supercodinpj1.controller;
//
//import com.github.supercodinpj1.dto.ResponseDto;
//import com.github.supercodinpj1.dto.UserDto;
//import com.github.supercodinpj1.service.JwtService;
//import com.github.supercodinpj1.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/api") //내부에 선언한 메서드의 URL 리소스 앞에 @RequestMapping의 값이 공통 값으로 추가됨.
//@RequiredArgsConstructor
//@RestController //사용자 요청을 제어하는 controller 클래스
//public class userController {
//
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<ResponseDto> signUpController(@RequestBody UserDto userDto){
//        String signup = userService.signupService(userDto);
//        HttpHeaders headers = new HttpHeaders();
//        ResponseDto signupResponse = new ResponseDto();
//        signupResponse.setMassage(signup);
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(signupResponse);
//        //return "회원가입을 완료 했습니다!";
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<ResponseDto> loginController(@RequestBody UserDto userDto) {
//        ResponseDto signupResponse = new ResponseDto();
//
//        String token = userService.loginService(userDto);
//
//        if(token != null) {
//            HttpHeaders headers = new HttpHeaders();
//
//            headers.setBearerAuth(token);
//            signupResponse.setMassage("로그인 성공, JWT 생성이 완료되었습니다");
//            return ResponseEntity.status(200).headers(headers).body(signupResponse);
//
//        } else
//            signupResponse.setMassage("가입되지 않은 정보입니다.");
//
//        return ResponseEntity.status(404).body(signupResponse);
//
//        //return "로그인에 성공하였습니다.";
//    }
//
//    @PostMapping("/logout")
//    public ResponseDto logoutController(@RequestHeader("Token") String token){
//        String userLogout = userService.userLogout(token.substring(7));
//
//        ResponseDto response = new ResponseDto();
//        response.setMassage(userLogout);
//
//        return response;
//        //return "로그아웃 되었습니다!";
//    }
//
//}
