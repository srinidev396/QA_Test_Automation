package web.Utilities;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class encryptPass {
    /** Enter password and password is encrypted and
     * set encrypted password store into variables.properties file
     * @param args = Enter password during the runtime
     */
    public static void main(String[] args) {
        byte[] encodedString = Base64.getEncoder().encode(args[0].getBytes());
        System.out.println("Encrypted Password : " + new String(encodedString));

        try(FileInputStream fileInputStream = new FileInputStream("./variables.properties")) {
            Properties prop = new Properties();
            prop.load(fileInputStream);
            prop.setProperty(args[1], new String(encodedString));
            OutputStream output = new FileOutputStream("./variables.properties");
            prop.store(output, "Updated Data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
