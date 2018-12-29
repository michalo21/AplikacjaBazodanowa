/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import modele.Kategorie;

/**
 *
 * @author Arlen
 */
public class kategorieDAO extends DAO<Kategorie, Integer> {
     
    @Override
    public List<Kategorie> getAll() {
         
         List<Kategorie> lista = openSessionWithTrans().createCriteria(Kategorie.class).list();
         closeSessionWithTrans();
         return lista;
    };
   
    
    public kategorieDAO(){};
}
