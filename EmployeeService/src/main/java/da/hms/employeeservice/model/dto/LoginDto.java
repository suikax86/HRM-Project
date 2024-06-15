package da.hms.employeeservice.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String employeeId;
    private String password;
}