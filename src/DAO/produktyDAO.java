/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
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
    public byte[] insertPhoto(TextArea g){
            File file = new File(g.getText());
            byte[] image = new byte[(int)file.length()];
            FileInputStream inputStream = null;
            try{
              inputStream = new FileInputStream(file);
              inputStream.read(image);
            } catch(IOException e){   
            } finally{
                try{
                   if(inputStream!=null){
                      inputStream.close();
                   }
                } catch (IOException e){ 
                }
            }
            return image;
         }         
    
    public produktyDAO(){};


    
    
}
