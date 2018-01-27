package ipfe;
import java.util.Arrays;
import java.util.regex.Pattern;

public class IPFE {

    public static void main(String[] args) {
        IPaddress test;
        try {
            test = new IPaddress("193.223.234.98");
        }
        catch(IpFormatError ex) {
            System.out.println(ex.getMessage());
            test = null;
        }
        if(test != null) {
            System.out.println(test.toString());
            System.out.println("Classe: " + test.getBelongClass());
            System.out.println("Indirizzo di rete: " + test.getNetworkAddress());
            System.out.println("Indirizzo di broadcast: " + test.getBroadcastAddress());
            System.out.println("SubnetMask: " + test.getSubnetMask());
            System.out.println(test.getIsPrivate() ? "Private" : "Public");
        }
    }
    
}
