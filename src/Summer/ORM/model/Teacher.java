package Summer.ORM.model;

public class Teacher {
private int tid;
private String tname;
private String sex;
private String cardnumber;
private String pwd;
private String title;
private String phone;
public static String TID = "tid";
public static String TNAME = "tname";
public static String SEX = "sex";
public static String CARDNUMBER = "cardnumber";
public static String PWD = "pwd";
public static String TITLE = "title";
public static String PHONE = "phone";

public int getTid()                               {             return tid;      }
public String getTname()                          {           return tname;      }
public String getSex()                            {             return sex;      }
public String getCardnumber()                     {      return cardnumber;      }
public String getPwd()                            {             return pwd;      }
public String getTitle()                          {           return title;      }
public String getPhone()                          {           return phone;      }

public void setTid(int tid)                       {         this.tid = tid;      }
public void setTname(String tname)                {     this.tname = tname;      }
public void setSex(String sex)                    {         this.sex = sex;      }
public void setCardnumber(String cardnumber)      {this.cardnumber = cardnumber;      }
public void setPwd(String pwd)                    {         this.pwd = pwd;      }
public void setTitle(String title)                {     this.title = title;      }
public void setPhone(String phone)                {     this.phone = phone;      }

}