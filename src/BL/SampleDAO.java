package BL;
import java.sql.SQLException;
import java.util.ArrayList;
import DAL.Section.IDAOSection;
import DAL.Section.DAOSection;

public class SampleDAO {
    public static void main(String[] args) throws Exception {
        IDAOSection sectionDAO = new DAOSection("jdbc:postgresql://192.168.1.57/test1", "postgres", "password");

        sectionDAO.addSection("Comptabilite");
        sectionDAO.addSection("Erreur");
        sectionDAO.addSection("A Changer");

        ArrayList<Section> sections = sectionDAO.getSections();
        for (Section section : sections) {
            if (section.getNom().equals("Erreur")) {
                sectionDAO.deleteSection(section.getId());
            } else if (section.getNom().equals("A Changer")) {
                sectionDAO.updateSection(section.getId(), "Changement");
            }
        }

        System.out.println(sectionDAO.getIDSection("Comptabilite"));
    }
}

