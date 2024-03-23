package fun.debaucherydungeon.asset;

public record TradingData(String dataType, String ticker, float price, float quantity, long timestamp) {}
