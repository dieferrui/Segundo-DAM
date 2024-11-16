package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.QuestResult;

import java.util.List;

public interface QuestResultDAO {
    /**
     * Función para insertar un nuevo resultado de misión en la base de datos.
     *
     * @param questResult El resultado de misión a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(QuestResult questResult) throws DAOException;

    /**
     * Función para obtener los resultados de una misión por su ID.
     *
     * @param questId El ID de la misión.
     * @return Los resultados de la misión.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    String getQuestResults(int questId) throws DAOException;

    /**
     * Función para obtener un resultado de misión por su ID.
     *
     * @param questId El ID de la misión.
     * @return El resultado de misión obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    QuestResult obtainById(int questId) throws DAOException;

    /**
     * Función para obtener todos los resultados de misión.
     *
     * @return Una lista de todos los resultados de misión.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<QuestResult> obtainAll() throws DAOException;

    /**
     * Función para obtener todos los resultados de misión por el nombre del grupo.
     *
     * @param partyName El nombre del grupo.
     * @return Una lista de resultados de misión del grupo especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<QuestResult> obtainAllByParty(String partyName) throws DAOException;

    /**
     * Función para obtener todos los resultados de misión por el nombre de la mazmorra.
     *
     * @param dungeonName El nombre de la mazmorra.
     * @return Una lista de resultados de misión de la mazmorra especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<QuestResult> obtainAllByDungeon(String dungeonName) throws DAOException;

    /**
     * Función para obtener todos los resultados de misión completados.
     *
     * @return Una lista de todos los resultados de misión completados.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<QuestResult> obtainAllCleared() throws DAOException;

    /**
     * Función para obtener todos los resultados de misión fallidos.
     *
     * @return Una lista de todos los resultados de misión fallidos.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<QuestResult> obtainAllFailed() throws DAOException;

    /**
     * Función para actualizar un resultado de misión existente.
     *
     * @param questResult El resultado de misión actualizado.
     * @param lastId El ID del resultado de misión a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(QuestResult questResult, int lastId) throws DAOException;

    /**
     * Función para eliminar un resultado de misión por su ID.
     *
     * @param questId El ID del resultado de misión a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(int questId) throws DAOException;
}