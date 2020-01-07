/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author jeff
 */
 public interface GenericClass {
    
    /**
     *
     * @param obj
     * @param sql
     * @return
     */
    public boolean insert(Object obj[],String sql);
    
    public boolean delete(int id,String sql);
    
    public boolean update(Object obj[],String sql);
    
    public ArrayList<Object> search(Object param,String sql);
    
    public Object[][] list(String sql);
    
    
}
