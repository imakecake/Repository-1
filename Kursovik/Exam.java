public class Exam { 
    private String id; 
    private String Cipher; 
    private String Ball; 
   
    public Exam(){}
    public Exam (String id){this.id=id;}
    public Exam(String id, String Cipher, String Ball){
        this.id = id; this.Cipher = Cipher; this.Ball = Ball;} 
    public String getId() {return id;} 
    public String getCipher() {return Cipher;} 
    public String getBall() {return Ball;} 
    public void  setId(String id) {this.id=id;} 
    public void setCipher(String Cipher) {this.Cipher = Cipher;} 
    public void setBall(String Ball) {this.Ball = Ball;} 
    
    public boolean equals (Object ob){
      if (ob==this) return true;                             
      if (ob==null) return false; 
      if (getClass()!=ob.getClass())return false; 
      Exam exam=(Exam)ob; 
      return id == exam.id;              
    }
       
    public int compareTo(Exam exam){
      if (Integer.parseInt(id) < Integer.parseInt(exam.id)) return -1;
      else if  (Integer.parseInt(id) == Integer.parseInt(exam.id)) return 0;
      else return 1;
    }  
}