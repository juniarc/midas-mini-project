package dev.codejar.security.service;

import dev.codejar.model.entity.TokenEntity;
import dev.codejar.model.entity.enums.TokenType;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.repository.TokensRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TokenService {


    private final TokensRepository tokensRepository;


    public TokenService(TokensRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }

    public void saveUserTokens(UserEntity user, String accessToken, String refreshToken, boolean mandatory) {
        TokenEntity token = TokenEntity.builder()
                .user(user)
                .tokenType(TokenType.ACCESS) // token type
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresAt(LocalDateTime.now().plusMinutes(30)) // Access Token valid 30 menit
                .refreshExpiresAt(LocalDateTime.now().plusDays(7)) // Refresh Token valid 7 hari
                .isValid(true)
                .expired(false) //Set expired (default false)
                .revoked(!mandatory) //if mandatory = true, revoked = false
                .build();

        tokensRepository.save(token);
    }





    public Optional<TokenEntity> getToken(String jwtToken) {
        return tokensRepository.findByAccessToken(jwtToken);
    }

    public void revokeAllUserTokens(UserEntity user) {
        List<TokenEntity> validUserTokens = tokensRepository.findAllIsValidTokenByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> token.setRevoked(true)); // Revoke all token
            tokensRepository.saveAll(validUserTokens);
        }
    }

    public void expireToken(TokenEntity token) {
        token.setExpired(true);
        tokensRepository.save(token);
    }

    public Optional<TokenEntity> getValidRefreshToken(UserEntity user) {
        return tokensRepository.findFirstByUserAndTokenTypeAndRevokedFalseAndExpiredFalse(
                user, TokenType.REFRESH);
    }


}
