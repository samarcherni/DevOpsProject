package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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




}