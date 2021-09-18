package pl.sda.travel360.contoller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.travel360.dto.CountryDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetCountriesResponse {

    private List<CountryDTO> countries;
}
