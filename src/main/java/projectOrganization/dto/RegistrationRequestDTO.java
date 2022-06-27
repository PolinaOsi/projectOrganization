package projectOrganization.dto;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String mail;
    private String password;
    private String passwordConfirm;
}