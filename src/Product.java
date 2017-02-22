/**
 * ---------------------------------------
 * Created by Hiraev Malik on 22.02.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 */
class Product {
    private Double price;
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Double price) {
        this.price = price;
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (this.price == null) return this.name + " - " + "Цена еще не задана";
        return this.name + " - " + this.price;
    }
}