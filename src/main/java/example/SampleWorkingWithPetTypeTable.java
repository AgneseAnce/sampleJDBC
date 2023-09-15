import entities.PetType;
import service.Database;

import java.sql.*;
import java.util.ArrayList;

public class SampleWorkingWithPetTypeTable {

    // Make, create, read, update and delete for the PetType table

    public PetType createPetTypes(PetType pettype) {
        try {
            Database database = new Database();
            Connection connection = database.getConnected();

            // Create SQL commands
            String query = "INSERT INTO petTypes(type) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pettype.getType());

            // Execute SQL commands
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            // Checks if the query has any results to return
            if (resultSet.next()) {
                int petTypeId = resultSet.getInt(1);
                return this.getPetTypeById(petTypeId);
            } else {
                throw new Exception("Unable to find newly added pet type");
            }

            // Get the last created pettype object
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public PetType getPetTypeById(int petTypeId) {
        try {
            Database database = new Database();
            Connection connection = database.getConnected();

            String query = "SELECT * FROM petTypes WHERE petTypes.id = ? LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, petTypeId);
            ResultSet resultSet = preparedStatement.executeQuery(); // Because some results are returned

            // Process the result
            // If statement to avoid exceptions
            if (resultSet.next()){
                // Create Owner object from resultSet
                return new PetType(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("createdAt"),
                        resultSet.getTimestamp("lastUpdated")
                );
            } else {
                throw new Exception("Unable to find petType with ID " + petTypeId);
            }
            // Return the result as Java object

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public ArrayList<PetType> getAllPetTypes() {
        ArrayList<PetType> petTypes = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnected();

            String query = "SELECT * FROM petTypes";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Use executeQuery when output is expected
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Convert ResultSet into Java object
                PetType petType = new PetType(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("createdAt"),
                        resultSet.getTimestamp("lastUpdated")
                );
                // Add Pettype to the ArrayList
                petTypes.add(petType);
            }
            connection.close();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return petTypes;
    }

    public void displayArray(){
        ArrayList<PetType> petTypes = new ArrayList<>(getAllPetTypes());
        StringBuilder stringBuilder = new StringBuilder();
        for (PetType petType : petTypes){
            stringBuilder.append(petType.toString())
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    public void updatePetType(PetType petTypeToUpdate) {
        try {
            Database database = new Database();
            Connection connection = database.getConnected();

            String query = "UPDATE petTypes SET type = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, petTypeToUpdate.getType());
            preparedStatement.setInt(2, petTypeToUpdate.getId());

            // Optional; checks if the item has been updated
            int result = preparedStatement.executeUpdate();
            if(result != 1) throw new Exception("Problem occurred during update");
            connection.close();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        } // General exception
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public void deletePetType(int id) {
        try {
            Database database = new Database();
            Connection connection = database.getConnected();

            String query = "DELETE FROM petTypes WHERE id = ?";
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
