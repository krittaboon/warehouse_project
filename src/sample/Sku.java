package sample;

public class Sku {
    private int sku_id;
    private String product_name;
    private String size;
    private String color;
    private int price;
    private int quantity;

    public Sku(int sku_id, String product_name, String size, String color, int price, int quantity) {
        this.sku_id = sku_id;
        this.product_name = product_name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public Sku(int sku_id, String product_name, int price) {
        this.sku_id = sku_id;
        this.product_name = product_name;
        this.price = price;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
