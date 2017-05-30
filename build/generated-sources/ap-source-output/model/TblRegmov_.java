package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblRoots;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-03T01:47:08")
@StaticMetamodel(TblRegmov.class)
public class TblRegmov_ { 

    public static volatile SingularAttribute<TblRegmov, String> tipo;
    public static volatile SingularAttribute<TblRegmov, Integer> ano;
    public static volatile SingularAttribute<TblRegmov, Double> valor;
    public static volatile SingularAttribute<TblRegmov, String> mes;
    public static volatile SingularAttribute<TblRegmov, TblRoots> idTipo;
    public static volatile SingularAttribute<TblRegmov, String> detalhamento;
    public static volatile SingularAttribute<TblRegmov, Integer> idRegmov;

}