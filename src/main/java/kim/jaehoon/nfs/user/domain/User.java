package kim.jaehoon.nfs.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Getter @Setter
@AllArgsConstructor
public class User {
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String userId;

    @Column(nullable = false, length = 100)
    private String password;

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
