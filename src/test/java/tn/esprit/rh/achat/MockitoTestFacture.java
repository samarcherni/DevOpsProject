package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StockServiceImpl.class})
public class MockitoTestFacture {

    @InjectMocks
    private FactureServiceImpl factureService;
    @Mock
    private FactureRepository factureRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetreiveFactures() {
        List<Facture> factures = new ArrayList<>();
        when(factureRepository.findAll()).thenReturn(factures);
        List<Facture> resultat = factureService.retrieveAllFactures();
        assertEquals(factures, resultat);
    }

    @Test
    void testAddFacture(){
        Facture facture = new Facture(1L,128.0f,500.0f,new Date(2023,10,25),new Date(2023,10,27),false,new HashSet<>(),null,new HashSet<>());
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);
        Facture resultat = factureService.addFacture(facture);
        assertNotNull(resultat);
        assertEquals(facture, resultat);
    }

    @Test
    void testCancelFacture(){
        Long idToCancel= 1L;
        factureService.cancelFacture(idToCancel);
        Mockito.verify(factureRepository).updateFacture(idToCancel);
    }

    @Test
    void testRetrieveFactures(){
        Long idToRetreive=1L;
        Facture facture = new Facture(1L,128.0f,500.0f,new Date(2023,10,25),new Date(2023,10,27),false,new HashSet<>(),null,new HashSet<>());
        when(factureRepository.findById(idToRetreive)).thenReturn(Optional.of(facture));
        Facture resultat= factureService.retrieveFacture(idToRetreive);
        assertNotNull(resultat);
        assertEquals(idToRetreive,resultat.getIdFacture());
    }

}
