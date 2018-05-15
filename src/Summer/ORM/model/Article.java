package Summer.ORM.model;

public class Article {
private int id;
private String title;
private String date;
private String author;
private String content;
public static String ID = "id";
public static String TITLE = "title";
public static String DATE = "date";
public static String AUTHOR = "author";
public static String CONTENT = "content";

public int getId()                                {              return id;      }
public String getTitle()                          {           return title;      }
public String getDate()                           {            return date;      }
public String getAuthor()                         {          return author;      }
public String getContent()                        {         return content;      }

public void setId(int id)                         {           this.id = id;      }
public void setTitle(String title)                {     this.title = title;      }
public void setDate(String date)                  {       this.date = date;      }
public void setAuthor(String author)              {   this.author = author;      }
public void setContent(String content)            { this.content = content;      }

}