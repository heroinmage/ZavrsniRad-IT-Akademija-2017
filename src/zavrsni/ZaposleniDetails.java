/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsni;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author boost
 */
@Entity
@Table(name = "ZAPOSLENIDETAILS")
public class ZaposleniDetails implements Serializable {

    private int zid;

    private Zaposleni zaposleni;
    private String username;
    private String password;
    private String jmbg;

    private int adminAcc;

    public void setZid(int zid) {
        this.zid = zid;
    }

    @Id
    @GeneratedValue()
    public int getZid() {
        return zid;
    }

    public ZaposleniDetails() {
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public int getAdminAcc() {
        return adminAcc;
    }

    public void setAdminAcc(int adminAcc) {
        this.adminAcc = adminAcc;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ZaposleniDetails(String username, String password, String jmbg, int adminAcc) {
        this.username = username;
        this.password = password;
        this.jmbg = jmbg;
        this.adminAcc = adminAcc;
    }
}
