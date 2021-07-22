package org.mahefa.business.fruit;

import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.mahefa.annotation.Service;
import org.mahefa.domain_object.Fruit;
import org.mahefa.repository.FruitRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitBusinessImpl implements FruitBusiness {

    @Inject
    FruitRepository fruitRepository;

    @Override
    public Fruit save(Fruit fruit) {
        fruit.setCreationDate(new Date().getTime());
        this.fruitRepository.persist(fruit);

        return this.findByName(fruit.getName());
    }

    @Override
    public Fruit update(Fruit fruit) {
        fruit.setModificationDate(new Date().getTime());
        this.fruitRepository.update(fruit);

        return fruit;
    }

    @Override
    public boolean delete(ObjectId fruitId) {
        try {
            this.fruitRepository.deleteById(fruitId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Fruit findByName(String name) {
        return this.fruitRepository.getByName(name);
    }

    @Override
    public List<Fruit> findAll() {
        return this.fruitRepository
                .streamAll()
                .collect(Collectors.toList());
    }

    @Override
    public List<Fruit> findAll(int page, int size) {
        return this.fruitRepository.streamAll().skip(page).limit(size).collect(Collectors.toList());
    }

    @Override
    public List<Fruit> keySetPagination(int size, Sort sort, ObjectId... id) {
        final String operator = (this.isInAscendingOrder(sort)) ? ">" : "<";
        PanacheQuery<Fruit> panacheQuery = fruitRepository.findAll(sort);

        if(id.length > 0 && id[0] != null) {
            final String query = String.format("_id %s ?1", operator);
            panacheQuery = fruitRepository.find(query, sort, id[0]);
        }

        return panacheQuery.page(Page.ofSize(size)).list();
    }

    @Override
    public long count() {
        return this.fruitRepository.count();
    }

    boolean isInAscendingOrder(Sort sort) {
        if(sort == null) {
            sort = Sort.by("_id", Sort.Direction.Ascending);
            return true;
        }

        final List<Sort.Column> columns = sort.getColumns();
        final Sort.Direction direction = columns.get(0).getDirection();

        return direction == Sort.Direction.Ascending;
    }
}
