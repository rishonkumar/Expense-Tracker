package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.Model.UserInfoDto;
import org.example.entites.RefershToken;
import org.example.response.JwtResponseDTO;
import org.example.service.JwtService;
import org.example.service.RefreshTokenService;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto) {

        try {
            Boolean isSignUp = userDetailsService.signUpUser(userInfoDto);

            if(Boolean.FALSE.equals(isSignUp)) {
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefershToken refershToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());

            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());

            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).token(refershToken.getToken())
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
