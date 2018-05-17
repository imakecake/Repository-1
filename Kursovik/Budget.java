public class Budget { 
    private String id; 
    private String Cipher; 
    private String Ball; 
    public Budget(){}
    public Budget (String id){this.id=id;}
    public Budget(String id, String Cipher, String Ball){
        this.id = id; this.Cipher = Cipher; this.Ball = Ball;} 
    public String getId() {return id;} 
    public String getCipher() {return Cipher;} 
    public String getBall() {return Ball;} 
    public void  setId(String id) {this.id=id;} 
    public void setCipher(String Cipher) {this.Cipher = Cipher;} 
    public void setBall(String Ball) {this.Ball = Ball;} 
}