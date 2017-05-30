package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblPoupanca;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-03T01:47:08")
@StaticMetamodel(TblRoots.class)
public class TblRoots_ { 

    public static volatile SingularAttribute<TblRoots, String> tipo;
    public static volatile SingularAttribute<TblRoots, String> descricaotipo;
    public static volatile ListAttribute<TblRoots, TblPoupanca> tblPoupancaList;
    public static volatile SingularAttribute<TblRoots, String> periodo;
    public static volatile SingularAttribute<TblRoots, Integer> idTipo;

}