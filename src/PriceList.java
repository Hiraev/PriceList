import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------
 * Created by Hiraev Malik on 09.02.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 */
public class PriceList {
    private Map<Long, Product> productMap;
    private long id;

    public void add(Product product) {
        productMap.put(id, product);
        id++;
    }

    public void remove(int key) {
        productMap.remove(key);
    }

    public double calculateThePrice(List<Pair<Integer, Integer>> list) {
        double price = 0.0;
        double itPrice;
        for (Pair pair : list) {
            itPrice = productMap.get(pair.getKey()).price * (Double) pair.getKey();
            price += itPrice;
        }
        return price;
    }

    class Product {
        private double price;
        private String name;

        public Product() {
        }

        public Product(String name, double price) {
            if (price > 0.0) {
                this.price = price;
            }
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (price > 0.0) {
                this.price = price;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}