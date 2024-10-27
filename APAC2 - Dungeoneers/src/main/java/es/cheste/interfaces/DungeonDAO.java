package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Dungeon;

import java.util.List;

public interface DungeonDAO {
    void insert(Dungeon dungeon) throws DAOException;

    Dungeon obtainByName(String dungeonName) throws DAOException;
    Dungeon obtainHardest() throws DAOException;

    List<Dungeon> obtainAll() throws DAOException;
    List<Dungeon> obtainAllByBiome() throws DAOException;
    List<Dungeon> obtainAllByDifficulty() throws DAOException;

    void update(Dungeon dungeon) throws DAOException;
    void delete(String dungeonName) throws DAOException;
}
