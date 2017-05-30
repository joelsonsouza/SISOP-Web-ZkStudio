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
@Table(name = "tbl_corretista", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCorretista.findAll", query = "SELECT t FROM TblCorretista t")
    , @NamedQuery(name = "TblCorretista.findByIdCorretista", query = "SELECT t FROM TblCorretista t WHERE t.idCorretista = :idCorretista")
    , @NamedQuery(name = "TblCorretista.findByNomecorretista", query = "SELECT t FROM TblCorretista t WHERE t.nomecorretista = :nomecorretista")
    , @NamedQuery(name = "TblCorretista.findBySexo", query = "SELECT t FROM TblCorretista t WHERE t.sexo = :sexo")
    , @NamedQuery(name = "TblCorretista.findByDtnasc", query = "SELECT t FROM TblCorretista t WHERE t.dtnasc = :dtnasc")
    , @NamedQuery(name = "TblCorretista.findByEscolaridade", query = "SELECT t FROM TblCorretista t WHERE t.escolaridade = :escolaridade")
    , @NamedQuery(name = "TblCorretista.findByTrabalhando", query = "SELECT t FROM TblCorretista t WHERE t.trabalhando = :trabalhando")
    , @NamedQuery(name = "TblCorretista.findByTipovinculo", query = "SELECT t FROM TblCorretista t WHERE t.tipovinculo = :tipovinculo")
    , @NamedQuery(name = "TblCorretista.findByEmpresa", query = "SELECT t FROM TblCorretista t WHERE t.empresa = :empresa")
    , @NamedQuery(name = "TblCorretista.findByCargo", query = "SELECT t FROM TblCorretista t WHERE t.cargo = :cargo")
    , @NamedQuery(name = "TblCorretista.findByTelefone", query = "SELECT t FROM TblCorretista t WHERE t.telefone = :telefone")
    , @NamedQuery(name = "TblCorretista.findByCelular", query = "SELECT t FROM TblCorretista t WHERE t.celular = :celular")
    , @NamedQuery(name = "TblCorretista.findByStatus", query = "SELECT t FROM TblCorretista t WHERE t.status = :status")
    , @NamedQuery(name = "TblCorretista.findBySalario", query = "SELECT t FROM TblCorretista t WHERE t.salario = :salario")})
public class TblCorretista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_corretista", nullable = false)
    private Integer idCorretista;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nomecorretista;
    @Column(length = 15)
    private String sexo;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtnasc;
    @Column(length = 25)
    private String escolaridade;
    @Column(length = 15)
    private String trabalhando;
    @Column(length = 50)
    private String tipovinculo;
    @Column(length = 50)
    private String empresa;
    @Column(length = 50)
    private String cargo;
    @Column(length = 20)
    private String telefone;
    @Column(length = 20)
    private String celular;
    private Boolean status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double salario;

    public TblCorretista() {
    }

    public TblCorretista(Integer idCorretista) {
        this.idCorretista = idCorretista;
    }

    public TblCorretista(Integer idCorretista, String nomecorretista, Date dtnasc) {
        this.idCorretista = idCorretista;
        this.nomecorretista = nomecorretista;
        this.dtnasc = dtnasc;
    }

    public Integer getIdCorretista() {
        return idCorretista;
    }

    public void setIdCorretista(Integer idCorretista) {
        this.idCorretista = idCorretista;
    }

    public String getNomecorretista() {
        return nomecorretista;
    }

    public void setNomecorretista(String nomecorretista) {
        this.nomecorretista = nomecorretista;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(Date dtnasc) {
        this.dtnasc = dtnasc;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getTrabalhando() {
        return trabalhando;
    }

    public void setTrabalhando(String trabalhando) {
        this.trabalhando = trabalhando;
    }

    public String getTipovinculo() {
        return tipovinculo;
    }

    public void setTipovinculo(String tipovinculo) {
        this.tipovinculo = tipovinculo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorretista != null ? idCorretista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCorretista)) {
            return false;
        }
        TblCorretista other = (TblCorretista) object;
        if ((this.idCorretista == null && other.idCorretista != null) || (this.idCorretista != null && !this.idCorretista.equals(other.idCorretista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblCorretista[ idCorretista=" + idCorretista + " ]";
    }
    
}
