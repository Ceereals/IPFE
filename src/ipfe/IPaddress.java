package ipfe;
import java.util.regex.Pattern;

public class IPaddress {

    private int b1; //First byte
    private int b2; //Second byte
    private int b3; //Third byte
    private int b4; //Fourth byte
    private char belongClass;
    private boolean isPrivate;
    private String particular;
    
    
    public IPaddress(String indirizzo) throws IpFormatError{
        String[] a = indirizzo.split(Pattern.quote(".")); //We use pattern.quote perchè "." means "any character" in regex
        try {
            this.b1 = Integer.parseInt(a[0]);
            this.b2 = Integer.parseInt(a[1]);
            this.b3 = Integer.parseInt(a[2]);
            this.b4 = Integer.parseInt(a[3]);
        }
        catch(NumberFormatException ex) {
            throw new IpFormatError("Byte Format Error: "+ ex.getMessage());
        }
        //Check if the insert bytes are valid, in case they aren't throws an exception
        if(!isValid()) throw new IpFormatError("The bytes are out of the regular limits");
        //If it's valid, set it's class
        this.findClass();
        //Set the value of isPrivate
        this.isPrivate();
        
    }
    /**
     * This function check if the ip adrees is valid, in particulare chack if every byte is included between 0 & 255
     * @return true or false if the address is valid or not
     */
    private boolean isValid(){
        return this.b1 >= 0 && this.b1<=255 && this.b2 >= 0 && this.b2<=255 && this.b3 >= 0 && this.b3<=255 && this.b4 >= 0 && this.b4<=255;
    }
    
    /**
     * Check if an ip is provate and set the attribute isPrivate on true or false depending of the result of the method
     */
    private void isPrivate() {
        switch(this.belongClass) {
            case 'A':
                this.isPrivate = this.b1 == 10;
                break;
            case 'B':
                this.isPrivate = this.b1 == 172 && this.b2 >= 16 && this.b2 <= 31;
                break;
            case 'C': 
                this.isPrivate = this.b1 == 192 && this.b2 == 168;
                break;
            default:
                this.isPrivate = false;
                break;
        }
    }
    /**
     * this method as the function of detrem the class of an ip address
     */
    private void findClass() {
        if(this.b1 < 128)
            this.belongClass = 'A';
        else if(this.b1 < 192)
            this.belongClass = 'B';
        else if(this.b1 < 224)
            this.belongClass = 'C';
        else if(this.b1 < 240)
            this.belongClass = 'D';
        else this.belongClass = 'E';
    }
    
    @Override
    public String toString() {
        return this.b1 + "." + this.b2 + "." + this.b3 + "." + this.b4; 
    }
    /**
     * 
     * @return the subnet mask of the current ip
     */
    public String getSubnetMask() {
        switch(this.belongClass) {
            case 'A':
                return "255.0.0.0";
            case 'B':
                return "255.255.0.0";
            case 'C': 
                return "255.255.255.0";
            default:
                return ""; //Da finire
        }
    }
    
    /**
     * @return the class of the address
     */
    public char getBelongClass() {
        return this.belongClass;
    }
    /**
     * 
     * @return the broadcast address of the current ip
     */
    public String getNetworkAddress() {
        String str = "";
        switch(this.belongClass) {
            case 'A':
                str += this.b1 + ".0.0.0";
                break;
            case 'B':
                str += this.b1 + "." + this.b2 + "." + "0.0";
                break;
            case 'C': 
                str += this.b1 + "." + this.b2 + "." + this.b3 + "." + "0";
            default:
                break; //Da finire
        }
        return str;
    }
    /**
     * 
     * @return the broadcast address of the current ip
     */
    public String getBroadcastAddress() {
        String str = "";
        switch(this.belongClass) {
            case 'A':
                str += this.b1 + ".255.255.255";
                break;
            case 'B':
                str += this.b1 + "." + this.b2 + ".255.255";
                break;
            case 'C': 
                str += this.b1 + "." + this.b2 + "." + this.b3 + ".255";
                break;
            default:
                break; //Da finire
        }
        return str;
    }
    
    /**
     * @return true if the address is private
     */
    public boolean getIsPrivate() {
        return this.isPrivate;
    }
}
   
