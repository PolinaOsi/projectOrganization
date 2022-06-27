package projectOrganization.services;

import projectOrganization.entity.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, RegisteredUser user) {
        String url = "Ваш токен: " + token;
        String message = "";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             RegisteredUser user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getMail());
        //email.setFrom(env.getProperty("support.email"));
        return email;
    }
    public void sendMailResetPassword(String contextPath, String token, RegisteredUser user) {
        javaMailSender.send(constructResetTokenEmail(contextPath, token, user));
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void sendMailPassword(RegisteredUser user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Your password");
        email.setText(user.getPassword());
        email.setTo(user.getMail());
    }
}