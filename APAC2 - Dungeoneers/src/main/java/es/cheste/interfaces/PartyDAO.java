package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Party;

import java.util.List;

public interface PartyDAO {
    void insert(Party party) throws DAOException;

    Party obtainByName(String partyName) throws DAOException;
    Party obtainStrongest() throws DAOException;

    List<Party> obtainAll() throws DAOException;
    List<Party> obtainAllThatContainMember(String memberName) throws DAOException;

    void update(Party party, String oldParty) throws DAOException;
    void delete(String partyName) throws DAOException;
}
