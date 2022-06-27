package projectOrganization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = RegisteredUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private RegisteredUser user;

    private Date expiryDate;
    public PasswordResetToken(RegisteredUser user, String token) {
        this.user = user;
        this.token = token;
        java.util.Date date = new java.util.Date();
        expiryDate = new Date(date.getTime() + EXPIRATION);
    }
}