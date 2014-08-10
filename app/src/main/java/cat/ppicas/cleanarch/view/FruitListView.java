package cat.ppicas.cleanarch.view;

import java.util.List;

import cat.ppicas.cleanarch.domain.Fruit;

public interface FruitListView extends View {

    void showFruits(List<Fruit> fruits);
}
