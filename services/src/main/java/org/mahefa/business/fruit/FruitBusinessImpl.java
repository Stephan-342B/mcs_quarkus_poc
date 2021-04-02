package org.mahefa.business.fruit;

import org.mahefa.annotation.Service;
import org.mahefa.domain_object.Fruit;
import org.mahefa.repository.FruitRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitBusinessImpl implements FruitBusiness {

    @Inject
    FruitRepository fruitRepository;

    @Override
    public List<Fruit> save(Fruit fruit) {
        this.fruitRepository.persist(fruit);

        return this.findAll();
    }

    @Override
    public List<Fruit> findAll() {
        return this.fruitRepository
                .streamAll()
                .collect(Collectors.toList());
    }

    @Override
    public Fruit findByName(String name) {
        return this.fruitRepository.findByName(name);
    }
}
