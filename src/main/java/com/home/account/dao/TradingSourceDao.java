package com.home.account.dao;

import com.home.account.dictionary.TradingSource;

import java.util.List;

public interface TradingSourceDao {
    boolean addTradingSource(TradingSource tradingSource);
    boolean updateTradingSource(TradingSource tradingSource);
    boolean deleteTradingSource(String  tradingSource_id);
    List<TradingSource> getTradingSource();
}
