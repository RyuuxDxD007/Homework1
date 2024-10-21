import DAL.DAOFactory;
import DAL.Section.IDAOSection;
//import DAL.Status.IDAOStatus;

public class App {
    public static void main(String[] args) throws Exception {
        // Nous pr´eparons l'usine pour cr´eer nos DAO.
        DAOFactory factory = new DAOFactory();

        // Nous pr´eparerons nos diff´erents DAO.
        IDAOSection sectionDAO = factory.createDAOSection();
        //IDAOStatus statusDAO = factory.createDAOStatus();

        // Nous pouvons manipuler nos objets cr´e´es.
        sectionDAO.addSection("Testing");
        //statusDAO.addStatus("Testing");

        // Une fois termin´e, n'oublions pas de fermer les connexions:
        //sectionDAO.close;
        /*statusDAO.close();
        factory.close();*/
    }
}
