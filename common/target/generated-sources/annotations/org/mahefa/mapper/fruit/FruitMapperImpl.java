package org.mahefa.mapper.fruit;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;
import org.mahefa.data_transfert_object.FruitDTO;
import org.mahefa.domain_object.Fruit;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-02T20:23:57+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.10 (Ubuntu)"
)
@ApplicationScoped
public class FruitMapperImpl implements FruitMapper {

    @Override
    public Fruit toDO(FruitDTO fruitDTO) {
        if ( fruitDTO == null ) {
            return null;
        }

        Fruit fruit = new Fruit();

        fruit.setName( fruitDTO.getName() );
        fruit.setDescription( fruitDTO.getDescription() );

        return fruit;
    }

    @Override
    public FruitDTO toDTO(Fruit fruit) {
        if ( fruit == null ) {
            return null;
        }

        FruitDTO fruitDTO = new FruitDTO();

        fruitDTO.setName( fruit.getName() );
        fruitDTO.setDescription( fruit.getDescription() );

        return fruitDTO;
    }

    @Override
    public List<Fruit> toDOs(List<FruitDTO> fruitDTOs) {
        if ( fruitDTOs == null ) {
            return null;
        }

        List<Fruit> list = new ArrayList<Fruit>( fruitDTOs.size() );
        for ( FruitDTO fruitDTO : fruitDTOs ) {
            list.add( toDO( fruitDTO ) );
        }

        return list;
    }

    @Override
    public List<FruitDTO> toDTOs(List<Fruit> fruits) {
        if ( fruits == null ) {
            return null;
        }

        List<FruitDTO> list = new ArrayList<FruitDTO>( fruits.size() );
        for ( Fruit fruit : fruits ) {
            list.add( toDTO( fruit ) );
        }

        return list;
    }
}
