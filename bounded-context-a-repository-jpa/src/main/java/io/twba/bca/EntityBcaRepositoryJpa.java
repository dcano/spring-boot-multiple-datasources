package io.twba.bca;

import io.twba.bca.db.EntityBcaJpa;
import io.twba.bca.db.EntityBcaRepositoryJpaHelper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class EntityBcaRepositoryJpa implements EntityBcaRepository {

    private final EntityBcaRepositoryJpaHelper helper;

    public EntityBcaRepositoryJpa(EntityBcaRepositoryJpaHelper helper) {
        this.helper = helper;
    }

    @Override
    public EntityBca save(EntityBca entityBca) {
        return toDomain(helper.save(toJpa(entityBca)));
    }

    @Override
    public EntityBca findOne(UUID id) {
        return toDomain(helper.findById(id.toString()).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public List<EntityBca> findAll() {
        return List.of();
    }

    private static EntityBca toDomain(EntityBcaJpa entityBcaJpa) {
        return new EntityBca(UUID.fromString(entityBcaJpa.getId()), entityBcaJpa.getPropA(), entityBcaJpa.getPropB(), entityBcaJpa.getPropC());
    }

    private static EntityBcaJpa toJpa(EntityBca entityBca) {
        EntityBcaJpa entity = new EntityBcaJpa();
        entity.setId(entityBca.getId().toString());
        entity.setPropA(entityBca.getPropA());
        entity.setPropB(entityBca.getPropB());
        entity.setPropC(entityBca.getPropC());
        return entity;
    }

}
