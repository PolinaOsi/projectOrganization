package projectOrganization.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String token;
    String password;
}