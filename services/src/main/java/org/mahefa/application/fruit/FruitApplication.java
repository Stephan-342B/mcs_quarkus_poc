package org.mahefa.application.fruit;

import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.mahefa.dto.FruitDTO;
import org.mahefa.dto.Pageable;

import java.util.List;

public interface FruitApplication {
    FruitDTO add(FruitDTO fruitDTO);
    FruitDTO update(FruitDTO fruitDTO);
    boolean delete(ObjectId fruitId);
    FruitDTO get(String name);
    List<FruitDTO> findAll();
    Pageable<FruitDTO> findAll(int page, int size);
    Pageable<FruitDTO> findAll(int page, int size, ObjectId id, Sort.Direction direction);
}
