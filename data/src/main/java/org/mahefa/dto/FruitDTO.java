package org.mahefa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FruitDTO {
    private ObjectId id;
    private String name;
    private String description;
    private Long creationDate;
    private Long modificationDate;
}
