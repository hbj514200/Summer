package Summer.ORM.model;

public class Student {
private int sid;
private String sname;
private String sex;
private String cardnumber;
private String pwd;
private String department;
private String phone;
public static String SID = "sid";
public static String SNAME = "sname";
public static String SEX = "sex";
public static String CARDNUMBER = "cardnumber";
public static String PWD = "pwd";
public static String DEPARTMENT = "department";
public static String PHONE = "phone";

public int getSid()                               {             return sid;      }
public String getSname()                          {           return sname;      }
public String getSex()                            {             return sex;      }
public String getCardnumber()                     {      return cardnumber;      }
public String getPwd()                            {             return pwd;      }
public String getDepartment()                     {      return department;      }
public String getPhone()                          {           return phone;      }

public void setSid(int sid)                       {         this.sid = sid;      }
public void setSname(String sname)                {     this.sname = sname;      }
public void setSex(String sex)                    {         this.sex = sex;      }
public void setCardnumber(String cardnumber)      {this.cardnumber = cardnumber;      }
public void setPwd(String pwd)                    {         this.pwd = pwd;      }
public void setDepartment(String department)      {this.department = department;      }
public void setPhone(String phone)                {     this.phone = phone;      }

}