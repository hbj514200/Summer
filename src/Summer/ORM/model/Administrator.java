package Summer.ORM.model;

public class Administrator {
private int aid;
private String aname;
private String sex;
private String cardnumber;
private String pwd;
private String phone;
public static String AID = "aid";
public static String ANAME = "aname";
public static String SEX = "sex";
public static String CARDNUMBER = "cardnumber";
public static String PWD = "pwd";
public static String PHONE = "phone";

public int getAid()                               {             return aid;      }
public String getAname()                          {           return aname;      }
public String getSex()                            {             return sex;      }
public String getCardnumber()                     {      return cardnumber;      }
public String getPwd()                            {             return pwd;      }
public String getPhone()                          {           return phone;      }

public void setAid(int aid)                       {         this.aid = aid;      }
public void setAname(String aname)                {     this.aname = aname;      }
public void setSex(String sex)                    {         this.sex = sex;      }
public void setCardnumber(String cardnumber)      {this.cardnumber = cardnumber;      }
public void setPwd(String pwd)                    {         this.pwd = pwd;      }
public void setPhone(String phone)                {     this.phone = phone;      }

}