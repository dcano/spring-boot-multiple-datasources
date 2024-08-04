package io.twba.bcb;

import org.reactivestreams.Publisher;

import java.util.UUID;

public class EntityBcbRepositoryPostgresReactive implements EntityBcbRepository {

    @Override
    public Publisher<EntityBcb> save(EntityBcb entityBcb) {
        return null;
    }

    @Override
    public Publisher<EntityBcb> findAll() {
        return null;
    }

    @Override
    public Publisher<EntityBcb> findOne(UUID id) {
        return null;
    }
}
