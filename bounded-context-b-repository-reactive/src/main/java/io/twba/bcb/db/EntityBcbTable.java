package io.twba.bcb.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema="bcb_context", name = "entity_bcb")
@Data
public class EntityBcbTable {

    @Id
    public String id;
    private String propA;
    private String propB;
    private String propC;


}
