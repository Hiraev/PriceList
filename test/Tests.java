import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * ---------------------------------------
 * Created by Hiraev Malik on 09.02.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 */
public class Tests {

    PriceList priceList = new PriceList();
    //Покупки
    Pair firstPurchase = new Pair(1001, 2);
    Pair secondPurchase = new Pair(1002, 5);
    Pair thirdPurchase = new Pair(1003, 3);


    //Продукты
    String productNameOne = "Торт Прага 860г.";
    String productNameTwo = "Хлебцы Dr.Korner 7-злаков 100г.";
    String productNameThree = "Булочка с маком";
    String productNameFour = "Булочка веснушка";
    String productNameFive = "Сдоба \"Ромашка\"";
    String productNameSix = "Беляш";
    String productNameSeven = "Плюшка московская";

    double priceOne = 399.99;
    double priceTwo = 49.90;
    double priceThree = 15.50;
    double priceFour = 19.00;
    double priceFive = 20.00;
    double priceSix = 19.80;


    @Before
    public void setup() {
        priceList.add(1001, productNameOne, priceOne);
        priceList.add(1002, productNameTwo, priceTwo);
        priceList.add(1003, productNameThree, priceThree);
        priceList.add(1004, productNameFour, priceFour);
        priceList.add(1005, productNameFive);
        priceList.add(1006, productNameSix);
        priceList.add(1007, productNameSeven);
    }

    @Test
    public void addToPriceList() {
        //При успешном добавлении получаем нолик
        assertEquals(0, priceList.add(1011, productNameOne, 399.99));
        assertEquals(0, priceList.add(1022, productNameTwo, 49.90));
        assertEquals(0, priceList.add(1033, productNameThree, 15.50));
        assertEquals(0, priceList.add(1044, productNameFour, 19.00));
        //Случай, когда Id уже занят каким-то продуктом
        assertEquals(-1, priceList.add(1004, "Этот товар не добавится", 0.00));
    }

    @Test
    public void addToPriceListWithoutPrice() {
        assertEquals(0, priceList.add(1009, "Безымяннй товар"));
        assertEquals(-1, priceList.add(1007, productNameSeven));
    }

    @Test
    public void getProductName() {
        assertEquals(productNameOne, priceList.getProductName(1001));
        assertEquals(productNameTwo, priceList.getProductName(1002));
        assertEquals(productNameThree, priceList.getProductName(1003));
        assertEquals(productNameFour, priceList.getProductName(1004));
        assertEquals("Error", priceList.getProductName(123));
    }

    @Test
    public void getProductPrice() {
        assertEquals(priceOne, priceList.getProductPrice(1001), 1e-3);
        assertEquals(priceTwo, priceList.getProductPrice(1002), 1e-3);
        assertEquals(priceThree, priceList.getProductPrice(1003), 1e-3);
        assertEquals(priceFour, priceList.getProductPrice(1004), 1e-3);
        assertEquals(-1, priceList.getProductPrice(4533), 1e-3);
    }

    @Test
    public void getProduct() {
        assertEquals(productNameOne + " - " + priceOne, priceList.getProduct(1001));
        assertEquals(productNameTwo + " - " + priceTwo, priceList.getProduct(1002));
        assertEquals(productNameThree + " - " + priceThree, priceList.getProduct(1003));
        assertEquals("Error", priceList.getProduct(12345677));
    }

    @Test
    public void setProductPrice() {
        assertEquals(0, priceList.setProductPrice(1005, priceFive));
        assertEquals(priceFive, priceList.getProductPrice(1005), 1e-3);
        assertEquals(0, priceList.setProductPrice(1006, priceSix));
        assertEquals(priceSix, priceList.getProductPrice(1006), 1e-3);
        assertEquals(-1, priceList.setProductPrice(1007, -19));
        assertEquals(-1, priceList.setProductPrice(1999, 0));
    }

    @Test
    public void setProductName() {
        assertEquals(0, priceList.setProductName(1001, productNameFour));
        assertEquals(productNameFour, priceList.getProductName(1001));
        assertEquals(-1, priceList.setProductName(9999, "Такого id там нет"));
    }

    @Test
    public void removeProduct() {
        //Проверяем наличие продукта
        assertEquals(productNameSeven, priceList.getProductName(1007));
        //Удаляем этот продукт
        assertEquals(0, priceList.removeProduct(1007));
        //Снова проверяем его наличие путем запроса имени
        assertEquals("Error", priceList.getProductName(1007));
        assertEquals(-1, priceList.removeProduct(74892));
    }

    @Test
    public void calculate() {
        //Сумма покупок
        double sum1 = priceOne * 2 + priceTwo * 5 + priceThree * 3;
        double sum2 = priceOne * 2 + priceTwo * 5;
        double sum3 = priceTwo * 5;
        assertEquals(sum1, priceList.calculate(firstPurchase, secondPurchase, thirdPurchase), 1e-3);
        assertEquals(sum2, priceList.calculate(firstPurchase, secondPurchase), 1e-3);
        assertEquals(sum3, priceList.calculate(secondPurchase), 1e-3);
    }

}
