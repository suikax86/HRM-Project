package da.hms.employeeservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String idNumber;
    private String taxNumber;
    private String address;
    private String phoneNumber;
    private String bankName;
    private String bankNumber;
    private double rewardPoints;

    public EmployeeDto() {
    }

    public EmployeeDto(Long Id, String name, String email, String role, String idNumber, String taxNumber, String address, String phoneNumber, String bankName, String bankNumber, double rewardPoints) {
        this.id = Id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.idNumber = idNumber;
        this.taxNumber = taxNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.rewardPoints = rewardPoints;
    }
}
