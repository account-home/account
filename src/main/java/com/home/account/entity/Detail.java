package  com.home.account.entity;

public class Detail {
    private String user_id; //用户id
    private double transaction_money; //交易金额
    private String pay_id; //支付方式
    private String consumptions_id;
    private String  pay_way;//支出或者收入 有且只有两种


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getTransaction_money() {
        return transaction_money;
    }

    public void setTransaction_money(double transaction_money) {
        this.transaction_money = transaction_money;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getConsumptions_id() {
        return consumptions_id;
    }

    public void setConsumptions_id(String consumptions_id) {
        this.consumptions_id = consumptions_id;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public Detail(String user_id, double transaction_money, String pay_id, String consumptions_id, String pay_way) {
        this.user_id = user_id;
        this.transaction_money = transaction_money;
        this.pay_id = pay_id;
        this.consumptions_id = consumptions_id;
        this.pay_way = pay_way;
    }

    public Detail() {
    }
}
