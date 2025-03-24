package com.girouard;

import java.util.HashMap;
import java.util.Map;

public class StockManager {

    private final Map<String, Integer> stocks = new HashMap<>();

    public void setQuantity(String stock, int quantity) {
        stocks.put(stock, quantity);
    }

    public boolean hasStock(String product) throws UnknownProductException {
        return getQuantity(product) > 0;
    }

    public boolean isExist(String product){
        if (stocks.containsKey(product)) {
            return true;
        }
        return false;
    }

    public int getQuantity (String product) throws UnknownProductException {
        if (stocks.containsKey(product)) {
            return stocks.get(product);
        }
        throw new UnknownProductException();
    }

    public void destock (String product) throws UnknownProductException, NullStockException {
        destock(product, 1);
    }

    public void destock (String product, int n) throws UnknownProductException, NullStockException {
        if(getQuantity(product) >= n) {
            setQuantity(product, getQuantity(product) - n);
        }else {
            throw new NullStockException();
        }
    }

    public void restock (String product) throws UnknownProductException {
        restock(product, 1);
    }

    public void restock (String product, int n) throws UnknownProductException {
        if(stocks.containsKey(product)) {
            setQuantity(product, getQuantity(product) + n);
        }else {
            setQuantity(product, n);
        }
    }

    public void delete (String product) throws UnknownProductException {
        if(stocks.containsKey(product)) {
            stocks.remove(product);
        }else {
            throw new UnknownProductException();
        }
    }

    public void deleteAll (){
        stocks.clear();
    }

    public boolean isEmpty (){
        if(stocks.isEmpty()){
            return true;
        }
        return false;
    }
}
