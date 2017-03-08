import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

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

    String productNameOne = "Торт Прага 860г.";
    String productNameTwo = "Хлебцы Dr.Korner 7-злаков 100г.";
    String productNameThree = "Булочка с маком";
    String productNameFour = "Булочка веснушка";
    String productNameFive = "Сдоба \"Ромашка\"";
    String productNameSix = "Беляш";

    String priceOne = "399.99";
    String priceTwo = "49.9";
    String priceThree = "15.5";
    String priceFour = "19";

    long idOne = 1001;
    long idTwo = 1002;
    long idThree = 1003;
    long idFour = 1004;
    long idFive = 1005;

    Pair<Long, Integer> firstPurchase = new Pair<>(idOne, 2); //799.98
    Pair<Long, Integer> secondPurchase = new Pair<>(idTwo, 5); //249.50
    Pair<Long, Integer> thirdPurchase = new Pair<>(idThree, 3); //46.50
    Pair<Long, Integer> fourthPurchase = new Pair<>(idFive, 3);

    @Before
    public void setup() {
        priceList.add(idOne, productNameOne, priceOne);
        priceList.add(idTwo, productNameTwo, priceTwo);
        priceList.add(idThree, productNameThree, priceThree);
        priceList.add(idFour, productNameFour, priceFour);
    }

    @Test
    public void add() {
        try {
            priceList.add(idOne, productNameOne, priceOne);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void contains() {
        assertTrue(priceList.contains(idOne));
        assertTrue(priceList.contains(idTwo));
        assertTrue(priceList.contains(idThree));
        assertTrue(priceList.contains(idFour));
        assertFalse(priceList.contains(idFive));
    }

    @Test
    public void getProductName() {
        assertEquals(productNameOne, priceList.getProductName(idOne));
        assertEquals(productNameTwo, priceList.getProductName(idTwo));
        assertEquals(productNameThree, priceList.getProductName(idThree));
        assertEquals(productNameFour, priceList.getProductName(idFour));
        try {
            //Пытаемся получить имя по ID, которого нет
            priceList.getProductName(123);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductPrice() {
        assertEquals(priceOne, priceList.getProductPrice(idOne));
        assertEquals(priceTwo + "0", priceList.getProductPrice(idTwo));
        assertEquals(priceThree + "0", priceList.getProductPrice(idThree));
        assertEquals(priceFour + ".00", priceList.getProductPrice(idFour));
        try {
            priceList.getProductPrice(4533);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProduct() {
        assertEquals(productNameOne + " - 399 руб. 99 коп.", priceList.getProduct(idOne));
        assertEquals(productNameTwo + " - 49 руб. 90 коп.", priceList.getProduct(idTwo));
        assertEquals(productNameThree + " - 15 руб. 50 коп.", priceList.getProduct(idThree));
        try {
            priceList.getProduct(12345677);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void calculate() {
        assertEquals("1095.98", priceList.calculate(firstPurchase, secondPurchase, thirdPurchase));
        assertEquals("1049.48", priceList.calculate(firstPurchase, secondPurchase));
        assertEquals("249.50", priceList.calculate(secondPurchase));
        try {
            priceList.calculate(fourthPurchase);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void calculateTwo() {
        assertEquals("38.00", priceList.calculate(idFour, 2));
        assertEquals("1199.97", priceList.calculate(idOne, 3));
    }

    @Test
    public void setProductName() {
        assertEquals(productNameOne, priceList.getProductName(idOne));
        priceList.setProductName(idOne, productNameFive);
        assertEquals(productNameFive, priceList.getProductName(idOne));
        assertEquals(productNameTwo, priceList.getProductName(idTwo));
        priceList.setProductName(idTwo, productNameSix);
        assertEquals(productNameSix, priceList.getProductName(idTwo));
        try {
            priceList.setProductName(19823, productNameFive);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void setProductPrice() {
        String priceFive = "-20.00";
        String priceSix = "19.806";
        assertEquals(priceOne, priceList.getProductPrice(idOne));
        priceList.setProductPrice(idOne, priceFour);
        assertEquals(priceFour + ".00", priceList.getProductPrice(idOne));
        try {
            priceList.setProductPrice(idTwo, priceFive);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            priceList.setProductPrice(idTwo, priceSix);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void removeProduct() {
        assertTrue(priceList.contains(idOne));
        priceList.removeProduct(idOne);
        assertFalse(priceList.contains(idOne));
        try {
            priceList.removeProduct(1234133);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void equals() {
        PriceList priceList1 = new PriceList();
        priceList1.add(idOne, productNameOne, priceOne);
        PriceList priceList2 = new PriceList();
        priceList2.add(idOne, productNameOne, priceOne);
        PriceList priceList3 = new PriceList();
        priceList3.add(idOne, productNameOne, priceThree);
        assertTrue(priceList1.equals(priceList2));
        assertFalse(priceList1.equals(priceList3));
    }

    @Test
    public void getSize() {
        assertEquals(4, priceList.getSize());
    }

    @Test
    public void toStringTest() {
        System.out.println(priceList.toString());
    }
}