package cat.ppicas.cleanarch.rest;

import android.os.SystemClock;

import java.util.Arrays;
import java.util.List;

import cat.ppicas.cleanarch.domain.Fruit;
import cat.ppicas.cleanarch.repository.FruitRepository;

public class RestFruitRepository implements FruitRepository {

    @Override
    public List<Fruit> getAllFruits() {
        SystemClock.sleep(3000);
        return Arrays.asList(
                createFruit("Pear"),
                createFruit("Apple"),
                createFruit("Peach")
        );
    }

    private Fruit createFruit(String name) {
        Fruit fruit = new Fruit();
        fruit.setName(name);
        return fruit;
    }
}
