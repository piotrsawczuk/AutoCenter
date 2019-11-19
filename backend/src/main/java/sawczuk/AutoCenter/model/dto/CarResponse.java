package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {
    private Long id;
    private Long carApiId;
    private String carName;
    private CarDetailResponse carDetail;
}
