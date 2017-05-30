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
@Table(name = "tbl_usuarios", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUsuarios.findAll", query = "SELECT t FROM TblUsuarios t")
    , @NamedQuery(name = "TblUsuarios.findByIdUsuario", query = "SELECT t FROM TblUsuarios t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TblUsuarios.findByUsuario", query = "SELECT t FROM TblUsuarios t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TblUsuarios.findBySenha", query = "SELECT t FROM TblUsuarios t WHERE t.senha = :senha")
    , @NamedQuery(name = "TblUsuarios.findByNome", query = "SELECT t FROM TblUsuarios t WHERE t.nome = :nome")
    , @NamedQuery(name = "TblUsuarios.findByPerfil", query = "SELECT t FROM TblUsuarios t WHERE t.perfil = :perfil")
    , @NamedQuery(name = "TblUsuarios.findByCelular", query = "SELECT t FROM TblUsuarios t WHERE t.celular = :celular")
    , @NamedQuery(name = "TblUsuarios.findByEmail", query = "SELECT t FROM TblUsuarios t WHERE t.email = :email")
    , @NamedQuery(name = "TblUsuarios.findByAtivo", query = "SELECT t FROM TblUsuarios t WHERE t.ativo = :ativo")})
public class TblUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String usuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String senha;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String perfil;
    @Column(length = 15)
    private String celular;
    @Column(length = 30)
    private String email;
    private Boolean ativo;

    public TblUsuarios() {
    }

    public TblUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TblUsuarios(Integer idUsuario, String usuario, String senha, String nome, String perfil) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.perfil = perfil;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsuarios)) {
            return false;
        }
        TblUsuarios other = (TblUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
