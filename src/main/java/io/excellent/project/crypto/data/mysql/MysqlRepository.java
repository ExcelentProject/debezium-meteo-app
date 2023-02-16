package io.excellent.project.crypto.data.mysql;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MysqlRepository implements PanacheRepositoryBase<MysqlCryptoEntity, String> {
}
