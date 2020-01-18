package com.home.account.dictionary;


/**
 * 交易来源 收入还是支出
 */
public class TradingSource {
    private String trading_id;
    private String trading_source;

    public String getTrading_id() {
        return trading_id;
    }

    public void setTrading_id(String trading_id) {
        this.trading_id = trading_id;
    }

    public String getTrading_source() {
        return trading_source;
    }

    public void setTrading_source(String trading_source) {
        this.trading_source = trading_source;
    }

    public TradingSource(String trading_id, String trading_source) {
        this.trading_id = trading_id;
        this.trading_source = trading_source;
    }

    public TradingSource() {
    }

}
