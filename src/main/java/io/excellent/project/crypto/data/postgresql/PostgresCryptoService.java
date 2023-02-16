package io.excellent.project.crypto.data.postgresql;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Singleton
@AllArgsConstructor
@Slf4j
public class PostgresCryptoService {
    private final PostgresRepository postgresRepository;

    @Transactional
    public void save(@Valid PostgreslCryptoEntity entity) {
        log.debug("[INSERT] inserting new entity");
        postgresRepository.persistAndFlush(entity);
    }

    @Transactional
    public void saveAll(@Valid List<PostgreslCryptoEntity> entities) {
        log.info("[POSTGRESQL] saving the entities");
        for (PostgreslCryptoEntity e : entities) {
            PostgreslCryptoEntity old = postgresRepository.findById(e.getId());
            if (old != null) {
                old.updateEntity(e);
                postgresRepository.persistAndFlush(old);
            } else {
                postgresRepository.persistAndFlush(e);
            }
        }
    }

    @Transactional
    public List<PostgreslCryptoEntity> getAll() {
        return postgresRepository.listAll();
    }
}
