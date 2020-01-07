/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import cado.CADO;
import Interface.GenericClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garci
 */
class genericClass implements GenericClass {

    protected PreparedStatement ps;
    protected ResultSet rs;
    CADO cd = new CADO();
    int row;
    int column;

    @Override
    public boolean insert(Object[] obj, String sqlInsert) {
        try {
            Object[] param = new Object[obj.length];
            System.arraycopy(obj, 0, param, 0, obj.length);
            if (cd.insert(sqlInsert, param)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(genericClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id, String sqlDelete) {
        try {
            Object[] param = new Object[1];
            param[0] = id;
            if (cd.delete(sqlDelete, param)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(genericClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean update(Object[] obj, String sqlUpdate) {
        try {
            Object[] param = new Object[obj.length];
            System.arraycopy(obj, 0, param, 0, obj.length);
            if (cd.update(sqlUpdate, param)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(genericClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Object> search(Object filter, String sqlSearch) {

        ArrayList<Object> obj = new ArrayList();
        try {
            rs = cd.search(sqlSearch, filter);
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount() - 1; i++) {
                    obj.add(rs.getObject(i + 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(genericClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }

    @Override
    public Object[][] list(String sql) {
        Object[][] obj = null;
        try {
            obj = cd.groupData(sql);             
        } catch (SQLException ex) {
            Logger.getLogger(genericClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

}
