import java.sql.*;
import java.util.*;

public class Patient{
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection,Scanner scanner)
    {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient()
    {
        scanner.nextLine();
        System.out.println("Enter Patient's Name : ");
        String name = scanner.nextLine();
        System.out.println("Enter Patient's Age : ");
        int age = scanner.nextInt();
        System.out.println("Enter Patient's Gender : ");
        String gender = scanner.next();

        try 
        {
            String query = "INSERT INTO Patients(Name,Age,Gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0)
                System.out.println("Patient Added Successfully!!");
            else
                System.out.println("Failed to Add Patient!!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void viewPatients()
    {
        String query = "SELECT * FROM Patients";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients : ");
            System.out.println("+------------+----------------------+--------+---------+");
            System.out.println("| Patient ID | Name                 | Age    | Gender  |");
            System.out.println("+------------+----------------------+--------+---------+");

            while(resultSet.next())
            {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String gender = resultSet.getString("Gender");
                System.out.printf("| %-10s | %-20s | %-6s | %-7s |\n",id,name,age,gender);
                System.out.println("+------------+----------------------+--------+---------+");
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientByID(int id)
    {
        String query = "SELECT * FROM Patients WHERE ID=?";
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