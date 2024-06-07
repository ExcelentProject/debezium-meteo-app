package io.skodjob.coincap;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.skodjob.coincap.model.CryptoEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoinCapRepository implements PanacheRepository<CryptoEntity> {

    public void upsertAll(List<CryptoEntity> entities) {
        for (CryptoEntity entity : entities) {
            CryptoEntity currentEntity = find("id", entity.getId()).stream().findFirst().orElse(null);
            if (currentEntity != null) {
                currentEntity.update(entity);
                persist(currentEntity);
            } else {
                persist(entity);
            }
        }
    }
}
