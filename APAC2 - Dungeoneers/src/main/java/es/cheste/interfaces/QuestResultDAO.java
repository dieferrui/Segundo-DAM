package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.QuestResult;

import java.util.List;

public interface QuestResultDAO {
    void insert(QuestResult questResult) throws DAOException;

    List<QuestResult> obtainAll() throws DAOException;
    List<QuestResult> obtainAllByParty() throws DAOException;
    List<QuestResult> obtainAllByDungeon() throws DAOException;
    List<QuestResult> obtainAllCleared() throws DAOException;
    List<QuestResult> obtainAllFailed() throws DAOException;

    void update(QuestResult questResult) throws DAOException;
    void delete(String questName) throws DAOException;
}
