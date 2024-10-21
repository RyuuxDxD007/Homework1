import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    public static void main(String[] args) throws Exception {

        // Information de connection
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String password = "toto1234";

        // Connexion à la base de données.
        java.sql.Connection connection = null;
        Statement requete = null;
        try {

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connecté à la base de données." + url);

            requete = connection.createStatement();

            try{

                requete.executeUpdate("CREATE DATABASE UE1392");
            }catch (SQLException e){
                
                System.out.println("Base de données UE1392 déjà existante.\n" + e.getMessage() );
            }
            
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {

            // Fermer les requêtes
            if (requete != null){
                requete.close();
            }

            // Toujours fermer la connection à la base de données.
            if (connection != null) {
                connection.close();
            }
        }
    }
}
