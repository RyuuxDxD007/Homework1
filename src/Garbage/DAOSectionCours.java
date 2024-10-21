package Garbage;
import BL.Section;
import DAL.Section.IDAOSection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSectionCours implements IDAOSection {
    private Connection connexion;
    private Statement statement;

    public DAOSectionCours(String url, String user, String password) {
        try {
            this.connexion = DriverManager.getConnection(url, user, password);
            this.statement = connexion.createStatement();
            try {
                this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS Section (id SERIAL PRIMARY KEY, nom VARCHAR(30))");
            } catch (SQLException e) {
                // La table existe d´ej`a. Log pour le cas o`u.
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void finalize() {
        if (this.statement != null) {
            try {
                this.statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (this.connexion != null) {
            try {
                this.connexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<Section> getSections() {
        ArrayList<Section> listeSection = new ArrayList<Section>();
        try {
            ResultSet set = this.statement.executeQuery("SELECT id, nom FROM Section");
            while (set.next()) {
                Section section = new Section(set.getInt(1), set.getString(2));
                listeSection.add(section);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listeSection;

    }

    @Override
    public int getIDSection(String nom) {
        int id = -1;
        try {
            ResultSet set = this.statement.executeQuery("SELECT id FROM Section where nom ='" + nom + "'");
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public boolean updateSection(int id, String nom) {
        try {
            this.statement.executeUpdate("UPDATE Section SET nom='" + nom + "' WHERE id='" + id + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSection(int id) {
        try {
            this.statement.executeUpdate("DELETE FROM Section WHERE id='" + id + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addSection(String nom) {
        try {
            this.statement.executeUpdate("INSERT into Section (nom) VALUES('" + nom + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}

