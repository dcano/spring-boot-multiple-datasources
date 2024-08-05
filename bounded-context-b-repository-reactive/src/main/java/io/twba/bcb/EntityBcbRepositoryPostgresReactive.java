package io.twba.bcb;

import io.twba.bcb.db.EntityBcbRepositoryReactiveHelper;
import io.twba.bcb.db.EntityBcbTable;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class EntityBcbRepositoryPostgresReactive implements EntityBcbRepository {

    private final EntityBcbRepositoryReactiveHelper helper;

    public EntityBcbRepositoryPostgresReactive(EntityBcbRepositoryReactiveHelper helper) {
        this.helper = helper;
    }

    @Override
    public Publisher<EntityBcb> save(EntityBcb entityBcb) {
        return Mono.just(entityBcb).map(EntityBcbRepositoryPostgresReactive::toTable).map(EntityBcbRepositoryPostgresReactive::toDomain);
    }

    @Override
    public Publisher<EntityBcb> findAll() {
        return helper.findAll().map(EntityBcbRepositoryPostgresReactive::toDomain);
    }

    @Override
    public Publisher<EntityBcb> findOne(UUID id) {
        return helper.findById(id.toString()).map(EntityBcbRepositoryPostgresReactive::toDomain);
    }

    private static EntityBcb toDomain(EntityBcbTable entityBcbTable) {
        return new EntityBcb(UUID.fromString(entityBcbTable.getId()), entityBcbTable.getPropA(), entityBcbTable.getPropB(), entityBcbTable.getPropC());
    }

    private static EntityBcbTable toTable(EntityBcb entityBcb) {
        EntityBcbTable entity = new EntityBcbTable();
        entity.setId(entityBcb.getId().toString());
        entity.setPropA(entityBcb.getPropA());
        entity.setPropB(entityBcb.getPropB());
        entity.setPropC(entityBcb.getPropC());
        return entity;
    }

}
