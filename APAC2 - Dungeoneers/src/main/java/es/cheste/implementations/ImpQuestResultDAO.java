package es.cheste.implementations;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;

import es.cheste.interfaces.QuestResultDAO;
import es.cheste.classes.QuestResult;
import es.cheste.classes.Party;
import es.cheste.classes.Dungeon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpQuestResultDAO implements QuestResultDAO {

    DBConnection c = new DBConnection();
    ImpPartyDAO partyImpMethod = new ImpPartyDAO();
    ImpDungeonDAO dungeonImpMethod = new ImpDungeonDAO();

    // Final Strings
    private static final String NOT_OBTAINED = "Quest result not obtained.";
    private static final String LIST_NOT_OBTAINED = "Quest results not obtained.";

    // SQL Queries
    private static final String INSERT = "INSERT INTO QuestResult (questId, PartyId, DungeonId, success, report) VALUES (?, ?, ?, ?, ?)";
    private static final String OBTAIN_BY_ID = "SELECT * FROM QuestResult WHERE questId = ?";
    private static final String OBTAIN_ALL = "SELECT * FROM QuestResult";
    private static final String OBTAIN_ALL_BY_PARTY = "SELECT * FROM QuestResult WHERE PartyId = ?";
    private static final String OBTAIN_ALL_BY_DUNGEON = "SELECT * FROM QuestResult WHERE DungeonId = ?";
    private static final String OBTAIN_ALL_CLEARED = "SELECT * FROM QuestResult WHERE success = true";
    private static final String OBTAIN_ALL_FAILED = "SELECT * FROM QuestResult WHERE success = false";
    private static final String UPDATE = "UPDATE QuestResult SET questId = ?, PartyId = ?, DungeonId = ?, success = ?, report = ? WHERE questId = ?";
    private static final String DELETE = "DELETE FROM QuestResult WHERE questId = ?";

    @Override
    public void insert(QuestResult questResult) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {

            ps.setInt(1, questResult.getQuestId());
            ps.setString(2, questResult.getPartyId());
            ps.setString(3, questResult.getDungeonId());
            ps.setBoolean(4, questResult.isSuccess());
            ps.setString(5, questResult.getReport());

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Quest result insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Quest result not inserted.", e);
        }

    }

    @Override
    public QuestResult obtainById(int questId) throws DAOException {
        QuestResult qr = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_ID)) {
            ps.setInt(1, questId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    qr = mapResults(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return qr;
    }

    @Override
    public List<QuestResult> obtainAll() throws DAOException {
        List<QuestResult> qrList = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    qrList.add(mapResults(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return qrList;
    }

    @Override
    public List<QuestResult> obtainAllByParty(String partyName) throws DAOException {
        List<QuestResult> qrList = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_PARTY)) {
            ps.setString(1, partyName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    qrList.add(mapResults(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return qrList;
    }

    @Override
    public List<QuestResult> obtainAllByDungeon(String dungeonName) throws DAOException {
        List<QuestResult> qrList = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_DUNGEON)) {
            ps.setString(1, dungeonName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    qrList.add(mapResults(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return qrList;
    }

    @Override
    public List<QuestResult> obtainAllCleared() throws DAOException {
        List<QuestResult> qrList = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_CLEARED)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    qrList.add(mapResults(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return qrList;
    }

    @Override
    public List<QuestResult> obtainAllFailed() throws DAOException {
        List<QuestResult> qrList = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_FAILED)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    qrList.add(mapResults(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return qrList;
    }

    @Override
    public void update(QuestResult questResult, int lastId) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {
            QuestResult lastQR = obtainById(lastId);

            ps.setInt(1, questResult.getQuestId());
            ps.setString(2, questResult.getPartyId());
            ps.setString(3, questResult.getDungeonId());
            ps.setBoolean(4, questResult.isSuccess());
            ps.setString(5, questResult.getReport());
            ps.setInt(6, lastQR.getQuestId());

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Quest result update failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Quest result not updated.", e);
        }
    }

    @Override
    public void delete(int questId) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {
            ps.setInt(1, questId);

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Quest result deletion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Quest result not deleted.", e);
        }
    }

    @Override
    public String getQuestResults(int questId) throws DAOException {
        String resultsExplanation = null;

        try (CallableStatement stmt = c.getConnection().prepareCall("{CALL GetQuestResultById(?)}")) {
            stmt.setInt(1, questId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dungeonName = rs.getString("dungeonName");
                String dungeonDifficulty = rs.getString("dungeonDifficulty");
                String partyName = rs.getString("partyName");
                int dungeonPower = rs.getInt("dungeonPoints");
                int partyPower = rs.getInt("ptPower");

                resultsExplanation = "Dungeon: " + dungeonName + " (Difficulty " + dungeonDifficulty + " - " +
                                        dungeonPower + ")\nParty: " + partyName + " (Power " + partyPower + ")";
            }

        } catch (SQLException e) {
            throw new DAOException("Quest results not obtained.", e);

        }

        return resultsExplanation;
    }

    private QuestResult mapResults(ResultSet rs) throws SQLException, DAOException {
        Party party = partyImpMethod.obtainByName(rs.getString("PartyId"));
        Dungeon dungeon = dungeonImpMethod.obtainByName(rs.getString("DungeonId"));
        String report = rs.getString("report");
        int questId = rs.getInt("questId");

        return new QuestResult(party, dungeon, report, questId);
    }
}
