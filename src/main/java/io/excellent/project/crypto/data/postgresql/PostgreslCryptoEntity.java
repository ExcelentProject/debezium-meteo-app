package io.excellent.project.crypto.data.postgresql;

import io.excellent.project.coincap.model.CryptoJson;
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
public class PostgreslCryptoEntity {
    public PostgreslCryptoEntity(CryptoJson json) {
        this(json.getId(), json.getSymbol(), json.getName(), json.getMarketCapUsd(),
                json.getPriceUsd(), json.getSupply());
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

    public PostgreslCryptoEntity() {
    }

    public void updateEntity(PostgreslCryptoEntity newEntity) {
        this.marketCapUsd = newEntity.getMarketCapUsd();
        this.priceUsd = newEntity.getPriceUsd();
        this.supply = newEntity.getSupply();
    }
}
