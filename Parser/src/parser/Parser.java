package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static parser.Parser.states.Done;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
  import java.util.Iterator;
import java.util.Random;
import org.graphstream.stream.ProxyPipe;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.view.Viewer;



class node {
 ArrayList<node> al=new ArrayList<node>();
 ArrayList<String> a2=new ArrayList<String>();
 /*node(ArrayList<node> a1,ArrayList<String>a2)
 {
     this.al=a1;
     this.a2=a2;
 }*/
 node(){
 }
public ArrayList<String> getName() {
        return a2;
    }

}

public class Parser extends JFrame {
    static    int x=0;

  static   int counter=-1;
   static  int counter1=-1;
  static   int counter2=-1;
 static    String token []=new String[1000];
   static  String Type []=new String[1000];
   static  PrintWriter writer;
   static  node stmt=new node();
  static   boolean ifs=false;
    static  boolean repeat=false;
    static  int temp=0;
   static  String parsetree[] =new String [100];
   static  int index=0;
 static    boolean stop=true;
   static  boolean top=false;
 static    boolean elsepart=false;
  static   boolean end=false;
    static  String [] tree= new String[1000];
   static   int ind=0;
   static int en=0;
       static int u=0;
static boolean  xx=false;
  static boolean  yy=false;  
  static int sized=0;
  static  String [] chif= new String[1000];
 static int chf=0;
  static  String [] chrep= new String[1000];
 static int chere=0;
 static int id=0;
    enum states{start,Done, Num,ID,Comment,Assig;};
   
public static void Scan(String st) throws FileNotFoundException, UnsupportedEncodingException
{
   writer =new  PrintWriter("file.txt", "UTF-8");
    String Done []=new String [400];
    String DoneType[]=new String[400];
    String Symbol []=new String [] {"+","-","=","*","/","<",";","(",")",":="};
    String words []=new String [] {"then","if","end","repeat","until","read","write","else"};
    int length =st.length();
 
    String letter="";
    String Comment="";
    String digit="";
    states stat=states.start;
    int count=0;
    while(stat==states.start&& count<length)
    {
        while(stat!=states.Done&&isLetter(st.charAt(count)))
        {
        
        letter=letter+st.charAt(count);
        count++;
        if(count==length)
            break;
        stat=states.ID;
        }
       if(stat==states.ID)
       {
        for(int i=0;i<words.length;i++)
        {
            if(letter.equals(words[i]))
            {
            Done[sized]=letter;
            DoneType[sized]=words[i];
            sized++;
            stat=states.Done;
            letter="";
            break;
            }
            else if(i==words.length-1)
            {
            Done[sized]=letter;
            DoneType[sized]="ID";
            sized++;
            stat=states.Done;
            letter="";
            }
        }
        
       }
       if(count==length)
            break;
        while(stat!=states.Done && isDigit(st.charAt(count)))
        {
        digit=digit+st.charAt(count);
        count++;
        stat=states.Num;
        }
        if(stat!=states.Done&&stat==states.Num)
        {
        Done[sized]=digit;
            DoneType[sized]="Digit";
            sized++;
            stat=states.Done;
            digit="";
        }
        if(count==length)
            break;
        if (stat!=states.Done && st.charAt(count)==' ')
        {
        stat=states.Done;
        count ++;
        
        }
        if(count==length)
            break;
        if(stat!=states.Done && st.charAt(count)=='{')
        {   
           
            count++;
            
            while(st.charAt(count)!='}')
            {
               Comment=Comment+st.charAt(count);
               count++;
               stat=states.Comment;   
            }
           
            Comment="";
            stat=states.Done;
            count++;
        }
        if(stat!=states.Done)
        {
        String sym="";
        sym=Character.toString(st.charAt(count));
        if(sym.equals(":"))
        {
        if(st.charAt(count+1)=='=')
        {
        Done[sized]=":=";
        DoneType[sized]="Symbol";
        stat=states.Assig;
        sized++;
        count++;
        count++;
        }
        }
        for(int j=0;j<Symbol.length;j++)
        {
        if(sym.equals(Symbol[j]))
        {
            stat=states.Assig;
         Done[sized]=sym;
            DoneType[sized]=Symbol[j];
            sized++; 
            count++;
        }
        }
        
        }
        stat=states.start;
            
    }
   /* int m=0;
    System.out.println("Tokens: ");
    while(Done[m]!=null)
    {
    System.out.println("("+Done[m]+")"+ " --> " + DoneType[m]);
    m++;
    }*/
for(int may=0;may<sized;may++){
    token[may]=Done[may];
}
for(int may=0;may<sized;may++){
    Type[may]=DoneType[may];
}
  
        program();
System.out.println(sized);


    
}


 
public static String fn() throws FileNotFoundException, IOException
{
File file = new File("F:\\f.txt"); 
  
  BufferedReader br = new BufferedReader(new FileReader(file)); 
  String st; 
  String s= "";
  while ((st = br.readLine()) != null) 
  {
  s=s+st;
  }
  System.out.println("The File: ");
  System.out.println(s);
  System.out.println();
  return s;

}


    
  
