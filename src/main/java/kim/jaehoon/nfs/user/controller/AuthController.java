package kim.jaehoon.nfs.user.controller;

import kim.jaehoon.nfs.common.response.JwtToken;
import kim.jaehoon.nfs.common.response.RestResponse;
import kim.jaehoon.nfs.user.domain.tempuser.TempUser;
import kim.jaehoon.nfs.user.dto.SigninDTO;
import kim.jaehoon.nfs.user.dto.SignupDTO;
import kim.jaehoon.nfs.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public RestResponse<TempUser> signup(@Valid @RequestBody SignupDTO dto) throws Exception {
        return RestResponse.success(authService.signup(dto));
    }

    @GetMapping("/signup/confirm/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<JwtToken> confirm(@PathVariable String code) throws Exception {
        return RestResponse.success(authService.confirmSignup(code));
    }

    @PostMapping("/reset/password")
    public RestResponse<?> resetPassword(@Valid @RequestBody SigninDTO dto) throws Exception {
        return RestResponse.success(authService.signin(dto));
    }
}
