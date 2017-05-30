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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_poupanca", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPoupanca.findAll", query = "SELECT t FROM TblPoupanca t")
    , @NamedQuery(name = "TblPoupanca.findByIdPoupanca", query = "SELECT t FROM TblPoupanca t WHERE t.idPoupanca = :idPoupanca")
    , @NamedQuery(name = "TblPoupanca.findByPopano", query = "SELECT t FROM TblPoupanca t WHERE t.popano = :popano")
    , @NamedQuery(name = "TblPoupanca.findByPopmes", query = "SELECT t FROM TblPoupanca t WHERE t.popmes = :popmes")
    , @NamedQuery(name = "TblPoupanca.findByPoptipo", query = "SELECT t FROM TblPoupanca t WHERE t.poptipo = :poptipo")
    , @NamedQuery(name = "TblPoupanca.findByPopdetalhamento", query = "SELECT t FROM TblPoupanca t WHERE t.popdetalhamento = :popdetalhamento")
    , @NamedQuery(name = "TblPoupanca.findByPopvalor", query = "SELECT t FROM TblPoupanca t WHERE t.popvalor = :popvalor")
    , @NamedQuery(name = "TblPoupanca.findByPopdatareg", query = "SELECT t FROM TblPoupanca t WHERE t.popdatareg = :popdatareg")})
public class TblPoupanca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_poupanca", nullable = false)
    private Integer idPoupanca;
    @Basic(optional = false)
    @Column(nullable = false)
    private int popano;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String popmes;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String poptipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String popdetalhamento;
    @Basic(optional = false)
    @Column(nullable = false)
    private double popvalor;
    @Temporal(TemporalType.DATE)
    private Date popdatareg;
    @JoinColumn(name = "popid_tipo", referencedColumnName = "id_tipo", nullable = false)
    @ManyToOne(optional = false)
    private TblRoots popidTipo;

    public TblPoupanca() {
    }

    public TblPoupanca(Integer idPoupanca) {
        this.idPoupanca = idPoupanca;
    }

    public TblPoupanca(Integer idPoupanca, int popano, String popmes, String poptipo, String popdetalhamento, double popvalor) {
        this.idPoupanca = idPoupanca;
        this.popano = popano;
        this.popmes = popmes;
        this.poptipo = poptipo;
        this.popdetalhamento = popdetalhamento;
        this.popvalor = popvalor;
    }

    public Integer getIdPoupanca() {
        return idPoupanca;
    }

    public void setIdPoupanca(Integer idPoupanca) {
        this.idPoupanca = idPoupanca;
    }

    public int getPopano() {
        return popano;
    }

    public void setPopano(int popano) {
        this.popano = popano;
    }

    public String getPopmes() {
        return popmes;
    }

    public void setPopmes(String popmes) {
        this.popmes = popmes;
    }

    public String getPoptipo() {
        return poptipo;
    }

    public void setPoptipo(String poptipo) {
        this.poptipo = poptipo;
    }

    public String getPopdetalhamento() {
        return popdetalhamento;
    }

    public void setPopdetalhamento(String popdetalhamento) {
        this.popdetalhamento = popdetalhamento;
    }

    public double getPopvalor() {
        return popvalor;
    }

    public void setPopvalor(double popvalor) {
        this.popvalor = popvalor;
    }

    public Date getPopdatareg() {
        return popdatareg;
    }

    public void setPopdatareg(Date popdatareg) {
        this.popdatareg = popdatareg;
    }

    public TblRoots getPopidTipo() {
        return popidTipo;
    }

    public void setPopidTipo(TblRoots popidTipo) {
        this.popidTipo = popidTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoupanca != null ? idPoupanca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPoupanca)) {
            return false;
        }
        TblPoupanca other = (TblPoupanca) object;
        if ((this.idPoupanca == null && other.idPoupanca != null) || (this.idPoupanca != null && !this.idPoupanca.equals(other.idPoupanca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblPoupanca[ idPoupanca=" + idPoupanca + " ]";
    }

}
