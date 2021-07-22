package org.mahefa.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Pageable<T> {
    private List<T> data;
    private long count;
    private int page;
    private int size;
}
