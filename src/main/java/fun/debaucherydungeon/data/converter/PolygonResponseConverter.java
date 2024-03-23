package fun.debaucherydungeon.data.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fun.debaucherydungeon.asset.TradingData;

public class PolygonResponseConverter {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PolygonResponseConverter() {}

    public TradingData convert(String json) {
        return gson.fromJson(json, TradingData.class);
    }
}
