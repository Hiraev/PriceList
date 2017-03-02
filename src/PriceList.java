import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
    private final Map<Long, Product> productMap;

    public PriceList() {
        productMap = new HashMap<>();
    }

    public void add(long id, String name, String price) {
        //Заменить исключение!!!
        if (productMap.containsKey(id)) throw new NoSuchElementException();
        productMap.put(id, new Product(name, price));
    }

    public String getProduct(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).toString();
    }

    public String getProductName(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).getName();
    }

    public String getProductPrice(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return (productMap.get(id)).getPrice();
    }

    public void setProductName(long id, String name) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setName(name);
    }

    public void setProductPrice(long id, String price) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setPrice(price);
    }

    public void removeProduct(long id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        productMap.remove(id);
    }

    public boolean contains(long id) {
        return productMap.containsKey(id);
    }

    public String calculate(long id, int quantity) {
        return priceOfOneProduct(id, quantity).toString();
    }

    public String calculate(Pair<Long, Integer>... list) {
        BigDecimal price = new BigDecimal("0");
        for (Pair<Long, Integer> product : list) {
            long key = product.getKey();
            int amount = product.getValue();
            price = price.add(priceOfOneProduct(key, amount));
        }
        return price.toString();
    }

    private BigDecimal priceOfOneProduct(long id, int amount) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).calculate(amount);
    }

    public int getSize() {
        return productMap.size();
    }


    private static class Product {
        private BigDecimal price;
        private String name;

        private Product(String name, String price) {
            this.price = convert(price);
            this.name = name;
        }

        private String getPrice() {
            return price.toString();
        }

        private String getName() {
            return name;
        }

        public void setPrice(String price) {
            this.price = convert(price);
        }

        private BigDecimal convert(String price) {
            String[] str = price.split("\\.");
            if (str.length < 2) {
                price += ".00";
            } else if (str[1].length() > 2) {
                throw new IllegalArgumentException("Копейки должны занимать не более 2-ух символов");
            } else if (str[1].length() < 2) price += "0";
            BigDecimal decimal = new BigDecimal(price);
            if (decimal.signum() == -1) throw new IllegalArgumentException();
            return decimal;
        }

        public void setName(String name) {
            this.name = name;
        }

        private BigDecimal calculate(int value) {
            return price.multiply(new BigDecimal(value));
        }
    }
}