package fr.demo.kataTransformation.controller;

import fr.demo.kataTransformation.exception.InvalidationNombreException;
import fr.demo.kataTransformation.service.NombreTransformerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
public class NombreTransformerControllerTest {

    @InjectMocks
    private NombreTransformerController controller;

    @Mock
    private NombreTransformerService service;

    @Test
    public void testTransformeNomberReturnsTransformedString() {
        when(service.transforme(15)).thenReturn("FOOBARBAR");

        ResponseEntity<String> response = controller.transformNombre(15);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("FOOBARBAR", response.getBody());
    }

    @Test
    public void testTransformeNombreReturnsBadRequest() {
        when(service.transforme(anyInt())).thenThrow(new InvalidationNombreException("Le nombre doit être compris entre 0 et 100."));

        ResponseEntity<String> response = controller.transformNombre(150);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Le nombre doit être compris entre 0 et 100.", response.getBody());
    }
}
