package cado;


import dao.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeff
 */
public class CADO {

    PreparedStatement ps;
    ResultSet rs;

    private static final ConexionBD con = ConexionBD.EstadoCon();

    public boolean insert(String sql, Object param[]) throws SQLException {

        boolean exito = false;
        try {
            ps = con.getConexion().prepareStatement(sql);
            
            for (int i = 0; i < param.length; i++) {
              
                switch (param[i].getClass().toString()) {

                    case "class java.lang.String":
                        ps.setString(i + 1, (String) param[i]);
                        break;
                    case "class java.lang.Integer":
                        ps.setInt(i + 1, (int) param[i]);
                        break;
                    case "class java.lang.Float":
                        ps.setFloat(i + 1, (Float) param[i]);
                        break;
                    default:
                        System.out.println("no se encontro el tipo de dato");
                        break;
                }

            }

            int res = ps.executeUpdate();

            if (res > 0) {
                exito = true;
            }

        } catch (SQLException ex) {
            System.out.println("error en cado " + ex.getLocalizedMessage());

        }

        return exito;
    }

    public boolean delete(String sql, Object param[]) throws SQLException {
        try {
            ps = con.getConexion().prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setInt(i + 1, (int) param[i]);
            }
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error " + ex.getLocalizedMessage());
        }

        return false;
    }

    public boolean update(String sql, Object param[]) throws SQLException {

        boolean exito = false;
        try {
            ps = con.getConexion().prepareStatement(sql);

            for (int i = 0; i < param.length; i++) {

                switch (param[i].getClass().toString()) {
                    case "class java.lang.String":
                        ps.setString(i + 1, (String) param[i]);

                        break;
                    case "class java.lang.Integer":
                        ps.setInt(i + 1, (int) param[i]);
                        break;
                    case " class java.lang.Float":
                        ps.setFloat(i + 1, (Float) param[i]);
                        break;
                    default:
                        System.out.println("no se encontro el tipo de dato");
                        break;
                }

            }
            int res = ps.executeUpdate();
            if (res > 0) {
                exito = true;
            }

        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }

        return exito;
    }

    public Object[][] groupData(String sql) throws SQLException {
        int row = 0;
        Object[][] obj = null;
        int column;
        ps = con.getConexion().prepareStatement(sql);
        rs = ps.executeQuery();

        try {
            if (rs.isBeforeFirst()) {
                rs.last();
                row = rs.getRow();
            }
            rs.beforeFirst();

            column = rs.getMetaData().getColumnCount();
            obj = new Object[row][column];
            int i = 0;
            while (rs.next() && i < row) {
                for (int j = 0; j < column; j++) {
                    switch (rs.getMetaData().getColumnClassName(j + 1)) {
                        case "java.lang.String":
                        case "java.sql.Date":
                            obj[i][j] = rs.getString(j + 1);
                            // System.out.println(rs.getString(j+1));
                            break;
                        case "java.lang.Integer":
                            obj[i][j] = rs.getInt(j + 1);
                            // System.out.println(rs.getString(j+1));
                            break;
                        case "java.sql.Boolean":
                            obj[i][j] = rs.getBoolean(j + 1);
                            // System.out.println(rs.getString(j+1));
                            break;
                        case "java.lang.Short":
                            obj[i][j] = rs.getShort(j + 1);
                            //System.out.println(rs.getString(j+1));
                            break;
                        case "java.lang.Long":
                            obj[i][j] = rs.getLong(j + 1);
                            //System.out.println(rs.getString(j+1));
                            break;
                        case "java.lang.Float":
                            obj[i][j] = rs.getFloat(j + 1);
                            //System.out.println(rs.getString(+1));
                            break;
                        case "java.lang.Double":
                            obj[i][j] = rs.getDouble(j + 1);
                            //System.out.println(rs.getString(j+1));
                            break;
                        default:
                            obj[i][j] = rs.getObject(j + 1);
                            //System.out.println(rs.getString(j+1));
                            break;
                    }
                }
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("eror " + ex.getMessage());
        }

        return obj;
    }

    public ResultSet search(String sql, Object param) throws SQLException {
        try {
            ps = con.getConexion().prepareStatement(sql);
            ps.setObject(1, param);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(CADO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            ps.close();
        }
        return rs;
    }

}
