import java.sql.*;

public class Doctor{
    private Connection connection;

    public Doctor(Connection connection)
    {
        this.connection = connection;
    }

    
    public void viewDoctors()
    {
        String query = "SELECT * FROM Doctors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors : ");
            System.out.println("+------------+------------------------+--------+------------+");
            System.out.println("| Doctor ID | Name                 | Specialization         |");
            System.out.println("+------------+------------------------+--------+------------+");

            while(resultSet.next())
            {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String specialization = resultSet.getString("Specialization");
                System.out.printf("| %-9s | %-20s | %-22s |\n",id,name,specialization);
                System.out.println("+------------+------------------------+--------+------------+");
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

    } 

    public boolean getDoctorByID(int id)
    {
        String query = "SELECT * FROM Doctors WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return true;
            else
                return false;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}