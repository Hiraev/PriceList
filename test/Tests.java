import org.junit.Test;

import static org.junit.Assert.assertEquals;



/**
 * ---------------------------------------
 * Created by Hiraev Malik on 09.02.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 */

public class Tests{
    PriceList priceList = new PriceList();
    Product sausage = new Product("Колбаса", 330.40);
    Product carrot = new Product("Морковь", 20.80);
    Product cheese = new Product("Сыр", 450.40);
    Product bread = new Product("Хлеб", 50.55);
    Product cereals = new Product("Гречневые хлопья", 165.44);

    @Test
    void Test() {
        priceList.add(sausage);
        priceList.add(carrot);
        priceList.add(cheese);
        priceList.add(bread);
        priceList.add(cereals);
        assertEquals(sausage, priceList.get(0));
    }
}
