package com.home.account.controller;


import com.home.account.dictionary.Consumptions;
import com.home.account.dictionary.Pays;
import com.home.account.dictionary.TradingSource;
import com.home.account.serviceimp.DataDictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操作数据字典的接口
 */
@RestController
@RequestMapping("dataDictionary")
public class DataDictionaryController {

    @Autowired
    private DataDictionaryServiceImpl dataDictionaryService;

    /**
     * 获取支付方式
     * @return Pays
     */
    @RequestMapping("getPays")
    public List<Pays> getPays(){
      return   dataDictionaryService.getPays();

    }

    /**
     *  获取消费方式
     * @return Consumptions
     */
    @RequestMapping("getConsumptions")
    public List<Consumptions> getConsumptions(){
        return   dataDictionaryService.getConsumptions();
    }

    /**
     * 交易方式，线上线下
     * @return TradingSource
     */
    @RequestMapping("getTradingSource")
    public List<TradingSource> getTradingSource(){
        return   dataDictionaryService.getTradingSource();
    }

    /**
     * 增加 支付的数据字典
     * @param pays 对象
     * @return bool
     */
    @RequestMapping("addPays")
    public Boolean addPays(Pays pays){
        return   dataDictionaryService.addPays(pays);

    }
    @RequestMapping("addConsumptions")
    public Boolean addConsumptions(Consumptions Consumptions){
        return   dataDictionaryService.addConsumptions(Consumptions);

    }
    @RequestMapping("addTradingSource")
    public Boolean addTradingSource(TradingSource tradingSource){
        return   dataDictionaryService.addTradingSource(tradingSource);

    }

    /**
     * 更新数据字典
     * @param pays 支付方式
     * @return bool
     */
    @RequestMapping("updatePays")
    public Boolean updatePays(Pays pays){
        return   dataDictionaryService.updatePays(pays);

    }
    @RequestMapping("updateConsumptions")
    public Boolean updateConsumptions(Consumptions Consumptions){
        return   dataDictionaryService.updateConsumptions(Consumptions);

    }
    @RequestMapping("updateTradingSource")
    public Boolean updateTradingSource(TradingSource tradingSource){
        return   dataDictionaryService.updateTradingSource(tradingSource);
    }

    /**
     * 删除数据字典
     * @param pay_id 支付ID
     * @return bool
     */
    @RequestMapping("deletePays")
    public Boolean deletePays(String pay_id){
        return   dataDictionaryService.deletePays(pay_id);

    }
    @RequestMapping("deleteConsumptions")
    public Boolean updateConsumptions(String consumption_id){
        return   dataDictionaryService.deleteConsumptions(consumption_id);

    }
    @RequestMapping("deleteTradingSource")
    public Boolean deleteTradingSource(String trading_id){
        return   dataDictionaryService.deleteTradingSource(trading_id);
    }

}
