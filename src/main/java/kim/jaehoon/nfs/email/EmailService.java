package kim.jaehoon.nfs.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import kim.jaehoon.nfs.common.exception.SendFailedException;
import kim.jaehoon.nfs.user.domain.tempuser.TempUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;

@EnableAsync
@Service
@Profile({"prod"})
public class EmailService {

    @Autowired
    SendGrid sendGrid;

    @Value("${baseUrl}")
    private String baseUrl;

    @Async
    public void sendEmail(String to, String title, String body) {
        Mail mail = new Mail(
            new Email("admin@noorim.jaehoon.kim"), title, new Email(to), new Content("text/plain", body)
        );

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendConfirmCode(String email, TempUser tempUser) {
        sendEmail(email, "[Noorim For Service] NFS 회원가입 인증 링크입니다.",
                "링크를 클릭하여 인증을 완료하세요.\n" + String.format("%s/%s", baseUrl, tempUser.getCode()));
    }
}
