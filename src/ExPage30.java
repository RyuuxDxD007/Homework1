import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ExPage30 {
    public static void main(String[] args) throws Exception {

        // Information de connexion
        final String url = "jdbc:postgresql://127.0.0.1/ecole";
        final String user = "postgres";
        final String password = "Baptiste0307";

        Connection connexion = null;
        Statement requete = null;
        ResultSet resultSet = null;

        try {

            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connecté à la base de données." + url);

            requete = connexion.createStatement();

            //try{

                //requete.executeUpdate("CREATE DATABASE ecole");
            //}catch (SQLException e){
                
                //System.out.println("Base de données ecole déjà existante.\n" + e.getMessage() );
            //}
            
            requete.executeUpdate("CREATE TABLE IF NOT EXISTS Section (id SERIAL PRIMARY KEY, NOM VARCHAR(30))");
            requete.executeUpdate("CREATE TABLE IF NOT EXISTS Cours (id SERIAL PRIMARY KEY, id_section INTEGER REFERENCES Section, NOM VARCHAR(30))");

            requete.executeUpdate("INSERT INTO Section (NOM) VALUES ('Informatique de gestion'), ('Droit')");

            requete.executeUpdate("INSERT INTO Cours (id_section, NOM) VALUES "
                + "((SELECT id FROM Section WHERE NOM='Informatique de gestion'), 'Base des réseaux'),"
                + "((SELECT id FROM Section WHERE NOM='Informatique de gestion'), 'Systèmes d''exploitation'),"
                + "((SELECT id FROM Section WHERE NOM='Informatique de gestion'), 'Programmation orientée objet'),"
                + "((SELECT id FROM Section WHERE NOM='Droit'), 'Droit civil'),"
                + "((SELECT id FROM Section WHERE NOM='Droit'), 'Droit commercial')");


            resultSet = requete.executeQuery("SELECT Section.NOM as nom_section, Cours.NOM as nom_cours FROM Cours "
                + "JOIN Section ON Cours.id_section = Section.id");

            while (resultSet.next()) {
                System.out.println("Section: " + resultSet.getString("nom_section") + " - Cours: " + resultSet.getString("nom_cours"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {

            if (resultSet != null){
                resultSet.close();
            }

            if (requete != null){
                requete.close();
            }

            if (connexion != null) {
                connexion.close();
            }
        }
    }
}

