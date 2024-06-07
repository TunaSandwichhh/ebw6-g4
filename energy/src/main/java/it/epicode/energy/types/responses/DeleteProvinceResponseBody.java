package it.epicode.energy.types.responses;
import it.epicode.energy.entities.Province;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProvinceResponseBody {
    private String message;
    private Province province;
}