    public static void drawtree(String s [] ,int i)
    {
        
  
 String Symbol []=new String [] {"+","-","=","*","/","<"};
 int mayar=0;

 while(ind<i)
 {
    
  
       if(ind<i&&s[ind].substring(0,1).equals("w") ) 
       {
       tree[ind]=s[ind];
       ind++;
       tree[ind]=s[ind];
       ind++;
       
       }
       else if(ind<i &&s[ind].substring(0,1).equals("a")) 
       { 
           tree[ind]=s[ind];
       ind++;
           while(ind<i&&!s[ind].substring(0, 1).equals("u")&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
           {
                            tree[ind]=s[ind];
                             ind++;
                           while(ind<i&&!s[ind].substring(0, 1).equals("u")&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
                         {  
                            tree[ind]=s[ind+1];
                            ind++;
                            while(ind<i&&!s[ind].substring(0, 1).equals("u")&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
                                   {  
                                        tree[ind]=s[ind-1];
                                        ind++;
                                   }
                            }
                            
       
                  }
            while(ind<i&&s[ind].substring(0, 1).equals("u")&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
           {
                            tree[ind]=s[ind];
                             ind++;
                           while(ind<i&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
                         {  
                            tree[ind]=s[ind+1];
                            ind++;
                            while(ind<i&&!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
                                   {  
                                        tree[ind]=s[ind-1];
                                        ind++;
                                   }
                            }
                            
       
                  }
         
       }
       else if(ind<i&&s[ind].equals("repeat_stmt"))
       {
            tree[ind]=s[ind];
            ind++;
       
       }
       else if(ind<i&&s[ind].substring(0,1).equals("r"))
       {
            tree[ind]=s[ind];
            ind++;
       }
         else if(ind<i&&s[ind].equals("end"))
       {
            tree[ind]=s[ind];
            ind++;
       }
       else if(ind<i&&s[ind].substring(0,1).equals("i"))
       {
           tree[ind]=s[ind];
       ind++;
          
             while(!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
           {
       tree[ind]=s[ind];
       ind++;
       while(!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
           {
       tree[ind]=s[ind+1];
       ind++;
       while(!s[ind].substring(0, 1).equals("a")&&!s[ind].substring(0, 1).equals("w")&&!s[ind].substring(0, 1).equals("r")&&!s[ind].substring(0, 1).equals("i"))
           {
       tree[ind]=s[ind-1];
       ind++;
           }
           }
       }
       }
         
     }     
 
 
         
 for(int j=0;j<ind;j++)
 {
 System.out.println(tree[j]);
 
 }
 
 
    
    
    
  
    }
    public static void file (String s,int x) throws FileNotFoundException, UnsupportedEncodingException
{
writer.println(s);
if(x==(sized-1))
{
        writer.flush();
    if(stop){

   for(int i = 0; i <stmt.a2.size(); i++) {
       
          //  System.out.println(stmt.a2.get(i));
            parsetree[index]=stmt.a2.get(i);
            index++;
          
             for(int j= 0; j <stmt.al.get(i).a2.size(); j++) {
           parsetree[index]=stmt.al.get(i).a2.get(j);
            index++;
               //System.out.println(stmt.al.get(i).a2.get(j));
               
             }
        }
   drawtree(parsetree,index);


}
stop=false;


}
}
    public static boolean match(String tok,String m)
    {
       
        if(tok.equals(m))  
        {
            if(x<sized-1)
            {     
             x++;
            
            }
           
       return true;
        }
        return false;
    }
    public static void program() throws FileNotFoundException, UnsupportedEncodingException{
       file("Program Found",x);
    stmt_sequence();
    
    }
    public static void stmt_sequence() throws FileNotFoundException, UnsupportedEncodingException{
        file("stmt_sequence Found",x);
        statement();
    
        for(int i=x; i<sized;i++)
        {
            
            if(match(token[x],";"))
            {
                
                file("Semi Colon Found",x);
            }  
       statement();
            
        }
      
    }
    public static  void statement() throws FileNotFoundException, UnsupportedEncodingException{
    
    switch(Type[x]) { 
    case "if":
        if(match(token[x],";"))
            {
                
                file("Semi Colon Found",x);
            } 
      //  else{
          //file("Semi Colon Not Found",x);   
       // }
        file("statement Found",x);
        if_stmt();
        break; 
    case "repeat": 
        file("statement Found",x);
        reapet_stmt();
        break; 
    case "read": 
        file("statement Found",x);
            read_stmt();
        break;
    case "write":
        file("statement Found",x);
        write_stmt();
            
       break;
    case "ID":
        file("statement Found",x);
    assign_stmt();
        break;
}
    }
 public static void if_stmt() throws FileNotFoundException, UnsupportedEncodingException 
 {
     file("If-statement Found",x);
     ifs=true;
        match(token[x],"if");
        file("If",x);
        if(!repeat)
 {
  node if_stmt=new node();
        counter++;
           stmt.al.add(if_stmt);
           stmt.a2.add("if_stmt");
 }
 else
 {
      node if_stmt=new node();
        counter1++;
           stmt.al.get(counter).al.add(if_stmt);
           stmt.al.get(counter).a2.add("if_stmt");
 }
        temp=counter1;
        counter1=-1;
        exp();
        
counter1=temp;
        if(!match(token[x],"then"))
        {
        file("Expected Then",x);
        }
        else 
        {
        file("Then",x);
        }
       temp=counter1;
        counter1=-1;
        stmt_sequence();
        counter1=temp;
        if(token[x].equals("else"))
        {
            file("Else-part Found",x);
             temp=counter1;
        counter1=-1;
        elsepart=true;
        stmt_sequence();
        elsepart=false;
        counter1=temp;
        }
       if(match(token[x],"end"))
       {
           file("Successfull End if",x);
              if(!repeat)
 {
  node end=new node();
        counter++;
           stmt.al.add(end);
           stmt.a2.add("end");
        
 }
 else
 {
      node end=new node();
        counter1++;
           stmt.al.get(counter).al.add(end);
           stmt.al.get(counter).a2.add("end");
 }
       }
       else {
           file("Expected End if",x);
       }
ifs=false;
 }
 public static void reapet_stmt() throws FileNotFoundException, UnsupportedEncodingException
 {
     repeat=true;
     file("Repeat_statement Found",x);
 match(token[x],"repeat");
 if(!ifs)
 {
  node repeat=new node();
        counter++;
           stmt.al.add(repeat);
           stmt.a2.add("repeat"+token[x-1]);
 }
 else
 {
      node repeat=new node();
        counter1++;
           stmt.al.get(counter).al.add(repeat);
           stmt.al.get(counter).a2.add("repeat_stmt");
 }
 temp=counter1;
 counter1=-1;
 stmt_sequence();
 counter1=temp;
 match(token[x],"until");
 file("Until Found",x);
  temp=counter1;
 counter1=-1;
 top=true;
    
 exp();
 top=false;
 counter1=temp;
// file("Repeat Stmt",x);
 repeat=false;
 }
 public static void assign_stmt() throws FileNotFoundException, UnsupportedEncodingException
 {
     file("Assignment_stmt",x);
   match(Type[x],"ID");
   if(!ifs && !repeat)
 {
  node assgin_stmt=new node();
        counter++;
           stmt.al.add(assgin_stmt);
           stmt.a2.add("assgin_stmt"+ token[x-1]);
 }
 else
 {
      node assign_stmt=new node();
        counter1++;
           stmt.al.get(counter).al.add(assign_stmt);
           stmt.al.get(counter).a2.add("assign_stmt"+token[x-1]);
 }
   match(token[x],":=");
   temp=counter1;
   counter1=-1;
   exp();
   counter1=temp;

   //file("Assignment stmt",x);
 }
public static void read_stmt() throws FileNotFoundException, UnsupportedEncodingException
{
if(match(token[x],"read"))
{
   if(match(Type[x],"ID"))
   {
      file("read stmt",x);
      
   if(!ifs && !repeat)
 {
  node read_stmt=new node();
        counter++;
           stmt.al.add(read_stmt);
           stmt.a2.add("read_stmt"+ token[x-1]);
 }
 else
 {
      node read_stmt=new node();
        counter1++;
           stmt.al.get(counter).al.add(read_stmt);
           stmt.al.get(counter).a2.add("read_stmt"+token[x-1]);

 }

   }
}
}

public static void write_stmt() throws FileNotFoundException, UnsupportedEncodingException
{
    file("Write stmt",x);
match(token[x],"write");
  if(!ifs && !repeat)
 {
  node write_stmt=new node();
        counter++;
           stmt.al.add(write_stmt);
           stmt.a2.add("write_stmt");
 }
 else
 {
      node write_stmt=new node();
        counter1++;
           stmt.al.get(counter).al.add(write_stmt);
           stmt.al.get(counter).a2.add("write_stmt");
 }
  temp=counter1;
  counter1=-1;
exp();
counter1=temp;

//file("Write stmt",x);
}
public static void  exp() throws FileNotFoundException, UnsupportedEncodingException
{
     file("Exp Found",x);
    simple_exp();
     if(token[x].equalsIgnoreCase("=")|| token[x].equalsIgnoreCase("<"))  
     {
         comparison();
          stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1-1));
             stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1-1));
         simple_exp();   
         stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1));
         stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1));
     }
}  
     public static  void comparison() throws FileNotFoundException, UnsupportedEncodingException
     {
          file("Comparison stmt",x);
         node comp=new node();
          counter1++;
          stmt.al.get(counter).al.add(comp);
          switch(token[x]) { 
    case "=":
        match(token[x],"=");
        file("=",x);
  if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "=");   
   }
  else if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"=");   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add("=");
   }
        break; 
    case "<": 
       match(token[x],"<");
       file("<",x);
    if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "<");   
   }
   if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"<");   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add("<");
   }
        break; 
     }
     }
     public static void simple_exp() throws FileNotFoundException, UnsupportedEncodingException{
         file("Simple_exp Found",x);
         term();
         while(token[x].equals("+")||token[x].equals("-"))
         {
             addop();
             
             stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1-1));
             stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1-1));
             term();
              stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1));
              stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1));
         
         }
         
}
     public static void addop() throws FileNotFoundException, UnsupportedEncodingException
     {
         file("Addop Found",x);
         node addop=new node();
          counter1++;
          stmt.al.get(counter).al.add(addop);
       switch(token[x]) { 
    case "+":
        match(token[x],"+");
        file("+",x);
       
 if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "+");   
   }
 else  if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"+");   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add("+");
   }
        break; 
    case "-": 
       match(token[x],"-");
       file("-",x);
 if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "-");   
   }
   if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"-");   
   } 
   else
   {
   stmt.al.get(counter).a2.add("-");
   }
        break; 
     }
     
     }
     public static void term() throws FileNotFoundException, UnsupportedEncodingException{
         file("Term Found",x);
     factor();
      while(token[x].equals("*")||token[x].equals("/"))
         {
             mulop();
       
            stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1-1));
             stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1-1));
         factor();
         
         stmt.al.get(counter).al.get(counter1).al.add(stmt.al.get(counter).al.get(counter1));
         stmt.al.get(counter).al.get(counter1).a2.add(stmt.al.get(counter).a2.get(counter1));
         
         }
     
     }
     
     public static void mulop() throws FileNotFoundException, UnsupportedEncodingException
     {
         file("Mulop Found",x);
          node mulop=new node();
          counter1++;
          stmt.al.get(counter).al.add(mulop);
         
       switch(token[x]) { 
    case "*":
        match(token[x],"*");
        file("*",x);
          if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "*");   
   }
  else if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"*");   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add("*");
   }
         
         
              break; 
    case "/": 
       match(token[x],"/");
       file("/",x);
               if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+ "/");   
   }
   if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+"/");   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add("/");
   }
        break; 
     }
     
     }
     public static void factor() throws FileNotFoundException, UnsupportedEncodingException{
          file("Factor Found ",x);
         switch (Type[x]){
   case "Digit":
       file("Digit",x);
     x++;
     node digit =new node();
     counter1++;
   stmt.al.get(counter).al.add(digit);
   if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+token[x-1]);   
   }
   else if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+token[x-1]);   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add(token[x-1]);
   }
     break;
     case"ID":
         file("ID",x);
         x++;
          node ID=new node();
            counter1++;
   stmt.al.get(counter).al.add(ID);
