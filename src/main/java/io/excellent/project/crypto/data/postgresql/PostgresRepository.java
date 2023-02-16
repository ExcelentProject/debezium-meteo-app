package io.excellent.project.crypto.data.postgresql;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostgresRepository implements PanacheRepositoryBase<PostgreslCryptoEntity, String> {
}
