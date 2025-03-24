import com.girouard.NullStockException;
import com.girouard.StockManager;
import com.girouard.UnknownProductException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockManagerTest {

    private  static  StockManager stockManager;

    @BeforeAll
    public static void setUp(){
        stockManager = new StockManager();
    }

    @BeforeEach
    public void setQuantities(){
        stockManager.setQuantity("PRODUCT1", 10);
        stockManager.setQuantity("PRODUCT2", 0);
        stockManager.setQuantity("PRODUCT3", 5);
        stockManager.setQuantity("PRODUCT4", 1);
    }

    @Test
    void testHasStock() throws UnknownProductException {
        assertTrue(stockManager.hasStock("PRODUCT1"));
    }
    @Test
    void testHasNoStock() throws UnknownProductException {
        assertFalse(stockManager.hasStock("PRODUCT2"));
    }
    @Test
    void testGetQuantity() throws UnknownProductException {
        assertEquals(10, stockManager.getQuantity("PRODUCT1"));
        assertEquals(0, stockManager.getQuantity("PRODUCT2"));
        assertEquals(5, stockManager.getQuantity("PRODUCT3"));
        assertEquals(1, stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testUnknownProduct(){
        assertThrows(UnknownProductException.class,
                () -> stockManager.getQuantity("UNKNOW PRODUCT"));
    }

    @Test
    void testDestock() throws UnknownProductException, NullStockException {
        stockManager.destock("PRODUCT1");
        assertEquals(9,stockManager.getQuantity("PRODUCT1"));

        stockManager.destock("PRODUCT3");
        assertEquals(4,stockManager.getQuantity("PRODUCT3"));

        stockManager.destock("PRODUCT4");
        assertEquals(0,stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testDestockNullException() {
        assertThrows(NullStockException.class,
                () -> stockManager.destock("PRODUCT2"));
    }

    @Test
    void testNDestock() throws UnknownProductException, NullStockException {
        stockManager.destock("PRODUCT1",5);
        assertEquals(5,stockManager.getQuantity("PRODUCT1"));
        stockManager.destock("PRODUCT3",3);
        assertEquals(2,stockManager.getQuantity("PRODUCT3"));
        stockManager.destock("PRODUCT4",1);
        assertEquals(0,stockManager.getQuantity("PRODUCT4"));
    }

    @Test
    void testNDestockNullException() {
        assertThrows(NullStockException.class,
                () -> stockManager.destock("PRODUCT1", 11));
    }

    @Test
    void testRestock() throws UnknownProductException {
        stockManager.restock("PRODUCT1");
        assertEquals(11,stockManager.getQuantity("PRODUCT1"));
    }

    @Test
    void testNRestock() throws UnknownProductException {
        stockManager.restock("PRODUCT1",5);
        assertEquals(15,stockManager.getQuantity("PRODUCT1"));

        stockManager.restock("PRODUCT5",20);
        assertEquals(20,stockManager.getQuantity("PRODUCT5"));
    }

    @Test
    void testIsExist() {
        assertTrue(stockManager.isExist("PRODUCT1"));
        assertFalse(stockManager.isExist("PRODUCT9"));
    }

    @Test
    void testDelete() throws UnknownProductException {
        stockManager.delete("PRODUCT1");
        assertFalse(stockManager.isExist("PRODUCT1"));
    }
    
    @Test
    void testDeleteAll() {
        assertFalse(stockManager.isEmpty());

        stockManager.deleteAll();
        assertTrue(stockManager.isEmpty());
    }
}
