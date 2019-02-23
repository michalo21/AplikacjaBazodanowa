/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author Arlen
 */
@Entity
@Table(name="Uzytkownicy")
public class Uzytkownicy implements Serializable {

    private static final long serialVersionUID = 5L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_uzytkownika", unique=true)
    private int id_uzytkownika;
    
    @Column(name="nazwa_uzytkownika")
    private String nazwa_uzytkownika;
    
    @Column(name="haslo_uzytkownika")
    private String haslo_uzytkownika;

    public int getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public String getNazwa_uzytkownika() {
        return nazwa_uzytkownika;
    }

    public void setNazwa_uzytkownika(String nazwa_uzytkownika) {
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    }

    public String getHaslo_uzytkownika() {
        return haslo_uzytkownika;
    }

    public void setHaslo_uzytkownika(String haslo_uzytkownika) {
        this.haslo_uzytkownika = haslo_uzytkownika;
    }
    
    
   

    public Uzytkownicy() {
    }
    
   


    
    
}
