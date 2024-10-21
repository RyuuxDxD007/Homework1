package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import DAL.Section.IDAOSection;
import DAL.Section.DAOSection;
//import DAL.Status.IStatusDAO;
//import DAL.Status.StatusDAO;

public class DAOFactory {
    Connection connection;

    public DAOFactory() {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.12/test1", "postgres", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public IDAOSection createDAOSection() {
        return new DAOSection("jdbc:postgresql://192.168.1.12/test1", "postgres", "password");
    }

    /*public IStatusDAO createStatusDAO() {
        return new StatusDAO("jdbc:postgresql://192.168.1.12/test1", "postgres", "password");
    }*/
}
