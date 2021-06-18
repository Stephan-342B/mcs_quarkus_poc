package org.mahefa.business.fruit;

import org.bson.types.ObjectId;
import org.mahefa.domain_object.Fruit;

import java.util.List;

public interface FruitBusiness {
    Fruit save(Fruit fruit);
    Fruit update(Fruit fruit);
    boolean delete(ObjectId fruitId);
    Fruit findByName(String name);
    List<Fruit> findAll();
    List<Fruit> findAll(int page, int size);
    long count();
}
