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

public class PriceList {
    private TreeMap productMap = new TreeMap<Long, Product>();

    public int add(long id, String name, double price) {
        if (productMap.containsKey(id) | price < 0) return -1;
        productMap.put(id, new Product(name, price));
        return 0;
    }

    public int add(long id, String name) {
        if (productMap.containsKey(id)) return -1;
        productMap.put(id, new Product(name));
        return 0;
    }

    public String getProduct(long id) {
        if (!productMap.containsKey(id)) return "Error";
        return productMap.get(id).toString();
    }

    public String getProductName(long id) {
        if (productMap.containsKey(id)) return ((Product) productMap.get(id)).getName();
        return "Error";
    }

    public double getProductPrice(long id) {
        if (productMap.containsKey(id)) return ((Product) productMap.get(id)).getPrice();
        return -1;
    }

    public int setProductName(long id, String name) {
        if (productMap.containsKey(id)) {
            ((Product) productMap.get(id)).setName(name);
            return 0;
        }
        return -1;
    }

    public int setProductPrice(long id, double price) {
        if (productMap.containsKey(id) & price > 0) {
            ((Product) productMap.get(id)).setPrice(price);
            return 0;
        }
        return -1;
    }

    public int removeProduct(long id) {
        if (productMap.containsKey(id)) {
            productMap.remove(id);
            return 0;
        }
        return -1;
    }

    /*
        public double calculate(Pair<Long, Integer>... list) {
            double price = 0;
            for (Pair product : list) {
                //Почему следующая строчка не компилируется?
                long id = product.getKey();
                int quantity = (Integer) product.getValue();
                if (productMap.containsKey(product.getKey())) {
                    price += ((Product) productMap.get(product.getKey())).getPrice() * (Integer) product.getValue();
                }
            }
            return price;
        }
    */
    //Вспомогательный класс
    private class Product {
        private double price;
        private String name;

        Product(String name) {
            this.name = name;
        }

        Product(String name, double price) {
            if (price > 0.0) {
                this.price = price;
            }
            this.name = name;
        }

        double getPrice() {
            return price;
        }

        void setPrice(double price) {
            if (price > 0.0) {
                this.price = price;
            }
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name + " - " + this.price;
        }
    }
}

