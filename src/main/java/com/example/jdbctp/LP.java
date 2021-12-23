package com.example.jdbctp;

import java.sql.*;
import java.util.*;

public class LP {
    private static String jdbUrl="jdbc:mysql://localhost:3306/bdtest1";
    private  static String jdbname="root";
    private  static String jdbpassword="";
    private static final String INSERT_PERSONNE_SQL = "INSERT INTO personne" + "  (nom, prenom, number) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_PERSONNE_BY_ID = "select id,nom,prenom,number from personne where id =?";
    private static final String SELECT_ALL_PERSONNE = "select * from personne";
    private static final String DELETE_PERSONNE_SQL = "delete from personne where id = ?;";
    private static final String UPDATE_PERSONNE_SQL = "update personne set nom = ?,prenom= ?, number =? where id = ?;";
    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbUrl, jdbname, jdbpassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public static void insertUser(Personne personne) throws SQLException {
        System.out.println(INSERT_PERSONNE_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONNE_SQL)) {
            preparedStatement.setString(1, personne.getName());
            preparedStatement.setString(2, personne.getPrenom());
            preparedStatement.setString(3, personne.getTel());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public static Personne selectPersonne(int id) {
        Personne personne = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONNE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                personne = new Personne(id, nom, prenom, tel);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return personne;
    }

    public static List<Personne> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Personne> personnes = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONNE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                personnes = (List<Personne>) new Personne(id, nom, prenom, tel);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return personnes;
    }
    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
}
}
