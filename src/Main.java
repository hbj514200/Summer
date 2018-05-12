import Summer.Ioc.ScanUtil;
import Summer.ORM.DBScanUtil;

import Summer.ORM.model.Record;
import Summer.ORM.model.Student;
import Summer.PipeLine.PipeCenter;
import xiaoxiang.MyBean;
import Summer.ORM.*;
import xiaoxiang.Pipe1;
import xiaoxiang.two.oneBean;
import java.lang.reflect.Method;
import java.net.URL;
import xiaoxiang.*;


public class Main {
    private MyBean bean;

    public static void main(String[] args) throws Exception {
        PipeCenter center = new PipeCenter();
        center.addPipe(Pipe1.class, new Pipe1());
        center.addPipe(Pipe2.class, new Pipe2());
        center.addPipe(Pipe3.class, new Pipe3());
        center.addPipe(Pipe4.class, new Pipe4());
        center.addPipe(Pipe5.class, new Pipe5());
        center.work();
    }

}