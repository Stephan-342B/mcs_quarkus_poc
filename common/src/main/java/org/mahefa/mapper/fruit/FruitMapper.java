package org.mahefa.mapper.fruit;

import org.mahefa.data_transfert_object.FruitDTO;
import org.mahefa.domain_object.Fruit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FruitMapper {

    FruitMapper INSTANCE = Mappers.getMapper(FruitMapper.class);

    Fruit toDO(FruitDTO fruitDTO);

    FruitDTO toDTO(Fruit fruit);

    List<Fruit> toDOs(List<FruitDTO> fruitDTOs);

    List<FruitDTO> toDTOs(List<Fruit> fruits);

}