if(top)
   {
    stmt.al.get(counter).a2.add("until-part"+token[x-1]);   
   }
    else if (elsepart)
   {
   stmt.al.get(counter).a2.add("else-part"+token[x-1]);   
   } 
   else
   {
   
   stmt.al.get(counter).a2.add(token[x-1]);
   }
         break;
    case"(":
        match(token[x],"(");
        file("(",x);
     exp();
     match(token[x],")");
     file(")",x);
     break;
         }
     
     }

   Parser(){
       setSize(1800, 1000);
        setTitle("Parse Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    static int xaxis=0;
  static int yaxis=0;
  static int tempxaxis=0;
   static int tempyaxis=0;
  public static void DrawApi(Graph g)
   {
       String []stmtt=new String[100];
       int loop=0;
        String []temp= new String [100];
        int si=0;
        int eng=0;
   while(en<index)
   {
         if(en<index&&tree[en].substring(0, 3).equals("unt"))
    {
        break;
    }
         
         if(en<index&&tree[en].substring(0, 3).equals("end"))
    {
        break;
    }
       if(en<index&&tree[en].substring(0, 3).equals("rea"))
       { 
            if(xx&&yy)
           {
               if(chf<chere&&chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                     chf++;
               }
               else if(chere<chf&&chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                     chere++;
               }
               else if(chf<chere&&chf>=1)
               {
                  Node dd= g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
        dd.addAttribute("xy", xaxis,yaxis);
        xaxis+=2;
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++; 
               }
               else if(chere<chf&&chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                     dd.addAttribute("layout.frozen");
        dd.addAttribute("xy", xaxis,yaxis);
        xaxis+=2;
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
           }
            else if(xx)
            {
                if(chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                      chf++;
               }
                 else if(chf>=1)
               {
                  Node dd= g.addNode(tree[en]+id);
                   dd.addAttribute("layout.frozen");
        dd.addAttribute("xy", xaxis,yaxis);
        xaxis+=2;
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++;  }
            }
            else if(yy)
            {
               if(chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    
                    chere++;
               }
               else if(chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    xaxis+=2;
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
            }
          else {
             if(loop>0){
           Node dd=g.addNode(tree[en]+id);
            dd.addAttribute("layout.frozen");
        dd.addAttribute("xy", xaxis,yaxis);
        xaxis+=2;
           stmtt[loop]=tree[en]+id;
             
            g.addEdge(stmtt[loop-1]+stmtt[loop],stmtt[loop-1],stmtt[loop]);
            loop++;
                    en++; 
                    id++;
             }
                   
          else{
              Node dd=g.addNode(tree[en]+id);
               dd.addAttribute("layout.frozen");
                dd.addAttribute("xy", xaxis,yaxis);
                xaxis+=2;
           stmtt[loop]=tree[en]+id;
           loop++;
           id++;
           en++;
          }
       }
     }
        if(en<index&&tree[en].substring(0, 1).equals("w"))    
       {
            if(xx&&yy)
           {
               if(chf<chere&&chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                    chf++;
                    
               }
               else if(chere<chf&&chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chf<chere&&chf>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                  
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++; 
               }
               else if(chere<chf&&chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                   dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    
                   stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
           }
            else if(xx)
            {
                if(chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                    chf++;
               }
                 else if(chf>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++; 
               }
            }
            else if(yy)
            {
               if(chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chere>=1)
               {
                  Node dd= g.addNode(tree[en]+id);
                  dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
            }
          else {
             if(loop>0){
           Node dd=g.addNode(tree[en]+id);
           dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
           stmtt[loop]=tree[en]+id;
            g.addEdge(stmtt[loop-1]+stmtt[loop],stmtt[loop-1],stmtt[loop]);
             loop++;
                    en++; 
                    id++;
                     
             }
          else{
              Node dd=g.addNode(tree[en]+id);
              dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                    loop++;
                    id++;
                    en++;
          }

         }     
            tempxaxis=xaxis;
            tempyaxis=yaxis;
           while(en<index&&!tree[en].substring(0, 1).equals("e")&&!tree[en].substring(0, 1).equals("a")&&!tree[en].substring(0, 1).equals("w")&&!tree[en].substring(0, 1).equals("r")&&!tree[en].substring(0, 1).equals("i"))
           {
                 temp[si]=tree[en]+id;
                    si++;
                    en++;
                    id++;
           }      
        eng=si-1;
        String tempp="";
      
         while(eng>=0)
            {
                 yaxis-=1;
                Node dd=g.addNode(temp[eng]);
               
                String op= temp[eng];
                if(eng==(si-1))
                {
                     dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                      xaxis+=2;
                       yaxis-=1;
                   g.addEdge(stmtt[loop-1]+temp[eng],stmtt[loop-1],temp[eng]); 
                }
                else
                {
                    dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                      xaxis+=2;
                       yaxis-=1;
                     g.addEdge(tempp+op,tempp,op); 
                }
                 eng--;
                 if(eng>0)
                 {
                              
                     dd=g.addNode(temp[eng]);
                     dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                 }
                 xaxis-=2; 
                 if(eng==0)
                 {
                  
                         dd=g.addNode(temp[eng]);
                          dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                    
                 }   
                tempp=op;
            }
             si=0;
             eng=0;
            
             yaxis=tempyaxis;
             xaxis+=2;
       }
       
       if(en<index&&tree[en].substring(0, 2).equals("if"))    
       {
           int tempifx=xaxis;
           int tempify=yaxis;
           chf=0;
               Node dd=g.addNode(tree[en]+id);   
              dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
               stmtt[loop]=tree[en]+id;
           if(loop>0)
           {
            g.addEdge(stmtt[loop-1]+stmtt[loop],stmtt[loop-1],stmtt[loop]);
             }
           loop++;
             id++;
           en++;
           while(en<index&&!tree[en].substring(0, 1).equals("e")&&!tree[en].substring(0, 1).equals("a")&&!tree[en].substring(0, 1).equals("w")&&!tree[en].substring(0, 1).equals("r")&&!tree[en].substring(0, 1).equals("i"))
           {
                 temp[si]=tree[en]+id;
                    si++;
                    en++;
                    id++;
           } 
         tempxaxis=xaxis;
         tempyaxis=yaxis;
        eng=si-1;
        String tempp="";
         yaxis-=2;
         while(eng>=0)
            {
                
                 dd=g.addNode(temp[eng]);  
                String op= temp[eng];
                if(eng==(si-1))
                {
                    dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                     xaxis+=2;
                     yaxis-=2;
                   g.addEdge(stmtt[loop-1]+temp[eng],stmtt[loop-1],temp[eng]); 
                }
                else
                {
                      dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                     xaxis+=2;
                      yaxis-=2;
                     g.addEdge(tempp+op,tempp,op); 
                }
                 eng--;
                 if(eng>0)
                 {
                    dd=g.addNode(temp[eng]);
                      dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                   
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                 }
                    xaxis-=2;
                 if(eng==0)
                 {
                  
                        dd= g.addNode(temp[eng]);
                         dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                    
                 }   
                tempp=op;
            }
             si=0;
        
             yaxis=tempify; 
             yaxis-=1;
             xaxis+=2;
             eng=0;
              while(en<index&&!tree[en].substring(0, 1).equals("e"))
              {
                  xx=true;
          
                    if(chf==0)
                    {
                        String s =tree[en]+id;
                         dd= g.addNode(s);
                      dd.addAttribute("layout.frozen");
                     dd.addAttribute("xy", xaxis,yaxis);
                     xaxis+=2;
                          g.addEdge(stmtt[loop-1]+s,stmtt[loop-1],s);
                          chif[chf]=s;
                            
                    }
               DrawApi(g); 
               xx=false;
              }
             
              if(en<index&&tree[en].substring(0, 3).equals("els"))
              { 
                  
                    yaxis=tempify; 
                    yaxis-=1;
                    xaxis+=2;
                  en++; 
                    while(en<index&&!tree[en].substring(0, 3).equals("end"))
                    {
                     xx=true;
                        if(chf==0)
                        {
                            String s =tree[en]+id;
                              dd=g.addNode(s);
                              dd.addAttribute("layout.frozen");
                              dd.addAttribute("xy", xaxis,yaxis);
                              g.addEdge(stmtt[loop-1]+s,stmtt[loop-1],s);
                              chif[chf]=s;
                              id++;
                              chf++;
                        }
                   DrawApi(g); 
                   xx=false;   
                    }
                    
              }
                  en++;
                    yaxis=tempify; 
                    xaxis+=2; 
           }
       if(en<index&&tree[en].substring(0, 3).equals("ass"))
       {
           
                 if(xx&&yy)
            {
               if(chf<chere&&chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                    chf++;
               }
               else if(chere<chf&&chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chf<chere&&chf>=1)
               {
                  Node dd= g.addNode(tree[en]+id);
                   dd.addAttribute("layout.frozen");
                   dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++; 
               }
               else if(chere<chf&&chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
           }
                  else if(xx)
            {
                if(chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                    chf++;
               }
                 else if(chf>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                   dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++;  
                    
               }
            
            }
                  else if(yy)
            {
               if(chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
            }
          else {
             if(loop>0){
          Node dd= g.addNode(tree[en]+id);
           dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
           stmtt[loop]=tree[en]+id;
         
            g.addEdge(stmtt[loop-1]+stmtt[loop],stmtt[loop-1],stmtt[loop]);
               loop++;
                    en++; 
                    id++;
                     
             }
          else{
              Node dd=g.addNode(tree[en]+id);
               dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
           stmtt[loop]=tree[en]+id;
           loop++;
           id++;
           en++;
          }   
                  }  
                 
           while(en<index&&!tree[en].substring(0, 1).equals("e")&&!tree[en].substring(0, 1).equals("u")&&!tree[en].substring(0, 1).equals("a")&&!tree[en].substring(0, 1).equals("w")&&!tree[en].substring(0, 1).equals("r")&&!tree[en].substring(0, 1).equals("i"))
           {
                 temp[si]=tree[en]+id;
                    si++;
                    id++;
                    en++;
           }      
        eng=si-1;
        String tempp="";
        tempyaxis=yaxis;
         while(eng>=0)
            {
                yaxis-=1;
                Node dd=g.addNode(temp[eng]);
                String op= temp[eng];
                if(eng==(si-1))
                {
                     dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
               xaxis+=1;
               yaxis-=1;
                   g.addEdge(stmtt[loop-1]+temp[eng],stmtt[loop-1],temp[eng]); 
                }
                else
                {
                     dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
                xaxis+=1;
                yaxis-=1;
                     g.addEdge(tempp+op,tempp,op); 
                }
                 eng--;
                 if(eng>0)
                 {
                   dd= g.addNode(temp[eng]);
                    dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                 }
                 xaxis-=1;
                 if(eng==0)
                 {
                  
                        dd= g.addNode(temp[eng]);
                         dd.addAttribute("layout.frozen");
               dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                    
                 }   
                tempp=op;
            }
             si=0;
             eng=0;
            xaxis+=2;
            yaxis=tempyaxis;
       }
       if(en<index&&tree[en].substring(0, 3).equals("rep"))
       {
            int temprx=xaxis;
           int tempry=yaxis;
              if(xx&&yy)
            {
               if(chf<chere&&chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++;
                    chf++;
               }
               else if(chere<chf&&chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chf<chere&&chf>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++; 
               }
               else if(chere<chf&&chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
           }
                  else if(xx)
            {
                if(chf==0)
               {
                   stmtt[loop]=tree[en]+id;
                   loop++;
                    en++; 
                    id++; 
                    chf++;
               }
                 else if(chf>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chif[chf]=tree[en]+id;  
                g.addEdge(chif[chf-1]+chif[chf],chif[chf-1],chif[chf]);
                 chf++;
                    loop++;
                    id++;
                    en++;  
               }
            
            }
                  else if(yy)
            {
               if(chere==0){          
                 stmtt[loop]=tree[en]+id;
                    loop++;
                    en++; 
                    id++;
                    chere++;
               }
               else if(chere>=1)
               {
                   Node dd=g.addNode(tree[en]+id);
                    dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    stmtt[loop]=tree[en]+id;
                chrep[chere]=tree[en]+id;  
                g.addEdge(chrep[chere-1]+chrep[chere],chrep[chere-1],chrep[chere]);
                 chere++;
                    loop++;
                    id++;
                    en++; 
               }
            }
          else {
             if(loop>0){
          Node dd= g.addNode(tree[en]+id);
           dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
           stmtt[loop]=tree[en]+id;
            loop++;
            g.addEdge(stmtt[loop-1]+stmtt[loop],stmtt[loop-1],stmtt[loop]);
                    en++; 
                    id++;
                     
             }
          else{
             Node dd= g.addNode(tree[en]+id);
              dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
           stmtt[loop]=tree[en]+id;
           loop++;
           id++;
           en++;
          } 
                  }
              yaxis-=1;
           while(en<index&&!tree[en].substring(0, 1).equals("u"))
           {
                    yy=true;
                     chere=0;
                  
                         String s=tree[en]+id;
                     Node dd=g.addNode(s);
                   dd.addAttribute("layout.frozen");
                    dd.addAttribute("xy", xaxis,yaxis);
                    xaxis+=2;
                    stmtt[loop]=s;
                    if(chere==0)
                    {
                        chrep[chere]=s;
                     g.addEdge(stmtt[loop-1]+s,stmtt[loop-1],s);
                    }
                loop++;
                
                      DrawApi(g);     
                      yy=false;
                 
            }
         
           yaxis=tempry;
           while(en<index&&tree[en].substring(0, 1).equals("u"))
           {
               
                while(en<index&&!tree[en].substring(0, 1).equals("e")&&!tree[en].substring(0, 1).equals("a")&&!tree[en].substring(0, 1).equals("w")&&!tree[en].substring(0, 1).equals("r")&&!tree[en].substring(0, 1).equals("i"))
                {
                 temp[si]=tree[en]+id;
                 id++;
                    si++;
                    en++;
                }     
                 eng=si-1;
                 String tempp="";
            xaxis+=2;   
            while(eng>=0)
            {
              yaxis-=1;
              
                Node dd=g.addNode(temp[eng]);
                String op= temp[eng];
               if(eng==(si-1))
                {
                    for(int i=0;i<loop;i++)
                    {
                        if(stmtt[i].substring(0,3).equals("rep"))
                        {
                             dd.addAttribute("layout.frozen");
                            dd.addAttribute("xy", xaxis,yaxis);
                            xaxis+=2;
                            yaxis-=1;
                           g.addEdge(stmtt[i]+temp[eng],stmtt[i],temp[eng]);
                        }
                    }
                  
                }
                else
                {
                     g.addEdge(tempp+op,tempp,op); 
                      dd.addAttribute("layout.frozen");
                            dd.addAttribute("xy", xaxis,yaxis);
                            xaxis+=2;
                            yaxis-=1;
                }
                 eng--;
                 if(eng>0)
                 {
                    dd=g.addNode(temp[eng]);
                     dd.addAttribute("layout.frozen");
                            dd.addAttribute("xy", xaxis,yaxis);
                       
                          
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                 }
                      xaxis-=2;
                 if(eng==0)
                 {
                  
                        dd= g.addNode(temp[eng]);
                         dd.addAttribute("layout.frozen");
                            dd.addAttribute("xy", xaxis,yaxis);
                      g.addEdge(op+temp[eng],op,temp[eng]);
                       eng--;
                    
                    
                 }   
                tempp=op;   
            }
             si=0;
             eng=0;
       
            yaxis=tempry;
            xaxis+=3;
           }
       
       }
   }        
   }
   

 public static void main(String[] args) throws IOException{
       
     
       
       String text="";
       text=fn();
       Scan(text); 
       Graph g = new SingleGraph("Parser");  
       String [] ss1= new String[100];
       int x1=0;
           DrawApi(g);
           int x=0;
   for (Node n : g) {
    String ss =n.getId();
       int s=ss.length();
       
       if(x<=9)
       {
      if(n.getId().substring(0,1).equals("u"))
       {
            n.addAttribute("ui.label",ss.substring(10, s-1) );
       }
         else
           {
              
               
           n.addAttribute("ui.label",ss.substring(0, s-1) ); 
               
           }
       }
      else {
           if(n.getId().substring(0,1).equals("u"))
       {
            n.addAttribute("ui.label",ss.substring(10, s-2) );
       }
           else
           {
               if(n.getId().length()==2)
               {
                  n.addAttribute("ui.label",ss.substring(0, s-1) );   
               }
               else
               {
           n.addAttribute("ui.label",ss.substring(0, s-2) ); 
               }
           }
       }
       
       x++;

    }
 
for (Node n : g) {
      Node enf = g.getNode(n.getId());
           if(n.getId().substring(0,1).equals("r")||n.getId().substring(0,1).equals("a")||n.getId().substring(0,1).equals("w")||n.getId().substring(0,1).equals("i"))
           {enf.addAttribute("ui.style","shape:box;fill-color: white;stroke-mode: plain;stroke-color: black;size:70px,30px;");}
           else
           { enf.addAttribute("ui.style","fill-color: white;stroke-mode: plain;stroke-color: black;size:50px,40px;");}
    }
  System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");  
 g.addAttribute("ui.quality");
 g.addAttribute("ui.antialias");
g.display();

       
    }

}