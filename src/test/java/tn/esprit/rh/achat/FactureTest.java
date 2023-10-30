package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FactureServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FactureTest {

    @MockBean
    private FactureRepository factureRepository;

    @Autowired
    private IFactureService factureService;

    @Test
    void testRetreiveAllFactures(){
        List <Facture> expectedFactures = new ArrayList<>();
        Facture facture1= new Facture();
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(5.0f);
        facture1.setDateCreationFacture(new Date(2023,10,05));
        facture1.setDateDerniereModificationFacture(new Date(2023,10,10));
        facture1.setArchivee(false);

        Facture facture2= new Facture();
        facture2.setIdFacture(2L);
        facture2.setMontantFacture(20.0f);
        facture2.setMontantRemise(10.0f);
        facture2.setDateCreationFacture(new Date(2023,9,30));
        facture2.setDateDerniereModificationFacture(new Date(2023,10,06));
        facture2.setArchivee(true);

        expectedFactures.add(facture1);
        expectedFactures.add(facture2);

        when(factureRepository.findAll()).thenReturn(expectedFactures);

        List<Facture> actualFactures = factureService.retrieveAllFactures();

        assertEquals(expectedFactures.size(), actualFactures.size());
        assertEquals(10.0f,actualFactures.get(0).getMontantFacture());
        assertEquals(20.0f,actualFactures.get(1).getMontantFacture());

    }


}