package io.twba.bcb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EntityBcb {

    private UUID id;
    private String propA;
    private String propB;
    private String propC;

}
