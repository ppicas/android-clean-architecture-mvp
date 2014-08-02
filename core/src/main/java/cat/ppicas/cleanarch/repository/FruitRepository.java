package cat.ppicas.cleanarch.repository;

import java.util.List;

import cat.ppicas.cleanarch.domain.Fruit;

public interface FruitRepository {

    public List<Fruit> getAllFruits();
}
