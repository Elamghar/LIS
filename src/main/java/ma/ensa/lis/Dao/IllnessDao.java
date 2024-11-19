package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Illness;
import ma.ensa.lis.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IllnessDao implements IllnessDAOInterface {
    @Override
    public void addIllness(Illness illness) {
        String sql = "INSERT INTO Illness (Id,nom,description) VALUES (?,?,?)";
        try(Connection connection = (Connection) new DbConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, illness.getId());
            statement.setString(2,illness.getNom());
            statement.setString(3, illness.getDescription());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Illness getIllnessById(String id) {
        String sql = "SELECT * FROM Illness WHERE Id = ?";
        Illness illness = null;
        try (Connection connection = (Connection) new DbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("nom");
                String description = resultSet.getString("description");
                illness = new Illness(id, name, description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return illness;
    }

    @Override
    public List<Illness> getAllIllness() {
        List<Illness> illnesses = new ArrayList<>();
        String sql = "SELECT * FROM Illness";

        try (Connection connection = (Connection) new DbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("Id");
                String name = resultSet.getString("nom");
                String description = resultSet.getString("description");
                illnesses.add(new Illness(id, name, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return illnesses;
    }

    @Override
    public void updateIllness(Illness illness) {
        String sql = "UPDATE Illness SET nom = ?, description = ? WHERE Id = ?";
        try (Connection connection = (Connection) new DbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, illness.getNom());
            statement.setString(2, illness.getDescription());
            statement.setString(3, illness.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteIllness(String id) {
        String sql = "DELETE FROM sickness WHERE Id = ?";
        try (Connection connection = (Connection) new DbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
