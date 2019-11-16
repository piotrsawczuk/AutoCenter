package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDetailDTO {
    private String vin;
    private String licencePlateNumber;
    private String color;
    private String imageUrl;
}
