package com.home.account.dictionary;


/**
 * 支付方式 线上，线下
 */
public class Pays {
    private String pay_id;
    private String pay_method;

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public Pays(String pay_id, String pay_method) {
        this.pay_id = pay_id;
        this.pay_method = pay_method;
    }

    public Pays() {
    }
}
