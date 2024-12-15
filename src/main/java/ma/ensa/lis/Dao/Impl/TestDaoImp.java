package ma.ensa.lis.Dao.Impl;

import ma.ensa.lis.Dao.TestDao;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.models.TestStatus;
import ma.ensa.lis.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoImp implements TestDao {

    private final DbConnection dbConnection;

    public TestDaoImp(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public TestLab findById(String id) {

        String query = "SELECT * FROM Test WHERE id = ?";

        try(PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToTest(rs);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TestLab> findAll() {

        List<TestLab> tests = new ArrayList<>();
        String query = "SELECT * FROM Test";

        try(Statement stmt = dbConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while(rs.next()) {
                tests.add(mapRowToTest(rs));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    @Override
    public void save(TestLab test) {

        String query = "INSERT INTO Test (id, name, category, testDate, expectedCompletionDate, status, result, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
            stmt.setString(1, test.getId());
            stmt.setString(2, test.getName());
            stmt.setString(3, test.getCategory());


            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TestLab test) {
        String query = "UPDATE Test SET name = ?, category = ?, testDate = ?, expectedCompletionDate = ?, status = ?, result = ?, price = ? WHERE id = ?";
        try(PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
            stmt.setString(1, test.getName());
            stmt.setString(2, test.getCategory());
            stmt.setString(8, test.getId());
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {

        String query = "DELETE FROM Test WHERE id = ?";

        try(PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private TestLab mapRowToTest(ResultSet rs) throws SQLException {

        TestLab test = new TestLab();
        test.setId(rs.getString("id"));
        test.setName(rs.getString("name"));
        test.setCategory(rs.getString("category"));

        return test;

    }
}
