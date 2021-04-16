package finki.emt.lab.onlinelibrary.service;

import finki.emt.lab.onlinelibrary.model.Country;
import finki.emt.lab.onlinelibrary.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("No country with id %d found", id)));
    }

    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }
}
