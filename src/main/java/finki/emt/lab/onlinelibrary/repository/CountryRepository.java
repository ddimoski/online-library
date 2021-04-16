package finki.emt.lab.onlinelibrary.repository;

import finki.emt.lab.onlinelibrary.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
