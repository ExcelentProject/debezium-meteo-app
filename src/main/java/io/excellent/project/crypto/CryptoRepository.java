package io.excellent.project.crypto;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CryptoRepository implements PanacheRepositoryBase<CryptoEntity, String> {
}
