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
@Table(name = "tbl_detdesc", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDetdesc.findAll", query = "SELECT t FROM TblDetdesc t")
    , @NamedQuery(name = "TblDetdesc.findByIdDet", query = "SELECT t FROM TblDetdesc t WHERE t.idDet = :idDet")
    , @NamedQuery(name = "TblDetdesc.findByDetalhamento", query = "SELECT t FROM TblDetdesc t WHERE t.detalhamento = :detalhamento")})
public class TblDetdesc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_det", nullable = false)
    private Integer idDet;
    @Column(length = 30)
    private String detalhamento;

    public TblDetdesc() {
    }

    public TblDetdesc(Integer idDet) {
        this.idDet = idDet;
    }

    public Integer getIdDet() {
        return idDet;
    }

    public void setIdDet(Integer idDet) {
        this.idDet = idDet;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDet != null ? idDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDetdesc)) {
            return false;
        }
        TblDetdesc other = (TblDetdesc) object;
        if ((this.idDet == null && other.idDet != null) || (this.idDet != null && !this.idDet.equals(other.idDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblDetdesc[ idDet=" + idDet + " ]";
    }
    
}
