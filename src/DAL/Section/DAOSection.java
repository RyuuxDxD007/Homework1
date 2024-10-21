package DAL.Section;

import BL.Section;

import java.sql.*;
import java.util.ArrayList;

public class DAOSection implements IDAOSection {

    ArrayList<Section> list = new ArrayList<>();
    Connection connection = null;
    ResultSet set = null;
    DAOSectionPrepared preparedStatement = null;

    public DAOSection(String url, String user, String pass) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            preparedStatement = new DAOSectionPrepared(connection);
            try {
                set = preparedStatement.createDB.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                set = preparedStatement.createTable.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeAll();
        }

    }

    public ArrayList<Section> getSections() {
        try {
            set = preparedStatement.getSections.executeQuery();
            while (set.next()) {
                Section section = new Section();
                section.setNom(set.getString("nom"));
                list.add(section);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }


    public int getIDSection(String nom) {
        try {
            preparedStatement.getIDSection.setString(1, nom);
            set = preparedStatement.getIDSection.executeQuery();
            if (set.next()) {
                return set.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeAll();
        }
        return -1;
    }


    public boolean updateSection(int id, String nom) {
        try {
            preparedStatement.updateSection.setString(1, nom);
            preparedStatement.updateSection.setInt(2, id);
            preparedStatement.updateSection.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean deleteSection(int id) {
        try {
            preparedStatement.deleteSection.setInt(1, id);
            preparedStatement.deleteSection.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean addSection(String nom) {
        try {
            preparedStatement.insertSection.setString(1, nom);
            preparedStatement.insertSection.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    protected void closeAll() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (set != null) {
            try {
                set.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
