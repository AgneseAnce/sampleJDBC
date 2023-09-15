import owner.Owner;

import java.sql.*;
import java.util.ArrayList;

public class SampleWorkingWithOwnerTable {
    // Functions as a repository

    public Owner createOwners(Owner owner) {
        try {
            // Create a connection by providing Java MySQL data
            // URL contains jdbc, DB type, //location, default code of local DBs, DB name
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create SQL commands
            String query = "INSERT INTO owners(ownerName, age, email) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, owner.getOwnerName());
            statement.setInt(2, owner.getAge());
            statement.setString(3, owner.getEmail());

            // Execute SQL commands
            statement.executeUpdate();
            // .executeUpdate() - executes query and gives feedback on updated items
            // execute() - returns boolean on success of sth;
            // .executeQuery() - to obtain data from DB

            // Select the last created ID
            // Returns a result set with primary keys
            ResultSet resultSet = statement.getGeneratedKeys();
            // Checks if the query has any results to return
            if (resultSet.next()) {
                int ownerId = resultSet.getInt(1);
                return this.getOwnerById(ownerId);
            } else {
                throw new Exception("Unable to find newly added owner");
            }

            // Get the last created owner object
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
//        } finally {
//            connection.close();
//        }

                return null;
    }

    public Owner getOwnerById(int ownerID) {
        try {
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM owners WHERE owners.id = ? LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ownerID);
            ResultSet resultSet = preparedStatement.executeQuery(); // Because some results are returned

            // Process the result
                // If statement to avoid exceptions
            if (resultSet.next()){
                // Create Owner object from resultSet
                return new Owner(
                        resultSet.getInt("id"),
                        resultSet.getString("ownerName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("createdAt"),
                        resultSet.getTimestamp("lastUpdated")
                );
            } else {
                throw new Exception("Unable to find owner with ID " + ownerID);
            }
            // Return the result as Java objects

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public ArrayList<Owner> getAllOwners() {
        ArrayList<Owner> owners = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM owners";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Use executeQuery when output is expected
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Convert ResultSet into Java object
                Owner owner = new Owner(
                        resultSet.getInt("id"),
                        resultSet.getString("ownerName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("createdAt"),
                        resultSet.getTimestamp("lastUpdated")
                );
                // Add Owner to the ArrayList
                owners.add(owner);
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
            return owners;
        }

    public void updateOwner(Owner ownerToUpdate) {
        try {
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "UPDATE owners SET ownerName = ?, age = ?, " +
                    "email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ownerToUpdate.getOwnerName());
            preparedStatement.setInt(2, ownerToUpdate.getAge());
            preparedStatement.setString(3, ownerToUpdate.getEmail());
            preparedStatement.setInt(4, ownerToUpdate.getId());

            // Optional; checks if the item has been updated
            int result = preparedStatement.executeUpdate();
            if(result != 1) throw new Exception("Problem occurred during update");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        } // General exception
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public void deleteOwner(int id) {
        try {
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "root";
            String password = "1234";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "DELETE FROM owners WHERE id = ?";
//         Alternative:   String query = "DELETE FROM owners WHERE id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            // Optional; checks if the item has been deleted
            int result = preparedStatement.executeUpdate();
            if(result != 1) throw new Exception("Deleting item failed");
            connection.close(); // Frees up the connection to prevent overload
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}