package io.twba.bca;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class EntityBca {

    private UUID id;
    private String propA;
    private String propB;
    private String propC;

}
