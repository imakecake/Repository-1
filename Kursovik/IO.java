import javax.swing.JFrame;
import java.awt.*; 
import javax.swing.*; 
import java.util.*; 
import java.awt.event.*; 
import java.io.*;
public class IO{
    public static LinkedHashSet<Exam> inp(String fileName) throws IOException{
        String t=new String(fileName);
        File c=new File(t);
            BufferedReader fr = new BufferedReader(new FileReader(c));
            String s=new String();
            int i=0;
            LinkedHashSet<Exam> lst=new LinkedHashSet<Exam>();
            while((s=fr.readLine())!=null){
               Exam tmp=new Exam();
               String[] tear = new String[3];
               tear=s.split(" ");
               
               tmp.setId(tear[0]);
               tmp.setCipher(tear[1]);
               tmp.setBall(tear[2]);
             
               i++;
               lst.add(tmp);
               s=new String();        
            }
        fr.close();
        return lst;
    }

    public static int saveas(String fway, ArrayList<Exam> lst, int mark) throws IOException{
        String t=new String(fway);
        File c=new File(t);
        if((!c.createNewFile())&&(mark==2)){return 2;}
        if((!c.createNewFile())&&(mark==1)){c.delete();c.createNewFile();}
        BufferedWriter bw = new BufferedWriter(new FileWriter(c));
        for(int p=0;p<lst.size();p++){
            Exam tmp=new Exam();
            tmp=lst.get(p);
            String s=new String();
            s=tmp.getId()+" "+tmp.getCipher()+" "+tmp.getBall();
            bw.write(s);
            bw.newLine();
        }
        bw.close();
        return 1;
    }
}