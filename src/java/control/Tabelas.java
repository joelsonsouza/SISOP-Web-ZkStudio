/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.TblRootsJpaController;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.TblRoots;
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

/**
 *
 * @author joels
 */
public class Tabelas implements VM{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SisopWebPU");
    private TblRoots selected;
    
    private String pesqTipo="";
    private String pesqDesc="";
     private String pesqPer="";
    private List<TblRoots> listaTabelas;     
    @Wire
    private Window Pesq;

    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        listaTabelas = new TblRootsJpaController(emf).findTblRootsEntities();
        selected = new TblRoots();
        
        
    }    

    
    //@NotifyChange({"listaTabelas"})
    @Override
    @Command
    public void incluir(){ 
        if(selected.getIdTipo()==null){
            new TblRootsJpaController(emf).create(selected); //Função para gravar registro no banco
        }else{
            try {
                new TblRootsJpaController(emf).edit(selected);
            } catch (Exception ex) {
                Messagebox.show("Erro na Alteração do Registro\n"+ex);
                
            }
        }
            Messagebox.show("Registro Salvo");
            
            selected = null;
    }
    
    @Override
    @NotifyChange({"listaTabelas"})
    @Command
    public void pesquisa(){
       listaTabelas = new TblRootsJpaController(emf).find(pesqTipo, pesqDesc, pesqPer);
       
    }
    
    @Override
    @NotifyChange({"listaTabelas"})
    @Command
    public void pesquisar(){ //Função para carregamento de janela cadFrete
    Pesq.doModal();
    listaTabelas = new TblRootsJpaController(emf).findTblRootsEntities();
    }
    
    
    @NotifyChange({"selected"})
    @Command
    @Override
    public void delete() { //Função para carregamento de janela cadFrete
        if(selected !=null){     
            try {
                new TblRootsJpaController(emf).destroy(selected.getIdTipo());
            } catch (NonexistentEntityException | IllegalOrphanException ex) {
                Messagebox.show("Erro na Alteração do Registro\n"+ex);
            }
            Messagebox.show("Registro apagado!");
            limpar();
        }else{
        Messagebox.show("Selecione um registro");
        }  
    }
    @Override
    @NotifyChange({"selected"})
    @Command
    public void limpar(){
    selected.setTipo("");
    selected.setDescricaotipo("");
    selected.setPeriodo("");
    }
    
    
    
    
    //Gatters and Setters

    public TblRoots getSelected() {
        return selected;
    }

    public void setSelected(TblRoots selected) {
        this.selected = selected;
    }

    public String getPesqTipo() {
        return pesqTipo;
    }

    public void setPesqTipo(String pesqTipo) {
        this.pesqTipo = pesqTipo;
    }

    public String getPesqDesc() {
        return pesqDesc;
    }

    public void setPesqDesc(String pesqDesc) {
        this.pesqDesc = pesqDesc;
    }

    public List<TblRoots> getListaTabelas() {
        return listaTabelas;
    }

    public void setListaTabelas(List<TblRoots> listaTabelas) {
        this.listaTabelas = listaTabelas;
    }

    public String getPesqPer() {
        return pesqPer;
    }

    public void setPesqPer(String pesqPer) {
        this.pesqPer = pesqPer;
    }
   
    
    
}
