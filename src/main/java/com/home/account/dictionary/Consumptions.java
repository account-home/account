package com.home.account.dictionary;


/**
 * 交易方式 数据字典
 */
public class Consumptions {
    private String consumption_id;
    private String  consumption_type;

    public String getConsumption_id() {
        return consumption_id;
    }

    public void setConsumption_id(String consumption_id) {
        this.consumption_id = consumption_id;
    }

    public String getConsumption_type() {
        return consumption_type;
    }

    public void setConsumption_type(String consumption_type) {
        this.consumption_type = consumption_type;
    }

    public Consumptions(String consumption_id, String consumption_type) {
        this.consumption_id = consumption_id;
        this.consumption_type = consumption_type;
    }

    public Consumptions() {

    }
}
