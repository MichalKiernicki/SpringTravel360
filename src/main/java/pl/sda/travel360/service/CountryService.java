package pl.sda.travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.travel360.domain.Country;
import pl.sda.travel360.dto.CountryDTO;
import pl.sda.travel360.mapper.CountryMapper;
import pl.sda.travel360.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    public List<CountryDTO> getAllCountries() {
        log.info("Get countries");
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    public void addCountry(CountryDTO countryDTO) {
        log.info("add country: {}", countryDTO);
        var country = countryMapper.mapToCountry(countryDTO);
        countryRepository.save(country);
    }
}
