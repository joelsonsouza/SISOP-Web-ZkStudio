/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.TblPoupancaJpaController;
import dao.TblRootsJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.TblPoupanca;
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
public class RegMovCP implements VM{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SisopWebPU");
    private TblPoupanca selected;
    
    
    private String pesqMes="";
    private String pesqDet="";
    private String pesqTipo="";
    private List<TblPoupanca> listaTabelas;
    private List<TblRoots> listaTipo;    
    
    @Wire
    private Window Pesq;

    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        listaTabelas = new TblPoupancaJpaController(emf).findTblPoupancaEntities();
        listaTipo = new TblRootsJpaController(emf).findTblRootsEntities();
        selected = new TblPoupanca(); 
        selected.setPopano(2017);
        
    }    

    
    @NotifyChange({"listaTabelas"})
    @Command
    @Override
    public void incluir(){ 
        if(selected.getIdPoupanca()==null){
  
            new TblPoupancaJpaController(emf).create(selected);
            Messagebox.show("Registro Salvo");
        }else{
            try {
                new TblPoupancaJpaController(emf).edit(selected);
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
       listaTabelas = new TblPoupancaJpaController(emf).find(pesqMes,pesqTipo);  
       
    }
    
    
    @NotifyChange({"listaTabelas"})
    @Command
    @Override
    public void pesquisar(){ //Função para carregamento de janela cadFrete
    Pesq.doModal();
    listaTabelas = new TblPoupancaJpaController(emf).findTblPoupancaEntities();
    }
    
    
    @NotifyChange({"selected"})
    @Command
    @Override
    public void delete(){ //Função para carregamento de janela cadFrete
        if(selected !=null){     
            try {
                new TblPoupancaJpaController(emf).destroy(selected.getIdPoupanca());
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
    selected.setPopano(2017);
    selected.setPopmes("");
    selected.setPopidTipo(null);//verificar
    selected.setPoptipo("");
    selected.setPopdetalhamento("");
    selected.setPopvalor(0.00);
    selected.setPopdatareg(null);
    }

    
    //Gatters and Setters

    public TblPoupanca getSelected() {
        return selected;
    }

    public void setSelected(TblPoupanca selected) {
        this.selected = selected;
    }

    public String getPesqMes() {
        return pesqMes;
    }

    public void setPesqMes(String pesqDesc) {
        this.pesqMes = pesqDesc;
    }

    public List<TblPoupanca> getListaTabelas() {
        return listaTabelas;
    }

    public void setListaTabelas(List<TblPoupanca> listaTabelas) {
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

    public String getPesqDet() {
        return pesqDet;
    }

    public void setPesqDet(String pesqDet) {
        this.pesqDet = pesqDet;
    }

    public String getPesqTipo() {
        return pesqTipo;
    }

    public void setPesqTipo(String pesqTipo) {
        this.pesqTipo = pesqTipo;
    }
    
    
    
  
}
