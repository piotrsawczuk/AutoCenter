package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDetailRequest {
    private String vin;
    private String licencePlateNumber;
    private String color;
    private String imageUrl;
}
