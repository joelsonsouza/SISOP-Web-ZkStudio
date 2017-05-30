/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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

/**
 *
 * @author joels
 */
@Entity
@Table(name = "tbl_bank", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBank.findAll", query = "SELECT t FROM TblBank t")
    , @NamedQuery(name = "TblBank.findByIdBank", query = "SELECT t FROM TblBank t WHERE t.idBank = :idBank")
    , @NamedQuery(name = "TblBank.findByNomebank", query = "SELECT t FROM TblBank t WHERE t.nomebank = :nomebank")})
public class TblBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bank", nullable = false)
    private Integer idBank;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String nomebank;

    public TblBank() {
    }

    public TblBank(Integer idBank) {
        this.idBank = idBank;
    }

    public TblBank(Integer idBank, String nomebank) {
        this.idBank = idBank;
        this.nomebank = nomebank;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public String getNomebank() {
        return nomebank;
    }

    public void setNomebank(String nomebank) {
        this.nomebank = nomebank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBank != null ? idBank.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBank)) {
            return false;
        }
        TblBank other = (TblBank) object;
        if ((this.idBank == null && other.idBank != null) || (this.idBank != null && !this.idBank.equals(other.idBank))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblBank[ idBank=" + idBank + " ]";
    }
    
}
