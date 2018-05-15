package Summer.ORM.model;

public class FillQ {
private int fid;
private String fproblem;
private String answer;
private int tiku;
public static String FID = "fid";
public static String FPROBLEM = "fproblem";
public static String ANSWER = "answer";
public static String TIKU = "tiku";

public int getFid()                               {             return fid;      }
public String getFproblem()                       {        return fproblem;      }
public String getAnswer()                         {          return answer;      }
public int getTiku()                              {            return tiku;      }

public void setFid(int fid)                       {         this.fid = fid;      }
public void setFproblem(String fproblem)          {this.fproblem = fproblem;      }
public void setAnswer(String answer)              {   this.answer = answer;      }
public void setTiku(int tiku)                     {       this.tiku = tiku;      }

}