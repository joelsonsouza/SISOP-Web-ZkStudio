package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-03T01:47:08")
@StaticMetamodel(TblContbank.class)
public class TblContbank_ { 

    public static volatile SingularAttribute<TblContbank, String> namecard;
    public static volatile SingularAttribute<TblContbank, String> cvv;
    public static volatile SingularAttribute<TblContbank, String> tipoconta;
    public static volatile SingularAttribute<TblContbank, String> tipocard;
    public static volatile SingularAttribute<TblContbank, String> nuncard;
    public static volatile SingularAttribute<TblContbank, Date> dvencimento;
    public static volatile SingularAttribute<TblContbank, Integer> idBank;
    public static volatile SingularAttribute<TblContbank, Integer> idContbank;
    public static volatile SingularAttribute<TblContbank, String> agencont;
    public static volatile SingularAttribute<TblContbank, Integer> idCorretista;

}