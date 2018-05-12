package Summer.AppContext;

import Summer.AOP.AOPCenter;
import Summer.Ioc.IocCenter;

public class AppContext {

    public static void init(){
        AOPCenter.scanTargetBean();
        IocCenter.scanTargetBean();
    }

}
