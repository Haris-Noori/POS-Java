package zip_company.Controllers;

public class Product {

    private Integer code;
    private int price;
    private int qty;
    private float length;
    private String name, type, description;


    Product(Integer Code, String Name, String Type, float Length, Integer Qty, Integer Price, String Desc)
    {
        this.code = Code;
        this.name = Name;
        this.type = Type;
        this.length = Length;
        this.qty = Qty;
        this.price = Price;
        this.description = Desc;
    }

    /**
     * SET FUNCTIONS
     */
    public void setCode(int code) {
        this.code = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * GET FUNCTIONS
     */
    public Integer getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public float getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void clearProduct()
    {
        this.code = Integer.parseInt(null);
        this.name = null;
        this.type = null;
        this.length = Float.parseFloat(null);
        this.qty = Integer.parseInt(null);
        this.price = Integer.parseInt(null);
        this.description = null;
    }


}
