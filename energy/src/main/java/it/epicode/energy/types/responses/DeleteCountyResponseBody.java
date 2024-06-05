package it.epicode.energy.types.responses;

import it.epicode.energy.entities.County;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCountyResponseBody {
    private String message;
    private County county;
}
