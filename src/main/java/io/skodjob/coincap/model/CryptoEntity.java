package io.skodjob.coincap.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coincap")
public class CryptoEntity {

    @Id
    private String id;
    private Integer rank;
    private String symbol;
    private String name;

    private Double supply;
    private Double maxSupply;
    private Double marketCapUsd;

    @Column(name = "volumeDay")
    private Double volumeUsd24Hr;

    private Double priceUsd;

    @Column(name = "changePercent")
    private Double changePercent24Hr;

    @Column(name = "vwmap")
    private Double vwap24Hr;


    public void update(CryptoEntity newState) {
        this.rank = newState.rank;
        this.symbol = newState.symbol;
        this.name = newState.name;
        this.supply = newState.supply;
        this.maxSupply = newState.maxSupply;
        this.marketCapUsd = newState.marketCapUsd;
        this.volumeUsd24Hr = newState.volumeUsd24Hr;
        this.priceUsd = newState.priceUsd;
        this.changePercent24Hr = newState.changePercent24Hr;
        this.vwap24Hr = newState.vwap24Hr;
    }
}
