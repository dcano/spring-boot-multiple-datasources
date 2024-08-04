package io.twba.bca.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema="bca_context", name = "entity_bca")
public class EntityBcaJpa {

    @Id
    private String id;
    private String propA;
    private String propB;
    private String propC;

}
