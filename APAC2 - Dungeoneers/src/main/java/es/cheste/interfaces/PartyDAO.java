package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Party;
import es.cheste.classes.Character;

import java.util.List;

public interface PartyDAO {
    /**
     * Función para insertar un nuevo grupo en la base de datos.
     *
     * @param party El grupo a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(Party party) throws DAOException;

    /**
     * Función para obtener los miembros de un grupo por su nombre.
     *
     * @param partyName El nombre del grupo.
     * @return Un arreglo de personajes que son miembros del grupo.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Character[] getMembers(String partyName) throws DAOException;

    /**
     * Función para obtener un grupo por su nombre.
     *
     * @param partyName El nombre del grupo.
     * @return El grupo obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Party obtainByName(String partyName) throws DAOException;

    /**
     * Función para obtener el grupo más fuerte.
     *
     * @return El grupo más fuerte.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Party obtainStrongest() throws DAOException;

    /**
     * Función para obtener todos los grupos.
     *
     * @return Una lista de todos los grupos.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Party> obtainAll() throws DAOException;

    /**
     * Función para obtener todos los grupos que contienen un miembro específico.
     *
     * @param memberName El nombre del miembro.
     * @return Una lista de grupos que contienen al miembro especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Party> obtainAllThatContainMember(String memberName) throws DAOException;

    /**
     * Función para actualizar un grupo existente.
     *
     * @param party El grupo actualizado.
     * @param oldParty El nombre del grupo a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(Party party, String oldParty) throws DAOException;

    /**
     * Función para eliminar un grupo por su nombre.
     *
     * @param partyName El nombre del grupo a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(String partyName) throws DAOException;
}