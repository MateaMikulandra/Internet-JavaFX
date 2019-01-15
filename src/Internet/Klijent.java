
package Internet;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "internet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klijent.findAll", query = "SELECT k FROM Klijent k"),
    @NamedQuery(name = "Klijent.findByKlijentId", query = "SELECT k FROM Klijent k WHERE k.klijentId = :klijentId"),
    @NamedQuery(name = "Klijent.findByIme", query = "SELECT k FROM Klijent k WHERE k.ime = :ime"),
    @NamedQuery(name = "Klijent.findByPrezime", query = "SELECT k FROM Klijent k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Klijent.findByAdresa", query = "SELECT k FROM Klijent k WHERE k.adresa = :adresa"),
    @NamedQuery(name = "Klijent.findByBrzina", query = "SELECT k FROM Klijent k WHERE k.brzina = :brzina"),
    @NamedQuery(name = "Klijent.findByProtok", query = "SELECT k FROM Klijent k WHERE k.protok = :protok"),
    @NamedQuery(name = "Klijent.findByUgovor", query = "SELECT k FROM Klijent k WHERE k.ugovor = :ugovor"),
    @NamedQuery(name = "Klijent.findByUuid", query = "SELECT k FROM Klijent k WHERE k.uuid = :uuid")})
public class Klijent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "klijent_id")
    private Integer klijentId;
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Column(name = "adresa")
    private String adresa;
    @Basic(optional = false)
    @Column(name = "brzina")
    private String brzina;
    @Basic(optional = false)
    @Column(name = "protok")
    private String protok;
    @Basic(optional = false)
    @Column(name = "ugovor")
    private String ugovor;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    public Klijent() {
    }

    public Klijent(Integer klijentId) {
        this.klijentId = klijentId;
    }

    public Klijent(Integer klijentId, String ime, String prezime, String adresa, String brzina, String protok, String ugovor, String uuid) {
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.brzina = brzina;
        this.protok = protok;
        this.ugovor = ugovor;
        this.uuid = uuid;
    }

    public Integer getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Integer klijentId) {
        this.klijentId = klijentId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrzina() {
        return brzina;
    }

    public void setBrzina(String brzina) {
        this.brzina = brzina;
    }

    public String getProtok() {
        return protok;
    }

    public void setProtok(String protok) {
        this.protok = protok;
    }

    public String getUgovor() {
        return ugovor;
    }

    public void setUgovor(String ugovor) {
        this.ugovor = ugovor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klijentId != null ? klijentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Klijent)) {
            return false;
        }
        Klijent other = (Klijent) object;
        if ((this.klijentId == null && other.klijentId != null) || (this.klijentId != null && !this.klijentId.equals(other.klijentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vasa porudzbina:\nBroj prodaje: "+" "+uuid+" "+"\nIme: "+" "+ime+" "+"Prezime: "+" "+prezime+" "+"Adresa: "+" "+adresa+" "+"Brzina: "+" "+brzina+" "+"Protok: "+" "+protok+" "+"Ugovor: "+" "+ugovor;
    }
        public void Dodaj() throws SQLException {
        Connection conn = DbConnector.getConnection();
        PreparedStatement st = conn.prepareStatement("insert into internet (ime,prezime,adresa,brzina,protok,ugovor,uuid)values(?,?,?,?,?,?,?)");
        st.setString(1, getIme());
        st.setString(2, getPrezime());
        st.setString(3, getAdresa());
        st.setString(4, getBrzina());
        st.setString(5, getProtok());
        st.setString(6, getUgovor());
        st.setString(7, getUuid());
        st.execute();
    }
    
    public void Obrisi(String uuid) throws SQLException {
        Connection conn = DbConnector.getConnection();
        PreparedStatement st = conn.prepareStatement("delete from internet where uuid=?");
        st.setString(1, uuid);
        st.executeUpdate();
    }
    public static List<Klijent> Pregled() throws SQLException {
        Connection conn = DbConnector.getConnection();
        List<Klijent> ls = new ArrayList<>();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select * from internet");
        while (rs.next()) {
            Klijent k = new Klijent();
            k.setKlijentId(rs.getInt(1));
            k.setIme(rs.getString(2));
            k.setPrezime(rs.getString(3));
            k.setAdresa(rs.getString(4));
            k.setBrzina(rs.getString(5));
            k.setProtok(rs.getString(6));
            k.setUgovor(rs.getString(7));
            k.setUuid(rs.getString(8));
            ls.add(k);
        }
        return ls;
    }
}
