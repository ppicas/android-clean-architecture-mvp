package cat.ppicas.cleanarch.task;

import java.util.List;

import cat.ppicas.cleanarch.domain.Fruit;
import cat.ppicas.cleanarch.repository.FruitRepository;

public class ListFruitsTask implements Task<List<Fruit>> {

    private FruitRepository mRepository;

    public ListFruitsTask(FruitRepository repository) {
        mRepository = repository;
    }

    @Override
    public List<Fruit> execute() throws Exception {
        return mRepository.getAllFruits();
    }
}
