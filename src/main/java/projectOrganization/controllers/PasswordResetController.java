package projectOrganization.controllers;

import projectOrganization.entity.PasswordResetToken;
import projectOrganization.entity.RegisteredUser;
import projectOrganization.exceptions.UserNotFoundException;
import projectOrganization.repository.PasswordTokenRepository;
import projectOrganization.dto.ChangePasswordRequest;
import projectOrganization.services.MailSender;
import projectOrganization.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.UUID;

@RestController
@RequestMapping("/resetPassword")
public class PasswordResetController {
    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @PostMapping
    public ResponseEntity<?> resetPassword(@RequestBody String email) {
        try {
            RegisteredUser user = userService.findUserByMail(email);
            if (user == null) {
                throw new UserNotFoundException("Cannot find user with this mail");
            }
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailSender.sendMailResetPassword("", token, user);
            return ResponseEntity.ok("Password reseted");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        /*return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));*/
    }
    @PostMapping("/new")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String result = validatePasswordResetToken(request.getToken());
            PasswordResetToken passToken = passwordTokenRepository.findByToken(request.getToken());
            System.out.println(request.getToken());
            System.out.println(passToken);
            if (result != null) {
                userService.changeUserPassword(passToken.getUser(), request.getPassword());
                return ResponseEntity.ok("Password changed");
            }
            else {
                return ResponseEntity.ok("Password didn't changed");
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}