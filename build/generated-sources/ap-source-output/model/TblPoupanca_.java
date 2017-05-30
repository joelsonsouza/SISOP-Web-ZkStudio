package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblRoots;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-03T01:47:08")
@StaticMetamodel(TblPoupanca.class)
public class TblPoupanca_ { 

    public static volatile SingularAttribute<TblPoupanca, String> poptipo;
    public static volatile SingularAttribute<TblPoupanca, Double> popvalor;
    public static volatile SingularAttribute<TblPoupanca, TblRoots> popidTipo;
    public static volatile SingularAttribute<TblPoupanca, String> popmes;
    public static volatile SingularAttribute<TblPoupanca, String> popdetalhamento;
    public static volatile SingularAttribute<TblPoupanca, Integer> idPoupanca;
    public static volatile SingularAttribute<TblPoupanca, Integer> popano;
    public static volatile SingularAttribute<TblPoupanca, Date> popdatareg;

}