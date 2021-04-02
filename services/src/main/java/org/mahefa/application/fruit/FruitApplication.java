package org.mahefa.application.fruit;

import org.mahefa.data_transfert_object.FruitDTO;

import java.util.List;

public interface FruitApplication {

    List<FruitDTO> add(FruitDTO fruitDTO);

    List<FruitDTO> findAll();

    FruitDTO get(String name);

}
