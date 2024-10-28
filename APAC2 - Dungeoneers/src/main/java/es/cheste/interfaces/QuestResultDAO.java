package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.QuestResult;

import java.util.List;

public interface QuestResultDAO {
    void insert(QuestResult questResult) throws DAOException;

    QuestResult obtainById(int QuestId) throws DAOException;

    List<QuestResult> obtainAll() throws DAOException;
    List<QuestResult> obtainAllByParty(String partyName) throws DAOException;
    List<QuestResult> obtainAllByDungeon(String dungeonName) throws DAOException;
    List<QuestResult> obtainAllCleared() throws DAOException;
    List<QuestResult> obtainAllFailed() throws DAOException;

    void update(QuestResult questResult, int lastId) throws DAOException;
    void delete(int questId) throws DAOException;
}
