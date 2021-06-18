package org.mahefa.application.fruit;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.mahefa.annotation.Service;
import org.mahefa.business.fruit.FruitBusiness;
import org.mahefa.dto.FruitDTO;
import org.mahefa.domain_object.Fruit;
import org.mahefa.dto.PageableDTO;
import org.mahefa.mapper.fruit.FruitMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FruitApplicationImpl implements FruitApplication {

    @Inject
    FruitBusiness fruitBusiness;

    @Inject
    FruitMapper fruitMapper;

    @Override
    public FruitDTO add(FruitDTO fruitDTO) {
        Fruit fruit = this.fruitMapper.toDO(fruitDTO);
        fruit = this.fruitBusiness.save(fruit);

        return this.fruitMapper.toDTO(fruit);
    }

    @Override
    public FruitDTO update(FruitDTO fruitDTO) {
        Fruit fruit = this.fruitMapper.toDO(fruitDTO);
        fruit = this.fruitBusiness.update(fruit);

        return this.fruitMapper.toDTO(fruit);
    }

    @Override
    public boolean delete(ObjectId fruitId) {
        return this.fruitBusiness.delete((fruitId));
    }

    @Override
    public FruitDTO get(String name) {
        return this.fruitMapper.toDTO(this.fruitBusiness.findByName(name));
    }

    @Override
    public List<FruitDTO> findAll() {
        return this.fruitMapper.toDTOs(this.fruitBusiness.findAll());
    }

    @Override
    public PageableDTO<FruitDTO> findAll(int page, int size) {
        return Uni.combine().all()
                .unis(findAllFruits(page, size), count())
                .asTuple()
                .onItem()
                .apply(
                        tuple -> {
                            PageableDTO pageableDTO =  new PageableDTO();
                            pageableDTO.setPage(page);
                            pageableDTO.setSize(size);
                            pageableDTO.setData(tuple.getItem1());
                            pageableDTO.setCount(tuple.getItem2());
                            return  pageableDTO;
                        }).await().indefinitely();
    }

    private Uni<Long> count() {
        return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> fruitBusiness.count()));
    }

    private Uni<List<FruitDTO>> findAllFruits(int page, int size) {
        return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> fruitMapper.toDTOs(fruitBusiness.findAll(page, size))));
    }
}
