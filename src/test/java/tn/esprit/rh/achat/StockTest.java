package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StockTest {

    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImpl stockServiceImpl;

    @Test
    void testRetrieveAllStocks() {
        ArrayList<Stock> stockList = new ArrayList<>();
        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> actualRetrieveAllStocksResult = stockServiceImpl.retrieveAllStocks();
        assertSame(stockList, actualRetrieveAllStocksResult);
        assertTrue(actualRetrieveAllStocksResult.isEmpty());
        verify(stockRepository).findAll();
    }

    @Test
    void testDeleteStock() {
        doNothing().when(stockRepository).deleteById((Long) any());
        stockServiceImpl.deleteStock(123L);
        verify(stockRepository).deleteById((Long) any());
    }
    @Test
    void testAddStock() {
        Stock sampleStock = new Stock();
        when(stockRepository.save(any(Stock.class))).thenReturn(sampleStock);
        Stock addedStock = stockServiceImpl.addStock(sampleStock);
        verify(stockRepository).save(sampleStock);
        assertSame(sampleStock, addedStock);
    }
    @Test
    void testRetrieveStatusStock() {
        List<Stock> sampleStocks = new ArrayList<>();
        when(stockRepository.retrieveStatusStock()).thenReturn(sampleStocks);
        String statusMessage = stockServiceImpl.retrieveStatusStock();
        verify(stockRepository).retrieveStatusStock();
        assertNotNull(statusMessage);
        log.println("Status Message: " + statusMessage);
    }
    @Test
    void testRetrieveStock() {
        Long sampleStockId = 123L;
        Stock sampleStock = new Stock();
        when(stockRepository.findById(sampleStockId)).thenReturn(Optional.of(sampleStock));
        Stock retrievedStock = stockServiceImpl.retrieveStock(sampleStockId);
        verify(stockRepository).findById(sampleStockId);
        assertEquals(sampleStock, retrievedStock);

    }
    @Test
    void testUpdateStock() {
        Stock sampleStock = new Stock();
        when(stockRepository.save(any(Stock.class))).thenReturn(sampleStock);
        Stock updatedStock = stockServiceImpl.updateStock(sampleStock);
        verify(stockRepository).save(sampleStock);
        assertEquals(sampleStock, updatedStock);
    }

}
