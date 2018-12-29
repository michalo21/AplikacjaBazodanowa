/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modele.Produkty;

/**
 *
 * @author Arlen
 */
public class produktyDAO extends DAO<Produkty, Integer>{
    @Override
    public List<Produkty> getAll() {
         List<Produkty> lista = openSessionWithTrans().createCriteria(Produkty.class).list();
         closeSessionWithTrans();
         return lista;
    }
    public produktyDAO(){};


    
    
}
