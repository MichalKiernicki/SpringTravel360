package pl.sda.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.travel360.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
