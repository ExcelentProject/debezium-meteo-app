package io.excellent.project.coincap.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoJson {
    private String id;
    private Integer rank;
    private String symbol;
    private String name;

    private Double supply;
    private Double maxSupply;
    private Double marketCapUsd;
    private Double volumeUsd24Hr;

    private Double priceUsd;
    private Double changePercent24Hr;
    private Double vwap24Hr;
}
