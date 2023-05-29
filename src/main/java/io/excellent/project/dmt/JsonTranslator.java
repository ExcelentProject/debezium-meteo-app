package io.excellent.project.dmt;

import io.debezium.performance.dmt.schema.DatabaseColumnEntry;
import io.debezium.performance.dmt.schema.DatabaseEntry;
import io.excellent.project.coincap.model.CryptoJson;
import io.excellent.project.coincap.model.CryptoJsonPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTranslator {

    public List<DatabaseEntry> translate(CryptoJsonPage cryptoJsonPage) {
        List<DatabaseEntry> rows = new ArrayList<>();

        for (CryptoJson cryptoJson: cryptoJsonPage.getData()) {
            DatabaseEntry jsonSchema = new DatabaseEntry();

            DatabaseColumnEntry id = new DatabaseColumnEntry();
            id.setDataType("VarChar(255)");
            id.setColumnName("id");
            id.setValue(cryptoJson.getId());

            DatabaseColumnEntry rank = new DatabaseColumnEntry();
            rank.setDataType("Integer");
            rank.setColumnName("rankino");
            rank.setValue(cryptoJson.getRank().toString());

            DatabaseColumnEntry symbol = new DatabaseColumnEntry();
            symbol.setDataType("VarChar(255)");
            symbol.setColumnName("symbol");
            symbol.setValue(cryptoJson.getSymbol());

            DatabaseColumnEntry name = new DatabaseColumnEntry();
            name.setDataType("VarChar(255)");
            name.setColumnName("name");
            name.setValue(cryptoJson.getName());

            DatabaseColumnEntry supply = new DatabaseColumnEntry();
            supply.setDataType("Double");
            supply.setColumnName("supply");
            supply.setValue(cryptoJson.getSupply().toString());

            DatabaseColumnEntry marketCapUsd = new DatabaseColumnEntry();
            marketCapUsd.setDataType("Double");
            marketCapUsd.setColumnName("marketCapUsd");
            marketCapUsd.setValue(cryptoJson.getMarketCapUsd().toString());

            jsonSchema.setName("crypto");
            jsonSchema.setPrimary("id");
            jsonSchema.setColumnEntries(Arrays.asList(id, rank, symbol, name, supply, marketCapUsd));
            rows.add(jsonSchema);
        }

        return rows;
    }
}
