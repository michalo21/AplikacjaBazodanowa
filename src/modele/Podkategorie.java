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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Arlen
 */
@Entity
@Table(name="Podkategorie")
public class Podkategorie implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_podkategorii", unique=true)
    private int id_podkategorii;
    
    @Column(name="nazwa_podkategorii")
    private String nazwa_podkategorii;
    
    @ManyToOne
    private Kategorie kategorie;

    public Kategorie getKategorie() {
         return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }
        
    public int getId_podkategorii() {
        return id_podkategorii;
    }

    public void setId_podkategorii(int id_podkategorii) {
        this.id_podkategorii = id_podkategorii;
    }

    public String getNazwa_podkategorii() {
        return nazwa_podkategorii;
    }

    public void setNazwa_podkategorii(String nazwa_podkategorii) {
        this.nazwa_podkategorii = nazwa_podkategorii;
    }

    public Podkategorie(int id_podkategorii, String nazwa_podkategorii) {
        this.id_podkategorii = id_podkategorii;
        this.nazwa_podkategorii = nazwa_podkategorii;
    }

    public Podkategorie() {
    }   
}
