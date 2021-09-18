package pl.sda.travel360.mapper;

import org.springframework.stereotype.Service;
import pl.sda.travel360.domain.Country;
import pl.sda.travel360.dto.CountryDTO;

@Service
public class CountryMapper {

    public CountryDTO mapToDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
    public Country mapToCountry (CountryDTO countryDTO){
        return Country.builder()
                .name(countryDTO.getName())
                .build();
    }
}
