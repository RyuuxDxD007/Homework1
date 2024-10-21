package DAL.Section;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOSectionPrepared {

    PreparedStatement insertSection;
    PreparedStatement updateSection;
    PreparedStatement deleteSection;
    PreparedStatement getSections;
    PreparedStatement getIDSection;
    PreparedStatement createDB;
    PreparedStatement createTable;

    public DAOSectionPrepared(Connection connection) throws SQLException{

        this.insertSection = connection.prepareStatement("INSERT INTO Section (nom) VALUES (?)");

        this.updateSection = connection.prepareStatement("UPDATE Section SET nom = ? WHERE id = ?");

        this.deleteSection = connection.prepareStatement("DELETE FROM Section WHERE id = ?");

        this.getIDSection = connection.prepareStatement("SELECT id FROM Section WHERE nom = ?");

        this.getSections = connection.prepareStatement("SELECT id, nom FROM Section");

        this.createDB = connection.prepareStatement("CREATE DATABASE TEST1234");

        this.createTable = connection.prepareStatement("CREATE TABLE Section (id SERIAL PRIMARY KEY, nom VARCHAR(30) UNIQUE)");
    }



}
