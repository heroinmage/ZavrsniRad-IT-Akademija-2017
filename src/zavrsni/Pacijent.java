/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsni;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author boost
 */
@Entity
@Table(name="PACIJENTI")
public class Pacijent implements Serializable {

    
    Calendar calendar = Calendar.getInstance();
    
   
    private Zaposleni zaposleni;
    
    
    private int pacijentid;
    private String ime;
    private String prezime;
   
    private String kontaktTelefon;
    private String usluga;
    private String jmbg;
    private String pol;
    
     public Pacijent() {
    }
    @Column(name="KALENDAR")
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Pacijent(String ime, String prezime, String usluga, String jmbg) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;

    }

  
    @Id
    @GeneratedValue()
    @Column(name = "PACIJENT_ID")
    public int getPacijentid() {
        return pacijentid;
    }
    @Column(name="JMBG")
    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
    @Column(name="POL")
    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
    @Column(name="USLUGA")
    public String getUsluga() {
        return usluga;
    }

    public void setUsluga(String usluga) {
        this.usluga = usluga;
    }
    
    public Pacijent(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public Pacijent(String ime, String prezime, String kontaktTelefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTelefon = kontaktTelefon;
    }
    @Column(name="IME")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    @Column(name="PREZIME", nullable = false, length = 15)
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    @Column(name="TELEFON", nullable = false, length = 15)
    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    

    public void setPacijentid(int pacijentid) {
        this.pacijentid = pacijentid;
    }

    public Pacijent( String ime, String prezime, String kontaktTelefon, String usluga, String jmbg, String pol) {
        
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTelefon = kontaktTelefon;
        this.usluga = usluga;
        this.jmbg = jmbg;
        this.pol = pol;
    }

}
