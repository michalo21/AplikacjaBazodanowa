/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modele.Producent;

/**
 *
 * @author Arlen
 */
public class producentDAO extends DAO<Producent, Integer>{
    
    @Override
    public List<Producent> getAll() {
         List<Producent> lista = openSessionWithTrans().createCriteria(Producent.class).list();
         closeSessionWithTrans();
         return lista;
    }
    public producentDAO(){};




   
    
}
