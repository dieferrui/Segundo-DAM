package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Dungeon;
import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import java.util.List;

public interface DungeonDAO {
    /**
     * Función para insertar una nueva mazmorra en la base de datos.
     *
     * @param dungeon La mazmorra a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(Dungeon dungeon) throws DAOException;

    /**
     * Función para obtener una mazmorra por su nombre.
     *
     * @param dungeonName El nombre de la mazmorra.
     * @return La mazmorra obtenida.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Dungeon obtainByName(String dungeonName) throws DAOException;

    /**
     * Función para obtener la mazmorra más difícil.
     *
     * @return La mazmorra más difícil.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Dungeon obtainHardest() throws DAOException;

    /**
     * Función para obtener todas las mazmorras.
     *
     * @return Una lista de todas las mazmorras.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Dungeon> obtainAll() throws DAOException;

    /**
     * Función para obtener todas las mazmorras por su bioma.
     *
     * @param biome El bioma de la mazmorra.
     * @return Una lista de mazmorras del bioma especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Dungeon> obtainAllByBiome(Biome biome) throws DAOException;

    /**
     * Función para obtener todas las mazmorras por su dificultad.
     *
     * @param difficulty La dificultad de la mazmorra.
     * @return Una lista de mazmorras de la dificultad especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Dungeon> obtainAllByDifficulty(Difficulty difficulty) throws DAOException;

    /**
     * Función para actualizar una mazmorra existente.
     *
     * @param dungeon La mazmorra actualizada.
     * @param oldDungeon El nombre de la mazmorra a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(Dungeon dungeon, String oldDungeon) throws DAOException;

    /**
     * Función para eliminar una mazmorra por su nombre.
     *
     * @param dungeonName El nombre de la mazmorra a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(String dungeonName) throws DAOException;
}