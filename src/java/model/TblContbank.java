/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joels
 */
@Entity
@Table(name = "tbl_contbank", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblContbank.findAll", query = "SELECT t FROM TblContbank t")
    , @NamedQuery(name = "TblContbank.findByIdContbank", query = "SELECT t FROM TblContbank t WHERE t.idContbank = :idContbank")
    , @NamedQuery(name = "TblContbank.findByIdCorretista", query = "SELECT t FROM TblContbank t WHERE t.idCorretista = :idCorretista")
    , @NamedQuery(name = "TblContbank.findByIdBank", query = "SELECT t FROM TblContbank t WHERE t.idBank = :idBank")
    , @NamedQuery(name = "TblContbank.findByTipoconta", query = "SELECT t FROM TblContbank t WHERE t.tipoconta = :tipoconta")
    , @NamedQuery(name = "TblContbank.findByTipocard", query = "SELECT t FROM TblContbank t WHERE t.tipocard = :tipocard")
    , @NamedQuery(name = "TblContbank.findByNamecard", query = "SELECT t FROM TblContbank t WHERE t.namecard = :namecard")
    , @NamedQuery(name = "TblContbank.findByNuncard", query = "SELECT t FROM TblContbank t WHERE t.nuncard = :nuncard")
    , @NamedQuery(name = "TblContbank.findByCvv", query = "SELECT t FROM TblContbank t WHERE t.cvv = :cvv")
    , @NamedQuery(name = "TblContbank.findByDvencimento", query = "SELECT t FROM TblContbank t WHERE t.dvencimento = :dvencimento")
    , @NamedQuery(name = "TblContbank.findByAgencont", query = "SELECT t FROM TblContbank t WHERE t.agencont = :agencont")})
public class TblContbank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contbank", nullable = false)
    private Integer idContbank;
    @Column(name = "id_corretista")
    private Integer idCorretista;
    @Column(name = "id_bank")
    private Integer idBank;
    @Column(length = 20)
    private String tipoconta;
    @Column(length = 20)
    private String tipocard;
    @Column(length = 30)
    private String namecard;
    @Column(length = 2147483647)
    private String nuncard;
    @Column(length = 3)
    private String cvv;
    @Temporal(TemporalType.DATE)
    private Date dvencimento;
    @Column(length = 15)
    private String agencont;

    public TblContbank() {
    }

    public TblContbank(Integer idContbank) {
        this.idContbank = idContbank;
    }

    public Integer getIdContbank() {
        return idContbank;
    }

    public void setIdContbank(Integer idContbank) {
        this.idContbank = idContbank;
    }

    public Integer getIdCorretista() {
        return idCorretista;
    }

    public void setIdCorretista(Integer idCorretista) {
        this.idCorretista = idCorretista;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getTipocard() {
        return tipocard;
    }

    public void setTipocard(String tipocard) {
        this.tipocard = tipocard;
    }

    public String getNamecard() {
        return namecard;
    }

    public void setNamecard(String namecard) {
        this.namecard = namecard;
    }

    public String getNuncard() {
        return nuncard;
    }

    public void setNuncard(String nuncard) {
        this.nuncard = nuncard;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getDvencimento() {
        return dvencimento;
    }

    public void setDvencimento(Date dvencimento) {
        this.dvencimento = dvencimento;
    }

    public String getAgencont() {
        return agencont;
    }

    public void setAgencont(String agencont) {
        this.agencont = agencont;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContbank != null ? idContbank.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblContbank)) {
            return false;
        }
        TblContbank other = (TblContbank) object;
        if ((this.idContbank == null && other.idContbank != null) || (this.idContbank != null && !this.idContbank.equals(other.idContbank))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblContbank[ idContbank=" + idContbank + " ]";
    }
    
}
