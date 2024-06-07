package io.skodjob.coincap.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CryptoJsonPage {
    private List<CryptoEntity> data;
    private Double timestamp;
}
