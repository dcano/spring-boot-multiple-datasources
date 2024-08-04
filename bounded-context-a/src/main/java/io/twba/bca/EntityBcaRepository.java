package io.twba.bca;

import java.util.List;
import java.util.UUID;

public interface EntityBcaRepository {

    EntityBca save(EntityBca entityBca);
    EntityBca findOne(UUID id);
    List<EntityBca> findAll();

}
