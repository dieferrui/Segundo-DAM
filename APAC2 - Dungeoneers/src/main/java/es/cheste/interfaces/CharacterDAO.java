package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Character;
import es.cheste.enums.CharaClass;
import es.cheste.enums.Ancestry;

import java.util.List;

public interface CharacterDAO {
    /**
     * Función para insertar un nuevo personaje en la base de datos.
     *
     * @param character El personaje a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(Character character) throws DAOException;

    /**
     * Función para obtener un personaje por su nombre.
     *
     * @param charName El nombre del personaje.
     * @return El personaje obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Character obtainByName(String charName) throws DAOException;

    /**
     * Función para obtener el personaje más fuerte.
     *
     * @return El personaje más fuerte.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Character obtainStrongest() throws DAOException;

    /**
     * Función para obtener todos los personajes.
     *
     * @return Una lista de todos los personajes.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Character> obtainAll() throws DAOException;

    /**
     * Función para obtener todos los personajes por su clase.
     *
     * @param chClass La clase del personaje.
     * @return Una lista de personajes de la clase especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Character> obtainAllByClass(CharaClass chClass) throws DAOException;

    /**
     * Función para obtener todos los personajes por su ascendencia.
     *
     * @param ancestry La ascendencia del personaje.
     * @return Una lista de personajes de la ascendencia especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Character> obtainAllByAncestry(Ancestry ancestry) throws DAOException;

    /**
     * Función para actualizar un personaje existente.
     *
     * @param character El personaje actualizado.
     * @param oldChar El nombre del personaje a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(Character character, String oldChar) throws DAOException;

    /**
     * Función para eliminar un personaje por su nombre.
     *
     * @param charName El nombre del personaje a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(String charName) throws DAOException;
}