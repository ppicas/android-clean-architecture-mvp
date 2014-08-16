package cat.ppicas.cleanarch.repository;

import java.util.List;

import cat.ppicas.cleanarch.domain.City;

public interface CityRepository {

    public City getCity(String cityId);

    public List<City> findCity(String name);
}
