package kim.jaehoon.nfs.user.dto;

import kim.jaehoon.nfs.user.domain.tempuser.TempUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SignupDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    public TempUser toTempUser(PasswordEncoder passwordEncoder) {
        return new TempUser(email, passwordEncoder.encode(password));
    }

    public SignupDTO(@NotNull @Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }
}
