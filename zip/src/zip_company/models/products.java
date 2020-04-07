package zip_company.models;

public class products {
    private Integer code;
    private int qty;
    private float price, length;
    private String name, type, description;

    public products(){

    }
    public products(Integer Code, String Name, String Type, float Length, Integer Qty, float Price, String Desc)
    {
        this.code = Code;
        this.name = Name;
        this.type = Type;
        this.length = Length;
        this.qty = Qty;
        this.price = Price;
        this.description = Desc;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setLength(float length) {
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

    public Integer getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    public Integer getQty() {
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

    public String get_insert_query(){
        return "insert into zips values("+this.getCode()+",'"+this.getName().toLowerCase()+
                "','"+this.getType()+"',"+this.getLength()+","+this.getQty()+","+
                this.getPrice()+",'"+this.getDescription()+"');";
    }

    public String check_prodect_db_query(){
        return "select * from zips where code = "+this.getCode()+";";
    }
    public String check_prodect_db_name_query(){
        return "select * from zips where name = '"+this.getName()+"';";
    }
    public String update_product_code(int preCode){
        return  "update zips set code = "+this.getCode()+" where code = "+ preCode+";";
    }
    public String update_product(){
        return "update zips set name = '"+this.getName()+"', type = '"+this.getType()+
                "', price = "+this.getPrice()+",quantity = "+this.getQty()+", description = '"
        +this.getDescription()+"', length = "+this.getLength()+" where code = "+this.getCode()+";";
    }
    public String get_all_products(){
        return "select * from zips";
    }
    public String delete_product(){
        return "delete from zips where code = "+this.getCode()+";";
    }
}
