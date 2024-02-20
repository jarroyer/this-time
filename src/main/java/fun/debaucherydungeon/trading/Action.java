package fun.debaucherydungeon.trading;

public record Action (int actionToTake, String ticker, float quantity, float price){ }
