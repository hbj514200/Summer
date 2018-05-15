package Summer.ORM.model;

public class Problem {
private int pid;
private String problem;
private String answer;
private String type;
private int tiku;
public static String PID = "pid";
public static String PROBLEM = "problem";
public static String ANSWER = "answer";
public static String TYPE = "type";
public static String TIKU = "tiku";

public int getPid()                               {             return pid;      }
public String getProblem()                        {         return problem;      }
public String getAnswer()                         {          return answer;      }
public String getType()                           {            return type;      }
public int getTiku()                              {            return tiku;      }

public void setPid(int pid)                       {         this.pid = pid;      }
public void setProblem(String problem)            { this.problem = problem;      }
public void setAnswer(String answer)              {   this.answer = answer;      }
public void setType(String type)                  {       this.type = type;      }
public void setTiku(int tiku)                     {       this.tiku = tiku;      }

}