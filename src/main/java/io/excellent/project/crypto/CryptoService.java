package io.excellent.project.crypto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Singleton
@AllArgsConstructor
@Slf4j
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    @Transactional
    public void save(@Valid CryptoEntity entity) {
        log.debug("[INSERT] inserting new entity");
        cryptoRepository.persistAndFlush(entity);
    }

// maybe change only if price changes
    @Transactional
    public void saveAll(@Valid List<CryptoEntity> entities) {
        for (CryptoEntity e : entities) {
            CryptoEntity old = cryptoRepository.findById(e.getId());
            if (old != null) {
                old.updateEntity(e);
                cryptoRepository.persistAndFlush(old);
            } else {
                cryptoRepository.persistAndFlush(e);
            }
        }
    }

    @Transactional
    public List<CryptoEntity> getAll() {
        return cryptoRepository.listAll();
    }
}
