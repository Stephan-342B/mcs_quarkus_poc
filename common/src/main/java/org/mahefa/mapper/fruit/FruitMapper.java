package org.mahefa.mapper.fruit;

import org.mahefa.domain_object.Fruit;
import org.mahefa.dto.FruitDTO;
import org.mahefa.mapper.MapperFactory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface FruitMapper extends MapperFactory<Fruit, FruitDTO> {
}
