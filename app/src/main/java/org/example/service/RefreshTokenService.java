package org.example.service;


import org.example.entites.RefershToken;
import org.example.entites.UserInfo;
import org.example.repository.RefreshTokenRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefershToken createRefreshToken(String userName) {

        UserInfo userInfo = userRepository.findByUsername(userName);

        if (userInfo != null) {
            RefershToken refershToken = RefershToken.builder().userInfo(userInfo).token(UUID.randomUUID().toString())
                    .expireDate(Instant.now().plusMillis(600000)).build();
            return refreshTokenRepository.save(refershToken);
        } else {
            return null;
        }
    }


    public RefershToken verifyExpiration(RefershToken refershToken) {

        if (refershToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refershToken);
            throw new RuntimeException(refershToken.getToken() + "Refresh token expired");
        }
        return refershToken;
    }

    public Optional<RefershToken>findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

}
