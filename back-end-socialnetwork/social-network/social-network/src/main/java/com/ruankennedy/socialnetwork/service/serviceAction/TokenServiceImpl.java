package com.ruankennedy.socialnetwork.service.serviceAction;



import com.ruankennedy.socialnetwork.dto.response.RoleDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ruankennedy.socialnetwork.util.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Service
@Primary
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    private final UserMapper userMapper;

    public String generateToken(Authentication authentication) { 

        UserMonitoringDTO userDTO = userMapper.toUserMonitoringDTO((User) authentication.getPrincipal());

        List<String> roles = convertFromObjectListToStringList(userDTO.getRolesDTO()); 

        try {
            return JWT.create()
                    .withIssuer("SecurityStandard API")
                    .withSubject(userDTO.getId())
                    .withClaim("email", userDTO.getEmail())
                    .withClaim("roles", roles)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationInstant())
                    .sign(secretAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Generating jwt token error", exception);
        }
    }

    public String getSubject(String token) {
        try {
            return JWT.require(secretAlgorithm())
                    .withIssuer("SecurityStandard API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (RuntimeException exception) {
            throw new RuntimeException("JWT Token invalid or expired!");
        }
    }

    private List<String> convertFromObjectListToStringList(List<RoleDTO> roles) {
        return roles.stream().map(String::valueOf).toList();
    }

    private Instant expirationInstant() {
        return LocalDateTime.now().plusHours(Long.parseLong(this.expiration))
                .toInstant(ZoneOffset.of("-03:00"));

    }

    private Algorithm secretAlgorithm () {
        return  Algorithm.HMAC256(secret);
    }

}
