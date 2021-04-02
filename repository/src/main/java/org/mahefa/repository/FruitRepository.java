package org.mahefa.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.mahefa.domain_object.Fruit;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitRepository implements PanacheMongoRepository<Fruit> {

    public Fruit findByName(String name){
        return find("name", name).firstResult();
    }

}
