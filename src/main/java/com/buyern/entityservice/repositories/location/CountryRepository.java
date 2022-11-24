package com.buyern.entityservice.repositories.location;

import com.buyern.entityservice.models.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select c from countries c order by c.name")
    List<Country> findByOrderByNameAsc();

    List<Country> findCountryByIso2(String countryCode);
}