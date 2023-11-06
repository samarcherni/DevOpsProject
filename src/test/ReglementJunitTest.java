import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReglemenJnitTest {

    @Autowired
    private ReglementServiceImpl reglementService;

    @MockBean
    private FactureRepository factureRepository;

    @MockBean
    private ReglementRepository reglementRepository;

    @Test
    void testRetrieveAllReglements() {
        // Mock data
        Reglement reglement1 = new Reglement();
        Reglement reglement2 = new Reglement();
        List<Reglement> reglements = Arrays.asList(reglement1, reglement2);

        // Configure mock
        when(reglementRepository.findAll()).thenReturn(reglements);

        // Test the service method
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    void testAddReglement() {
        // Mock data
        Reglement reglement = new Reglement();

        // Configure mock
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        // Test the service method
        Reglement result = reglementService.addReglement(reglement);

        // Verify the result
        assertEquals(reglement, result);
    }

    @Test
    void testRetrieveReglement() {
        // Mock data
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        reglement.setId(reglementId);

        // Configure mock
        when(reglementRepository.findById(reglementId)).thenReturn(java.util.Optional.of(reglement));

        // Test the service method
        Reglement result = reglementService.retrieveReglement(reglementId);

        // Verify the result
        assertEquals(reglementId, result.getId());
    }

    @Test
    void testRetrieveReglementByFacture() {
        // Mock data
        Long idFacture = 1L;
        List<Reglement> reglements = Arrays.asList(new Reglement(), new Reglement());

        // Configure mock
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(reglements);

        // Test the service method
        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        // Mock data
        Date startDate = new Date();
        Date endDate = new Date();
        float chiffreAffaire = 100.0f;

        // Configure mock
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(chiffreAffaire);

        // Test the service method
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Verify the result
        assertEquals(chiffreAffaire, result);
    }

    // Add more test methods as needed
}
