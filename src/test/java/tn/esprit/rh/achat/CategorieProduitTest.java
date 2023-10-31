package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategorieProduitServiceImpl.class})
public class CategorieProduitTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
        assertEquals(categorieProduits, result);
    }


    @Test
    void testAddCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit(1L,"bmw58","voiture",new HashSet<>());
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);
        CategorieProduit resultat = categorieProduitService.addCategorieProduit(categorieProduit);
        assertNotNull(resultat);
        assertEquals(categorieProduit, resultat);
    }

    @Test
    void testDeleteCategorieProduit() {
        Long idToDelete = 1L;
        categorieProduitService.deleteCategorieProduit(idToDelete);
        Mockito.verify(categorieProduitRepository).deleteById(idToDelete);
    }

    @Test
    void testUpdateCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit(2L,"mac","pc",new HashSet<>());
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);
        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);
        assertNotNull(result);
        assertEquals(categorieProduit, result);
    }












}