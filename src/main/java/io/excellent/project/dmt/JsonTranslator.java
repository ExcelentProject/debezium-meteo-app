package io.excellent.project.dmt;

import io.excellent.project.coincap.model.CryptoJson;
import io.excellent.project.coincap.model.CryptoJsonPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonTranslator {

    public List<JsonSchema> translate(CryptoJsonPage cryptoJsonPage, List<String> databases) {
        List<JsonSchema> rows = new ArrayList<>();

        for (CryptoJson cryptoJson: cryptoJsonPage.getData()) {
            JsonSchema jsonSchema = new JsonSchema();

            JsonTableAttribute id = new JsonTableAttribute();
            id.setDataType("VarChar(255)");
            id.setName("id");
            id.setValue(cryptoJson.getId());

            JsonTableAttribute rank = new JsonTableAttribute();
            rank.setDataType("Integer");
            rank.setName("rankino");
            rank.setValue(cryptoJson.getRank().toString());

            JsonTableAttribute symbol = new JsonTableAttribute();
            symbol.setDataType("VarChar(255)");
            symbol.setName("symbol");
            symbol.setValue(cryptoJson.getSymbol());

            JsonTableAttribute name = new JsonTableAttribute();
            name.setDataType("VarChar(255)");
            name.setName("name");
            name.setValue(cryptoJson.getName());

            JsonTableAttribute supply = new JsonTableAttribute();
            supply.setDataType("Double");
            supply.setName("supply");
            supply.setValue(cryptoJson.getSupply().toString());

            JsonTableAttribute marketCapUsd = new JsonTableAttribute();
            marketCapUsd.setDataType("Double");
            marketCapUsd.setName("marketCapUsd");
            marketCapUsd.setValue(cryptoJson.getMarketCapUsd().toString());

            jsonSchema.setDatabases(databases);
            jsonSchema.setTable("crypto");
            jsonSchema.setPrimary("id");
            jsonSchema.setPayload(Arrays.asList(id, rank, symbol, name, supply, marketCapUsd));
            rows.add(jsonSchema);
        }

        return rows;
    }
}
