package kim.jaehoon.nfs.user.service;

import kim.jaehoon.nfs.common.exception.ConflictException;
import kim.jaehoon.nfs.common.exception.SendFailedException;
import kim.jaehoon.nfs.common.response.JwtToken;
import kim.jaehoon.nfs.common.security.jwt.Jwt;
import kim.jaehoon.nfs.email.EmailService;
import kim.jaehoon.nfs.user.domain.UserRepository;
import kim.jaehoon.nfs.user.domain.tempuser.TempUser;
import kim.jaehoon.nfs.user.domain.tempuser.TempUserRepository;
import kim.jaehoon.nfs.user.dto.PasswordResetDTO;
import kim.jaehoon.nfs.user.dto.SigninDTO;
import kim.jaehoon.nfs.user.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Jwt jwt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.prefix}")
    private String prefix;

    @PostConstruct
    private void init() {
        this.prefix += " ";
    }

    @Override
    public TempUser signup(@Valid @RequestBody SignupDTO dto) throws SendFailedException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("email", "email has conflicted");
        }
        tempUserRepository.deleteByEmail(dto.getEmail());
        tempUserRepository.flush();

        TempUser tempUser = tempUserRepository.save(dto.toTempUser(passwordEncoder));
        emailService.sendConfirmCode(dto.getEmail(), tempUser);

        return tempUser;
    }

    @Override
    public JwtToken confirmSignup(String code) throws Exception {
        return null;
    }

    @Override
    public JwtToken signin(SigninDTO dto) throws Exception {
        return null;
    }

    public void sendPasswordResetCode(String email) {

    }

    public void confirmPasswordResetCode(String email, String passwordResetCode) {

    }

    public void resetPassword(PasswordResetDTO dto) {

    }
}
