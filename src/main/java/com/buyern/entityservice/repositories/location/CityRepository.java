package com.buyern.entityservice.repositories.location;

import com.buyern.entityservice.models.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    @Override
    Optional<City> findById(Long aLong);

    List<City> findAllByCountryIdOrderByName(Long countryId);

    List<City> findAllByCountryCodeOrderByName(String countryCode);

    List<City> findAllByStateIdOrderByName(Long StateId);

    List<City> findAllByCountryCodeAndStateCode(String countryCode, String stateCode);
}