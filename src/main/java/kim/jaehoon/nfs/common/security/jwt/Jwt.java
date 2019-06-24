package kim.jaehoon.nfs.common.security.jwt;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import kim.jaehoon.nfs.common.response.JwtToken;
import kim.jaehoon.nfs.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Jwt {

    @Value("TEST_ISSUER")
    private String issuer;

    @Value("TEST_ID")
    private String clientId;

    @Value("345600")
    private int exp;

    @Value("604800")
    private int refreshExp;

    @Value("${NFS_JWT_SECRET:needforspeed123}")
    private String secret;

    private JWTSigner jwtSigner;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void initPropertiesSetting() {
        jwtSigner = new JWTSigner(Base64.decodeBase64(secret));
        jwtVerifier = new JWTVerifier(Base64.decodeBase64(secret), clientId, issuer);
    }

    private JWTSigner.Options initSetting(Integer exp) {
        JWTSigner.Options options = new JWTSigner.Options();
        options.setAlgorithm(Algorithm.HS512);
        options.setJwtId(true);
        options.setIssuedAt(true);
        options.setExpirySeconds(exp);
        return options;
    }

    public JwtToken createToken(User user) throws Exception {
        String accessToken = createToken(user.getUserId());
        return new JwtToken(user.getEmail(), accessToken, createRefreshToken(accessToken));
    }

    public String createToken(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("iss", issuer);
        map.put("userId", userId);
        return jwtSigner.sign(map, initSetting(exp));
    }

    public String createRefreshToken(String token) throws Exception {
        Map<String, Object> claims = authToken(token);
        claims.remove("exp");
        claims.remove("iat");
        claims.remove("jti");
        return jwtSigner.sign(claims, initSetting(refreshExp));
    }

    public Map<String, Object> authToken(String token throws Exception) {
        return jwtVerifier.verify(token);
    }

    public boolean validation(String token) {
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) throws Exception {
        log.debug("jwtVerifier verifying token [{}]", token);
        String userId = (String) jwtVerifier.verify(token).get("userId");
        log.debug("Verifying succeed. UserId : {}", userId);
        return userId;
    }
}
