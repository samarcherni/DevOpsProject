package tn.esprit.rh.achat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FournisseurTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurServiceImpl;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFournisseur() {
        Fournisseur newFournisseur = new Fournisseur();

        when(fournisseurRepository.save(newFournisseur)).thenReturn(newFournisseur);

        Fournisseur addedFournisseur = fournisseurServiceImpl.addFournisseur(newFournisseur);

        // Add assertions to check if the addedFournisseur matches the expected behavior
        // For example:
        assertEquals(newFournisseur, addedFournisseur);
    }

    @Test
    public void testRetrieveAllFournisseur (){
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);
        List<Fournisseur> actualRetrieveFournisseursResult = fournisseurServiceImpl.retrieveAllFournisseurs();
        assertSame(fournisseurs, actualRetrieveFournisseursResult);
        assertTrue(actualRetrieveFournisseursResult.isEmpty());
        verify(fournisseurRepository).findAll();
    }
    @Test
    public void testDeleteFournisseur() {
        doNothing().when(fournisseurRepository).deleteById((Long) any());
        fournisseurRepository.deleteById(123L);
        verify(fournisseurRepository).deleteById((Long) any());
    }
}
