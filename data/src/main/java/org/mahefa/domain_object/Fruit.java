package org.mahefa.domain_object;

import io.quarkus.mongodb.panache.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MongoEntity(collection="fruit")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Fruit extends Entity {
    private String name;
    private String description;
    private Long creationDate;
    private Long modificationDate;
}
