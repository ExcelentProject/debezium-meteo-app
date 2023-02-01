package io.excellent.project.coincap.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CryptoJsonPage {
    private List<CryptoJson> data;
    private Double timestamp;
}
