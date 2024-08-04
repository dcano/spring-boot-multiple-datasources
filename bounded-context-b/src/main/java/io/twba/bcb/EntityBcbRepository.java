package io.twba.bcb;

import org.reactivestreams.Publisher;

import java.util.UUID;

public interface EntityBcbRepository {

    Publisher<EntityBcb> save(EntityBcb entityBcb);
    Publisher<EntityBcb> findAll();
    Publisher<EntityBcb> findOne(UUID id);

}
