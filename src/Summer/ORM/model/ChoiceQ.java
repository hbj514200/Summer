package Summer.ORM.model;

public class ChoiceQ {
private int cid;
private String cproblem;
private String a;
private String b;
private String c;
private String d;
private String answer;
private int tiku;
public static String CID = "cid";
public static String CPROBLEM = "cproblem";
public static String A = "a";
public static String B = "b";
public static String C = "c";
public static String D = "d";
public static String ANSWER = "answer";
public static String TIKU = "tiku";

public int getCid()                               {             return cid;      }
public String getCproblem()                       {        return cproblem;      }
public String getA()                              {               return a;      }
public String getB()                              {               return b;      }
public String getC()                              {               return c;      }
public String getD()                              {               return d;      }
public String getAnswer()                         {          return answer;      }
public int getTiku()                              {            return tiku;      }

public void setCid(int cid)                       {         this.cid = cid;      }
public void setCproblem(String cproblem)          {this.cproblem = cproblem;      }
public void setA(String a)                        {             this.a = a;      }
public void setB(String b)                        {             this.b = b;      }
public void setC(String c)                        {             this.c = c;      }
public void setD(String d)                        {             this.d = d;      }
public void setAnswer(String answer)              {   this.answer = answer;      }
public void setTiku(int tiku)                     {       this.tiku = tiku;      }

}