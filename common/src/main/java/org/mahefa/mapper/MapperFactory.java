package org.mahefa.mapper;

import org.mahefa.dto.ObjectDTO;
import org.mahefa.dto.Pageable;

import java.util.List;

/**
 *
 * @param <T> Entity
 * @param <E> DTO class
 */
public interface MapperFactory<T, E extends ObjectDTO> {

    T toDO(E entity);
    List<T> toDO(List<E> entityListDto);
    E toDTO(T entity);
    List<E> toDTO(List<T> entityList);

    default Pageable<E> toPageableDTO(List<T> data, long count, int page, int size) {
        return new Pageable(toDTO(data), count, page, size);
    }
}
