package org.mahefa.application.fruit;

import org.mahefa.annotation.Service;
import org.mahefa.business.fruit.FruitBusiness;
import org.mahefa.data_transfert_object.FruitDTO;
import org.mahefa.domain_object.Fruit;
import org.mahefa.mapper.fruit.FruitMapper;

import javax.inject.Inject;
import java.util.List;

@Service
public class FruitApplicationImpl implements FruitApplication {

    @Inject
    FruitBusiness fruitBusiness;

    @Inject
    FruitMapper fruitMapper;

    @Override
    public List<FruitDTO> add(FruitDTO fruitDTO) {
        Fruit fruit = this.fruitMapper.toDO(fruitDTO);
        List<Fruit> fruits = this.fruitBusiness.save(fruit);

        return this.fruitMapper.toDTOs(fruits);
    }

    @Override
    public List<FruitDTO> findAll() {
        return this.fruitMapper.toDTOs(
                this.fruitBusiness.findAll()
        );
    }

    @Override
    public FruitDTO get(String name) {
        return this.fruitMapper.toDTO(
                this.fruitBusiness.findByName(name)
        );
    }
}
