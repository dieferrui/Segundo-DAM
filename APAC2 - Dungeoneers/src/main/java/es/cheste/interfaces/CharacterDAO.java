package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Character;
import es.cheste.enums.CharaClass;
import es.cheste.enums.Ancestry;

import java.util.List;

public interface CharacterDAO {
    void insert(Character character) throws DAOException;

    Character obtainByName(String charName) throws DAOException;
    Character obtainStrongest() throws DAOException;

    List<Character> obtainAll() throws DAOException;
    List<Character> obtainAllByClass(CharaClass chClass) throws DAOException;
    List<Character> obtainAllByAncestry(Ancestry ancestry) throws DAOException;

    void update(Character character) throws DAOException;
    void delete(String charName) throws DAOException;
}