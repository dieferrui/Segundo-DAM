package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Dungeon;
import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import java.util.List;

public interface DungeonDAO {
    void insert(Dungeon dungeon) throws DAOException;

    Dungeon obtainByName(String dungeonName) throws DAOException;
    Dungeon obtainHardest() throws DAOException;

    List<Dungeon> obtainAll() throws DAOException;
    List<Dungeon> obtainAllByBiome(Biome diome) throws DAOException;
    List<Dungeon> obtainAllByDifficulty(Difficulty difficulty) throws DAOException;

    void update(Dungeon dungeon) throws DAOException;
    void delete(String dungeonName) throws DAOException;
}
