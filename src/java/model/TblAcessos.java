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
@Table(name = "tbl_acessos", catalog = "sinf", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcessos.findAll", query = "SELECT t FROM TblAcessos t")
    , @NamedQuery(name = "TblAcessos.findByIdAcesso", query = "SELECT t FROM TblAcessos t WHERE t.idAcesso = :idAcesso")
    , @NamedQuery(name = "TblAcessos.findByIdUsuario", query = "SELECT t FROM TblAcessos t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TblAcessos.findByDtaacesso", query = "SELECT t FROM TblAcessos t WHERE t.dtaacesso = :dtaacesso")
    , @NamedQuery(name = "TblAcessos.findByHracesso", query = "SELECT t FROM TblAcessos t WHERE t.hracesso = :hracesso")})
public class TblAcessos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso", nullable = false)
    private Integer idAcesso;
    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String dtaacesso;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String hracesso;

    public TblAcessos() {
    }

    public TblAcessos(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public TblAcessos(Integer idAcesso, int idUsuario, String dtaacesso, String hracesso) {
        this.idAcesso = idAcesso;
        this.idUsuario = idUsuario;
        this.dtaacesso = dtaacesso;
        this.hracesso = hracesso;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDtaacesso() {
        return dtaacesso;
    }

    public void setDtaacesso(String dtaacesso) {
        this.dtaacesso = dtaacesso;
    }

    public String getHracesso() {
        return hracesso;
    }

    public void setHracesso(String hracesso) {
        this.hracesso = hracesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcesso != null ? idAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcessos)) {
            return false;
        }
        TblAcessos other = (TblAcessos) object;
        if ((this.idAcesso == null && other.idAcesso != null) || (this.idAcesso != null && !this.idAcesso.equals(other.idAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblAcessos[ idAcesso=" + idAcesso + " ]";
    }
    
}
