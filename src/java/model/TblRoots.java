/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joels
 */
@Entity
@Table(name = "tbl_roots", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRoots.findAll", query = "SELECT t FROM TblRoots t")
    , @NamedQuery(name = "TblRoots.findByIdTipo", query = "SELECT t FROM TblRoots t WHERE t.idTipo = :idTipo")
    , @NamedQuery(name = "TblRoots.findByTipo", query = "SELECT t FROM TblRoots t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TblRoots.findByDescricaotipo", query = "SELECT t FROM TblRoots t WHERE t.descricaotipo = :descricaotipo")
    , @NamedQuery(name = "TblRoots.findByPeriodo", query = "SELECT t FROM TblRoots t WHERE t.periodo = :periodo")})
public class TblRoots implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String tipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String descricaotipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String periodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "popidTipo")
    private List<TblPoupanca> tblPoupancaList;

    public TblRoots() {
    }

    public TblRoots(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TblRoots(Integer idTipo, String tipo, String descricaotipo, String periodo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
        this.descricaotipo = descricaotipo;
        this.periodo = periodo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricaotipo() {
        return descricaotipo;
    }

    public void setDescricaotipo(String descricaotipo) {
        this.descricaotipo = descricaotipo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @XmlTransient
    public List<TblPoupanca> getTblPoupancaList() {
        return tblPoupancaList;
    }

    public void setTblPoupancaList(List<TblPoupanca> tblPoupancaList) {
        this.tblPoupancaList = tblPoupancaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRoots)) {
            return false;
        }
        TblRoots other = (TblRoots) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblRoots[ idTipo=" + idTipo + " ]";
    }
    
}
