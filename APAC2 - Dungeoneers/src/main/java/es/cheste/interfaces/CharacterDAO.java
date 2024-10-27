package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Character;

import java.util.List;

public interface CharacterDAO {
    void insert(Character character) throws DAOException;

    Character obtainByName(String charName) throws DAOException;
    Character obtainStrongest(String charStat) throws DAOException;

    List<Character> obtainAll() throws DAOException;
    List<Character> obtainAllByClass() throws DAOException;
    List<Character> obtainAllByAncestry() throws DAOException;

    void update(Character character) throws DAOException;
    void delete(String charName) throws DAOException;
}