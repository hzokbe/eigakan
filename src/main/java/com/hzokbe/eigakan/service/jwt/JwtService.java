package com.hzokbe.eigakan.service.jwt;

import com.hzokbe.eigakan.model.jwt.JwtResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public JwtResponse generateJwt(Authentication authentication) {
        var issuedAt = Instant.now();

        var expiresAt = issuedAt.plusSeconds(3600);

        var claims = JwtClaimsSet
                .builder()
                .issuer("eigakan")
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject(authentication.getName())
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();

        return new JwtResponse(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
