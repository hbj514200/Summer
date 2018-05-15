package Summer.ORM.model;

public class Record {
private int sid;
private double fen;
private String date;
public static String SID = "sid";
public static String FEN = "fen";
public static String DATE = "date";

public int getSid()                               {             return sid;      }
public double getFen()                            {             return fen;      }
public String getDate()                           {            return date;      }

public void setSid(int sid)                       {         this.sid = sid;      }
public void setFen(double fen)                    {         this.fen = fen;      }
public void setDate(String date)                  {       this.date = date;      }

}