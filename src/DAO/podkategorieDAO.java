/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modele.Podkategorie;

/**
 *
 * @author Arlen
 */
public class podkategorieDAO extends DAO<Podkategorie, Integer> {
    
    @Override
    public List<Podkategorie> getAll() {
         
         List<Podkategorie> lista = openSessionWithTrans().createCriteria(Podkategorie.class).list();
         closeSessionWithTrans();
         return lista;
    }
  
    
       public podkategorieDAO(){};



   

    
    
}
