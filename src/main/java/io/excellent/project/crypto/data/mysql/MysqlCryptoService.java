package io.excellent.project.crypto.data.postgresql;

import io.excellent.project.crypto.data.mysql.MysqlCryptoEntity;
import io.excellent.project.crypto.data.mysql.MysqlRepository;
import io.quarkus.hibernate.orm.PersistenceUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Singleton
@AllArgsConstructor
@Slf4j
public class MysqlCryptoService {
    private final MysqlRepository mysqlRepository;

    @Transactional
    public void save(@Valid MysqlCryptoEntity entity) {
        log.debug("[INSERT] inserting new entity");
        mysqlRepository.persistAndFlush(entity);
    }

    // maybe change only if price changes
    @Transactional
    public void saveAll(@Valid List<MysqlCryptoEntity> entities) {
        log.info("[MYSQL] saving the entities");
        for (MysqlCryptoEntity e : entities) {
            MysqlCryptoEntity old = mysqlRepository.findById(e.getId());
            if (old != null) {
                old.updateEntity(e);
                mysqlRepository.persistAndFlush(old);
            } else {
                mysqlRepository.persistAndFlush(e);
            }
        }
    }

    @Transactional
    public List<MysqlCryptoEntity> getAll() {
        return mysqlRepository.listAll();
    }
}
