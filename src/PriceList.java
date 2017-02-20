import javafx.util.Pair;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * ---------------------------------------
 * Created by Hiraev Malik on 09.02.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 */

public final class PriceList {
    private final Map<Long, Product> productMap = new TreeMap<>();

    public boolean add(long id, String name, double price) {
        if (price < 0) throw new IllegalArgumentException();
        if (productMap.containsKey(id)) return false;
        productMap.put(id, new Product(name, price));
        return true;
    }

    public boolean add(long id, String name) {
        if (productMap.containsKey(id)) return false;
        productMap.put(id, new Product(name));
        return true;
        s
    }

    public String getProduct(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).toString();
    }

    public String getProductName(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).getName();
    }

    public double getProductPrice(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        if (productMap.get(id).getPrice() == null) throw new IllegalStateException("Цена для "
                + productMap.get(id).getName() + " еще не задана");
        return (productMap.get(id)).getPrice();
    }

    public boolean setProductName(long id, String name) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setName(name);
        return true;
    }

    public boolean setProductPrice(long id, Double price) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        if (price < 0) return false;
        (productMap.get(id)).setPrice(price);
        return true;
    }

    public boolean removeProduct(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        productMap.remove(id);
        return true;
    }


    public double calculate(Pair<Long, Integer>... list) {
        double price = 0;
        for (Pair<Long, Integer> product : list) {
            int quantity = product.getValue();
            long key = product.getKey();
            if (!productMap.containsKey(key)) throw new NoSuchElementException();
            Product itProduct = productMap.get(key);
            Double itPrice = itProduct.getPrice();
            if (itPrice == null) throw new IllegalStateException("Цена для "
                    + itProduct.getName() + " еще не задана");
            if (productMap.containsKey(key)) {
                price += itPrice * quantity;
            }
        }
        return price;
    }

    public int getSize() {
        return productMap.size();
    }

    //Вспомогательный класс
    private class Product {
        private Double price;
        private String name;

        Product(String name) {
            this.name = name;
        }

        Product(String name, Double price) {
            this.price = price;
            this.name = name;
        }

        Double getPrice() {
            return price;
        }

        void setPrice(Double price) {
            this.price = price;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (this.price == null) return this.name + " - " + "Цена еще не задана";
            return this.name + " - " + this.price;
        }
    }
}