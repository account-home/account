package com.home.account.service;

import com.home.account.dictionary.TradingSource;

import java.util.List;

public interface TradingSourceService {
    boolean addTradingSource(TradingSource tradingSource);
    boolean updateTradingSource(TradingSource tradingSource);
    boolean deleteTradingSource(String tradingSource_id);
    List<TradingSource> getTradingSource();
}
