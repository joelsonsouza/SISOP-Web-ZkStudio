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
@Table(name = "tbl_regmov", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRegmov.findAll", query = "SELECT t FROM TblRegmov t")
    , @NamedQuery(name = "TblRegmov.findByIdRegmov", query = "SELECT t FROM TblRegmov t WHERE t.idRegmov = :idRegmov")
    , @NamedQuery(name = "TblRegmov.findByAno", query = "SELECT t FROM TblRegmov t WHERE t.ano = :ano")
    , @NamedQuery(name = "TblRegmov.findByMes", query = "SELECT t FROM TblRegmov t WHERE t.mes = :mes")
    , @NamedQuery(name = "TblRegmov.findByTipo", query = "SELECT t FROM TblRegmov t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TblRegmov.findByDetalhamento", query = "SELECT t FROM TblRegmov t WHERE t.detalhamento = :detalhamento")
    , @NamedQuery(name = "TblRegmov.findByDatareg", query = "SELECT t FROM TblRegmov t WHERE t.datareg = :datareg")
//    , @NamedQuery(name = "TblRegmov.findBySaldo", query = "SELECT t FROM TblRegmov t WHERE t.saldo = :saldo")
    , @NamedQuery(name = "TblRegmov.findByValor", query = "SELECT t FROM TblRegmov t WHERE t.valor = :valor")})
public class TblRegmov implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_regmov", nullable = false)
    private Integer idRegmov;
    @Basic(optional = false)
    @Column(nullable = false)
    private int ano;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String mes;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String tipo;
    @Column(length = 50)
    private String detalhamento;
    @Temporal(TemporalType.DATE)
    private Date datareg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    @Column(precision = 17, scale = 17)
    private Double valor;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne
    private TblRoots idTipo;

    public TblRegmov() {
    }

    public TblRegmov(Integer idRegmov) {
        this.idRegmov = idRegmov;
    }

    public TblRegmov(Integer idRegmov, int ano, String mes, String tipo) {
        this.idRegmov = idRegmov;
        this.ano = ano;
        this.mes = mes;
        this.tipo = tipo;
    }

    public Integer getIdRegmov() {
        return idRegmov;
    }

    public void setIdRegmov(Integer idRegmov) {
        this.idRegmov = idRegmov;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Date getDatareg() {
        return datareg;
    }

    public void setDatareg(Date datareg) {
        this.datareg = datareg;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TblRoots getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TblRoots idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegmov != null ? idRegmov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRegmov)) {
            return false;
        }
        TblRegmov other = (TblRegmov) object;
        if ((this.idRegmov == null && other.idRegmov != null) || (this.idRegmov != null && !this.idRegmov.equals(other.idRegmov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblRegmov[ idRegmov=" + idRegmov + " ]";
    }
    
}
