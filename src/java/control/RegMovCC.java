/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.TblRegmovJpaController;
import dao.TblRootsJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.TblRegmov;
import model.TblRoots;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.MessageboxDlg;

/**
 *
 * @author joels
 */
public class RegMovCC implements VM{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SisopWebPU");
    private TblRegmov selected;
    
    
    private String pesqMes="";
    private String pesqDet="";
    private String pesqTip="";
    private List<TblRegmov> listaTabelas;
    private List<TblRoots> listaTipo;    
    private TblRoots pesqTipo;
    @Wire
    private Window Pesq;

    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        listaTabelas = new TblRegmovJpaController(emf).findTblRegmovEntities();
        listaTipo = new TblRootsJpaController(emf).findTblRootsEntities();
        selected = new TblRegmov();
        pesqTipo = new TblRoots();
        selected.setAno(2017);
        
    }    

    
    //@NotifyChange({"listaTabelas"})
    @Command
    public void incluir(){ 
        if(selected.getIdRegmov()==null){
  
            new TblRegmovJpaController(emf).create(selected);
            Messagebox.show("Registro Salvo");
        }else{
            try {
                new TblRegmovJpaController(emf).edit(selected);
                Messagebox.show("Registro alterado");
            } catch (Exception ex) {
                Messagebox.show("Erro ao Salvar o registro\n"+ex);
                
            }
        } 
        selected = null;
    }
    
    
    @NotifyChange({"listaTabelas"})
    @Command
    @Override
    public void pesquisa(){
       listaTabelas = new TblRegmovJpaController(emf).find(pesqMes, pesqTip , pesqDet); 
 
    }
    
    
    @NotifyChange({"listaTabelas"})
    @Command
    @Override
    public void pesquisar(){ //Função para carregamento de janela cadFrete
    Pesq.doModal();
    listaTabelas = new TblRegmovJpaController(emf).findTblRegmovEntities();
    }
    
    
    @NotifyChange({"selected"})
    @Command
    @Override
    public void delete(){ //Função para carregamento de janela cadFrete
        if(selected !=null){     
            try {
                new TblRegmovJpaController(emf).destroy(selected.getIdRegmov());
            } catch (NonexistentEntityException ex) {
                Messagebox.show("Erro na Alteração do Registro\n"+ex);
            }
            Messagebox.show("Registro apagado!");
            limpar();
        }else{
        Messagebox.show("Selecione um registro");
        }  
    }
    @NotifyChange({"selected"})
    @Command
    @Override
    public void limpar(){
    selected.setAno(2017);
    selected.setMes("");
    selected.setIdTipo(null);//verificar
    selected.setTipo("");
    selected.setDetalhamento("");
    selected.setValor(null);
    }

    
    //Gatters and Setters

    public TblRegmov getSelected() {
        return selected;
    }

    public void setSelected(TblRegmov selected) {
        this.selected = selected;
    }

    public String getPesqMes() {
        return pesqMes;
    }

    public void setPesqMes(String pesqDesc) {
        this.pesqMes = pesqDesc;
    }

    public List<TblRegmov> getListaTabelas() {
        return listaTabelas;
    }

    public void setListaTabelas(List<TblRegmov> listaTabelas) {
        this.listaTabelas = listaTabelas;
    }

    public Window getPesq() {
        return Pesq;
    }

    public void setPesq(Window Pesq) {
        this.Pesq = Pesq;
    }

    public List<TblRoots> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<TblRoots> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public TblRoots getPesqTipo() {
        return pesqTipo;
    }

    public void setPesqTipo(TblRoots pesqTipo) {
        this.pesqTipo = pesqTipo;
    }

    public String getPesqDet() {
        return pesqDet;
    }

    public void setPesqDet(String pesqDet) {
        this.pesqDet = pesqDet;
    }

    public String getPesqTip() {
        return pesqTip;
    }

    public void setPesqTip(String pesqTip) {
        this.pesqTip = pesqTip;
    }


  
}
