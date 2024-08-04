package io.twba.bca.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityBcaRepositoryJpaHelper extends JpaRepository<EntityBcaJpa, String> {
}
