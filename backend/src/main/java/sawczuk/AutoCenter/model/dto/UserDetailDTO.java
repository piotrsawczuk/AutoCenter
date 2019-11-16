package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDTO {
    private String firstname;
    private String surname;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
}
