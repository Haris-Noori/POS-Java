package zip_company.Controllers;

public class FinalProduct
{
    private Integer code, qty;
    private String name, type;
    private Float length, price, total;

    public FinalProduct()
    {
        this.code = null;
        this.name = null;
        this.type = null;
        this.length = null;
        this.qty = null;
        this.price = null;
        this.total = null;
    }

    public FinalProduct(Integer Code, String Name, String Type, Float Length, Integer Qty, Float Price, Float Total)
    {
        this.code = Code;
        this.name = Name;
        this.type = Type;
        this.length = Length;
        this.qty = Qty;
        this.price = Price;
        this.total = Total;
    }

    public void setCode(Integer Code)
    { this.code = Code; }

    public void setName(String Name)
    { this.name = Name;}

    public void setType(String Type)
    { this.type = Type; }

    public void setLength(Float Length)
    { this.length = Length; }

    public void setQty(Integer Qty)
    { this.qty = Qty; }

    public void setPrice(Float Price)
    { this.price = Price; }

    public void setTotal()
    { this.total = (this.qty * this.price); }

    public Integer getCode()
    { return this.code; }

    public String getName()
    { return this.name; }

    public String getType()
    { return this.type; }

    public Float getLength()
    { return this.length; }

    public Integer getQty()
    { return this.qty; }

    public Float getPrice()
    { return this.price; }

    public Float getTotal()
    { return this.total; }
}
