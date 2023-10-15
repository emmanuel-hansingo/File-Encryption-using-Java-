
/**
 * Write a description of class EncryptionAssignment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.*;
import java.io.*;
import java.security.*;

public class FileEncryption {
    public static void main(String[] args) {
        // Initialize the timer
        Timer timer = new Timer();

        // Schedule the encryption task to run every 10 minutes
        timer.schedule(new EncryptionTask(), 0, 1 * 60 * 1000);

        
    }
    
    static class EncryptionTask extends TimerTask {
        @Override
        public void run() {
            try {
                // Your encryption logic here
                // Traverse the directory, encrypt files, and save them

                File directory = new File("/home/emmanuelhansingo/Desktop/encryption folder");
                File[] files = directory.listFiles();
                for (File file : files) {
                     System.out.println(file);
                }

                for (File file : files) {
                    if (file.isFile()) {
                        encryptAndSave(file);
                    }
                }
                } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
         private void encryptAndSave(File file) throws Exception {
            // Your encryption logic here
            // Generate a secret key, create a cipher, and encrypt the file
            // Save the encrypted file with a different name or extension
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // Key size
            SecretKey secretKey = keyGen.generateKey();
    
            // Create a cipher for encryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String inputFile = ""+file;
            String encryptedFile = file+".dat";
            File file1 = new File(inputFile);
        // Encrypt the file
        if (file1.exists() && !(getFileExtension(file1).equals("dat"))) {
        try (FileInputStream in = new FileInputStream(inputFile);
             FileOutputStream out = new FileOutputStream(encryptedFile))
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                out.write(encryptedBytes);
            }
            byte[] finalEncryptedBytes = cipher.doFinal();
            out.write(finalEncryptedBytes);
        }
        File plainfile = new File(inputFile);
        plainfile.delete();

            System.out.println("File encrypted successfully.");
        }
        
        
    
                
        }
        
        private static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }
    }
}
