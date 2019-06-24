package kim.jaehoon.nfs.user.service;

import kim.jaehoon.nfs.common.response.JwtToken;
import kim.jaehoon.nfs.user.domain.tempuser.TempUser;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthService {

    TempUser signup(@Valid @RequestBody SignupDTO dto) throws SendFailedException;

    JwtToken confirmSignup(String code) throws Exception;

    JwtToken signin(SigninDTO dto) throws Exception;

    void sendPasswordResetCode(String email);

    void confirmPasswordResetCode(String email, String passwordResetCode);

    void resetPassword(PasswordResetDTO dto);
}
