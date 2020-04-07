package zip_company.Controllers;

public class dailySalesTab {
    private String customer,user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private  float remaining;
    private float total;


    public dailySalesTab() {
        this.customer = null;
        this.user = null;
        this.remaining = 0;
        this.total = 0;
    }

    public dailySalesTab(float remain, float total, String cust,String user) {
        this.customer = cust;
        this.remaining = remain;
        this.total = total;
        this.user = user;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getRemaining() {
        return remaining;
    }

    public void setRemaining(float code) {
        this.remaining = code;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }




}
