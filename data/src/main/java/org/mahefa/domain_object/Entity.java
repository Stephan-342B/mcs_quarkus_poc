package org.mahefa.domain_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Entity {
    private ObjectId id;
}
