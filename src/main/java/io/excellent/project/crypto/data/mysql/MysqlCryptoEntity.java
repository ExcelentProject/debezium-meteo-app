package io.excellent.project.crypto.data.mysql;

import io.excellent.project.coincap.model.CryptoJson;
import io.excellent.project.crypto.data.postgresql.PostgreslCryptoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "Crypto")
@Table(name = "crypto")
@AllArgsConstructor
@Data
public class MysqlCryptoEntity {
    public MysqlCryptoEntity(CryptoJson json) {
        this(json.getId(), json.getSymbol(), json.getName(), json.getMarketCapUsd(),
                json.getPriceUsd(), json.getSupply());
    }

    public MysqlCryptoEntity(PostgreslCryptoEntity e) {
        this(e.getId(), e.getSymbol(), e.getName(), e.getMarketCapUsd(),
                e.getPriceUsd(), e.getSupply());
    }
    @Id
    @NotNull
    private String id;
    @NotEmpty
    private String symbol;

    @NotEmpty
    private String name;

    @NotNull
    @Column(name = "market_cap_usd")
    private Double marketCapUsd;

    @NotNull
    @Column(name = "price_usd")
    private Double priceUsd;

    @NotNull
    private Double supply;

    public MysqlCryptoEntity() {
    }

    public void updateEntity(MysqlCryptoEntity newEntity) {
        this.marketCapUsd = newEntity.getMarketCapUsd();
        this.priceUsd = newEntity.getPriceUsd();
        this.supply = newEntity.getSupply();
    }
}
