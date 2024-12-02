package fr.demo.kataTransformation.controller;

import fr.demo.kataTransformation.exception.InvalidationNombreException;
import fr.demo.kataTransformation.exception.TransformationException;
import fr.demo.kataTransformation.service.NombreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/transform")
@Tag(name = "Transformation des Nombres", description = "API pour la transformation des nombres")
public class NombreTransformerController {


    private final NombreTransformer service;

    @Autowired
    public NombreTransformerController(NombreTransformer transformer) {
        this.service = transformer;
    }

    @GetMapping("/{number}")
    @Operation(summary = "Transforme un nombre selon les règles spécifiées")
    public ResponseEntity<String> transformNombre(@PathVariable int number) {
        try {
            String result = service.transforme(number);
            return ResponseEntity.ok(result);
        } catch (InvalidationNombreException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (TransformationException e) {
            return ResponseEntity.status(500).body("Une erreur est survenue lors de la transformation.");
        }
    }

}
