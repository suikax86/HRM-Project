package da.hms.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Random;

@Entity
@Data
public class Employee {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

//    Căn cước công dân
    @Column(unique = true, nullable = false)
    private String idNumber;

//  Mã số thuế cá nhân
    @Column(nullable = false)
    private String taxNumber;

    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String bankNumber;

    public Employee() {
    }

    public Employee(String name, String email, String idNumber, String taxNumber, String address, String phoneNumber, String bankNumber) {
        this.name = name;
        this.email = email;
        this.idNumber = idNumber;
        this.taxNumber = taxNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankNumber = bankNumber;
    }

    @PrePersist
    public void prePersist() {
        Random rnd = new Random();
        this.id = Integer.toString( 10000 + rnd.nextInt(90000)); // Generate a random 5-digit number)

    }

//    Tạo relation ship giữa Employee và UserEntity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}
