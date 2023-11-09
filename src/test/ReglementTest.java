import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReglementServiceMockitoTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Test
    void testRetrieveAllReglements() {
        Reglement reglement1 = new Reglement();
        Reglement reglement2 = new Reglement();
        List<Reglement> reglements = Arrays.asList(reglement1, reglement2);

        when(reglementRepository.findAll()).thenReturn(reglements);
        List<Reglement> result = reglementService.retrieveAllReglements();
        assertEquals(2, result.size());
    }

    @Test
    void testAddReglement() {
        Reglement reglement = new Reglement();
        doNothing().when(reglementRepository).save(reglement);
        Reglement result = reglementService.addReglement(reglement);
        assertEquals(reglement, result);
        verify(reglementRepository, times(1)).save(reglement);
    }

    @Test
    void testRetrieveReglement() {
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        reglement.setId(reglementId);
        when(reglementRepository.findById(reglementId)).thenReturn(java.util.Optional.of(reglement));
        Reglement result = reglementService.retrieveReglement(reglementId);
        assertEquals(reglementId, result.getId());
    }

    @Test
    void testRetrieveReglementByFacture() {
        Long idFacture = 1L;
        List<Reglement> reglements = Arrays.asList(new Reglement(), new Reglement());
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(reglements);
        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);
        assertEquals(2, result.size());
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        float chiffreAffaire = 100.0f;

        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(chiffreAffaire);
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        assertEquals(chiffreAffaire, result);
    }
}
