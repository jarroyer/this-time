package fun.debaucherydungeon;

public class Data {

    private String dataType;
    private float price;
    private String ticker;
    private long timestamp;

    public Data(String dataType, String ticker, float price, long timestamp) {
        this.dataType = dataType;
        this.timestamp = timestamp;
        this.ticker = ticker;
        this.price = price;
    }

    public String getDataType() {
        return dataType;
    }

    public float getPrice() {
        return price;
    }

    public String getTicker() {
        return ticker;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
