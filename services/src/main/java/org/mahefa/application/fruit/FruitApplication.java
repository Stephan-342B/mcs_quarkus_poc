package org.mahefa.application.fruit;

import org.bson.types.ObjectId;
import org.mahefa.dto.FruitDTO;
import org.mahefa.dto.PageableDTO;

import java.util.List;

public interface FruitApplication {

    FruitDTO add(FruitDTO fruitDTO);
    FruitDTO update(FruitDTO fruitDTO);
    boolean delete(ObjectId fruitId);
    FruitDTO get(String name);
    List<FruitDTO> findAll();
    PageableDTO<FruitDTO> findAll(int page, int size);
}
