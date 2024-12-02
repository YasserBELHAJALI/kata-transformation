package fr.demo.kataTransformation.service;
/**
 * Interface pour la transformation des nombres selon différentes stratégies.
 */
public interface NombreTransformer {

    /**
     * Transforme un nombre en une chaîne de caractères selon des règles spécifiques.
     *
     * @param number le nombre à transformer
     * @return la chaîne résultante après transformation
     */
    String transforme(int number);
}
