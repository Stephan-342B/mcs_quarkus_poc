package org.mahefa.business.fruit;

import org.mahefa.domain_object.Fruit;

import java.util.List;

public interface FruitBusiness {

    List<Fruit> save(Fruit fruit);

    List<Fruit> findAll();

    Fruit findByName(String name);

}
