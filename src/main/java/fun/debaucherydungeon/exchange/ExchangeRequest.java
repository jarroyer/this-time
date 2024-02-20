package fun.debaucherydungeon.exchange;

public record ExchangeRequest (int action, String ticker, float price, float quantity) {}
