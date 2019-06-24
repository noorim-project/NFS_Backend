package kim.jaehoon.nfs.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.FORBIDDEN, reason = "cannot find match user info")
public class UnauthorizedException extends RuntimeException {
}
