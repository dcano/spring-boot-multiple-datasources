package io.twba.bcb.db;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityBcbRepositoryReactiveHelper extends R2dbcRepository<EntityBcbTable, String> {
}
