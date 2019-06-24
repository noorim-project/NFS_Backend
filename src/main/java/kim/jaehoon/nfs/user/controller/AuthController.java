package kim.jaehoon.nfs.user.controller;

import kim.jaehoon.nfs.common.response.JwtToken;
import kim.jaehoon.nfs.common.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    @Qualifier("auth-service")
    private AuthService authService;

    @PostMapping("/signup")
    public RestResponse<TempUser> signup(@Valid @RequestBody SignupDTO dto) throws Exception {
        return new RestResponse.success(authService.signup(dto));
    }

    @PostMapping("/signup/confirm/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<JwtToken> confirm(@PathVariable String code) throws Exception {
        return RestResponse.success(authService.confirmSignup(code));
    }

    @PostMapping("/rest/password")
    @Transactional
    public RestResponse<?> resetPassword(@Valid @RequestBody SigninDTO dto) throws Exception {
        return RestResponse.success(authService.signin(dto));
    }

    @Get
}
