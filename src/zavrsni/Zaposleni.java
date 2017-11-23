/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsni;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author boost
 */
@Entity
@Table(name = "ZAPOSLENI")
public class Zaposleni implements Serializable {

    public Set<Pacijent> pacijenti = new HashSet<>(0);

    private ZaposleniDetails zDetails;

    private int id;
    private String ime;
    private String prezime;
    private String kontaktTelefon;
    private String posao;
    private String pol;

    public Zaposleni(String ime, String prezime, String kontaktTelefon, String posao, String pol, Set<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTelefon = kontaktTelefon;
        this.posao = posao;
        this.pol = pol;
    }

    public Zaposleni(String ime, String prezime, String kontaktTelefon, String posao, String pol) {

        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTelefon = kontaktTelefon;
        this.posao = posao;
        this.pol = pol;
    }

    public Zaposleni() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ZAPOSLENI_ID")
    public int getId() {
        return id;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "zaposleni", cascade = CascadeType.ALL)
    public ZaposleniDetails getzDetails() {
        return zDetails;
    }

    public void setzDetails(ZaposleniDetails zDetails) {
        this.zDetails = zDetails;
    }

    @OneToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "ZAPOSLENI_PACIJENTI", joinColumns = {
        @JoinColumn(name = "ZAPOSLENI_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PACIJENT_ID")})
    public Set<Pacijent> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(Set<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "IME")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Column(name = "PREZIME")
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Column(name = "TELEFON")
    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    @Column(name = "POSAO")
    public String getPosao() {
        return posao;
    }

    public void setPosao(String posao) {
        this.posao = posao;
    }

    @Column(name = "POL")
    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

}
