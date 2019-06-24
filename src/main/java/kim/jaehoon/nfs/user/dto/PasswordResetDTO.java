package kim.jaehoon.nfs.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PasswordResetDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String passwordResetCode;

    @NotNull
    private String password;
}
