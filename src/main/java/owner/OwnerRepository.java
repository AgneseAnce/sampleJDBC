import entities.Owner;

import java.sql.*;
import java.util.ArrayList;

public class OwnerRepository {
    private String dbUserName;
    private String dbPassword;
    private Connection connection;

    private String url;

    public OwnerRepository() {
        this.initializeConnection();
    }

    private void initializeConnection() {
        try {
            this.dbUserName = "";
            this.dbPassword = "";
            this.url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            this.connection = DriverManager.getConnection(url, dbUserName, dbPassword);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Owner createOwners(Owner owner) throws SQLException, OwnerRepositoryActionFailedException {

        String query = "INSERT INTO owners(ownerName, age, email) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, owner.getOwnerName());
        statement.setInt(2, owner.getAge());
        statement.setString(3, owner.getEmail());

        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            int ownerId = resultSet.getInt(1);
            return this.getOwnerById(ownerId);
        }
        throw new OwnerRepositoryActionFailedException("Creating item failed");
    }

    public Owner getOwnerById(int ownerID) throws SQLException, OwnerRepositoryActionFailedException {

        String query = "SELECT * FROM owners WHERE owners.id = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ownerID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return this.convertResultSetToOwner(resultSet);
        }
        throw new OwnerRepositoryActionFailedException("Something went wrong again");
    }
    public ArrayList<Owner> getAllOwners() throws SQLException {
        ArrayList<Owner> owners = new ArrayList<>();

            String query = "SELECT * FROM owners";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                owners.add(this.convertResultSetToOwner(resultSet));
            }

        return owners;
    }

    private Owner convertResultSetToOwner(ResultSet resultSet) throws SQLException {
        return new Owner(
                resultSet.getInt("id"),
                resultSet.getString("ownerName"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("lastUpdated")
        );
    }

}
