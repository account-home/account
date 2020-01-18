package com.home.account.serviceimp;

import com.home.account.dao.ConsumptionsDao;
import com.home.account.dao.PaysDao;
import com.home.account.dao.TradingSourceDao;
import com.home.account.dictionary.Consumptions;
import com.home.account.dictionary.Pays;
import com.home.account.dictionary.TradingSource;
import com.home.account.service.ConsumptionsService;
import com.home.account.service.PaysService;
import com.home.account.service.TradingSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 数据字典的获取类
 * 3中方式：
 * 交易领域，交易方式，支付方式
 */
@Service
public class DataDictionaryServiceImpl implements ConsumptionsService , PaysService, TradingSourceService {

    @Autowired
    private PaysDao paysDao;
    @Autowired
    private ConsumptionsDao consumptionsDao;
    @Autowired
    private TradingSourceDao tradingSourceDao;


    @Override
    public boolean updateConsumptions(Consumptions consumptions) {
        return consumptionsDao.updateConsumptions(consumptions);
    }

    @Override
    public boolean deleteConsumptions(String consumptions_id) {
        return consumptionsDao.deleteConsumptions(consumptions_id);
    }

    @Override
    public boolean addConsumptions(Consumptions consumptions) {
        return consumptionsDao.addConsumptions(consumptions);
    }

    @Override
    public List<Consumptions> getConsumptions() {
        return consumptionsDao.getConsumptions();
    }

    @Override
    public boolean addPays(Pays pays) {
        return paysDao.addPays(pays);
    }

    @Override
    public boolean deletePays(String pay_id) {
        return paysDao.deletePays(pay_id);
    }

    @Override
    public boolean updatePays(Pays pays) {
        return paysDao.updatePays(pays);
    }

    @Override
    public List<Pays> getPays() {
        return paysDao.getPays();
    }

    @Override
    public boolean addTradingSource(TradingSource tradingSource) {
        return tradingSourceDao.addTradingSource(tradingSource);
    }

    @Override
    public boolean updateTradingSource(TradingSource tradingSource) {
        return tradingSourceDao.updateTradingSource(tradingSource);
    }

    @Override
    public boolean deleteTradingSource(String tradingSource_id) {
        return tradingSourceDao.deleteTradingSource(tradingSource_id);
    }

    @Override
    public List<TradingSource> getTradingSource() {
        return tradingSourceDao.getTradingSource();
    }
}
