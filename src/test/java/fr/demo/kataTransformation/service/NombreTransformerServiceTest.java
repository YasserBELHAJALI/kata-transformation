package fr.demo.kataTransformation.service;

import fr.demo.kataTransformation.exception.InvalidationNombreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NombreTransformerServiceTest {
    private final NombreTransformerService service = new NombreTransformerService();

    @Test
    public void testTransforme_Number1_Returns1() {
        Assertions.assertEquals("1", service.transforme(1));
    }

    @Test
    public void testTransforme_Number3_ReturnsFOOFOO() {
        Assertions.assertEquals("FOOFOO", service.transforme(3));
    }

    @Test
    public void testTransforme_Number5_ReturnsBARBAR() {
        Assertions.assertEquals("BARBAR", service.transforme(5));
    }

    @Test
    public void testTransforme_Number15_ReturnsFOOBARBAR() {
        Assertions.assertEquals("FOOBARBAR", service.transforme(15));
    }

    @Test
    public void testTransforme_NombreInvalide_ThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidationNombreException.class, () -> {
            service.transforme(101);
        });
        Assertions.assertEquals("Le nombre doit être compris entre 0 et 100.", exception.getMessage());
    }
}
