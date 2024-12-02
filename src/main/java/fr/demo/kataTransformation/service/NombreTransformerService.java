package fr.demo.kataTransformation.service;

import fr.demo.kataTransformation.exception.InvalidationNombreException;
import fr.demo.kataTransformation.exception.TransformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NombreTransformerService implements NombreTransformer {
    private static final Logger logger = LoggerFactory.getLogger(NombreTransformerService.class);

    public String transforme(int number) {
        logger.info("Transformation du nombre : {}", number);

        // Validation de l'entrée
        if (number < 0 || number > 100) {
            logger.error("Le nombre {} n'est pas compris entre 0 et 100.", number);
            throw new InvalidationNombreException("Le nombre doit être compris entre 0 et 100.");
        }

        StringBuilder result = new StringBuilder();

        try {
            // Vérification de la divisibilité (prioritaire)
            if (number % 3 == 0) result.append("FOO");
            if (number % 5 == 0) result.append("BAR");

            // Conversion du nombre en chaîne pour l'analyse des chiffres
            String numStr = String.valueOf(number);

            // Analyse des chiffres de gauche à droite
            for (char digit : numStr.toCharArray()) {
                if (digit == '3') result.append("FOO");
                if (digit == '5') result.append("BAR");
                if (digit == '7') result.append("QUIX");
            }

            // Retourner le nombre sous forme de chaîne si aucune règle ne s'applique
            String finalResult = result.length() > 0 ? result.toString() : numStr;
            logger.info("Résultat de la transformation : {}", finalResult);
            return finalResult;

        } catch (Exception e) {
            logger.error("Erreur lors de la transformation du nombre : {}", number, e);
            throw new TransformationException("Une erreur est survenue lors de la transformation du nombre.", e);
        }
    }
}
