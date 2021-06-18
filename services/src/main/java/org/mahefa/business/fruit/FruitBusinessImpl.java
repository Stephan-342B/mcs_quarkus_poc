package org.mahefa.business.fruit;

import org.bson.types.ObjectId;
import org.mahefa.annotation.Service;
import org.mahefa.domain_object.Fruit;
import org.mahefa.repository.FruitRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitBusinessImpl implements FruitBusiness {

    @Inject
    FruitRepository fruitRepository;

    @Override
    public Fruit save(Fruit fruit) {
        fruit.setCreationDate(new Date().getTime());
        this.fruitRepository.persist(fruit);

        return this.findByName(fruit.getName());
    }

    @Override
    public Fruit update(Fruit fruit) {
        fruit.setModificationDate(new Date().getTime());
        this.fruitRepository.update(fruit);

        return fruit;
    }

    @Override
    public boolean delete(ObjectId fruitId) {
        if(fruitId == null) {
            return false;
        }

        this.fruitRepository.deleteById(fruitId);
        return true;
    }

    @Override
    public Fruit findByName(String name) {
        return this.fruitRepository.getByName(name);
    }

    @Override
    public List<Fruit> findAll() {
        return this.fruitRepository
                .streamAll()
                .collect(Collectors.toList());
    }

    @Override
    public List<Fruit> findAll(int page, int size) {
        return this.fruitRepository.streamAll().skip(page).limit(size).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return this.fruitRepository.count();
    }
}
