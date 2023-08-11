import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Sha_256 {

    public static String checksum(byte[] data, MessageDigest md){
        md.update(data);
        byte[] digest = md.digest();

        StringBuilder result = new StringBuilder();
        for (byte b : digest) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String ChooseFile() {
        JFrame frame = new JFrame("Choose Your File");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String Files = selectedFile.getAbsolutePath();
            frame.dispose();
            return Files;

        } else {
            frame.dispose();
            //System.out.println("No file selected.");
            return null;
        }
    }

    public static List<String> DivideFile(String filePath, int chunkSize, MessageDigest md) throws IOException{
        List<String> hashes = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(filePath);

        byte[] chunk = new byte[chunkSize];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(chunk)) != -1) {
            byte[] chunkData = new byte[bytesRead];
            System.arraycopy(chunk, 0, chunkData, 0, bytesRead);
            String hashValue = checksum(chunkData, md);
            hashes.add(hashValue);
        }

        fileInputStream.close();
        return hashes;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        long start = System.currentTimeMillis();
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your file:");
        String filePath = ChooseFile();

        if (filePath != null) {
            List<String> hashes = DivideFile(filePath, 1024 * 1024, md);

            System.out.println("SHA-256 Hashes for 1MB Chunks:");
            for (int i = 0; i < hashes.size(); i++) {
                System.out.println("Chunk " + (i + 1) + ": " + hashes.get(i));
            }

            byte[] fileBytes = Files.readAllBytes(new File(filePath).toPath());
            //long deger = fileBytes.length;


            // FileSize.kosul(out, new Sha_512());
            String hexFile = checksum(fileBytes, md);
            //System.out.println("Selected File:" + hexFile);
            long end = System.currentTimeMillis();
            long elapsedTime = end - start; //kodun başlangıç ve biriş zamanları arasındaki süre hesaplanır
            System.out.println("Time: (ms) " + elapsedTime);
            System.out.println(fileBytes.length);


            long start2 = System.currentTimeMillis();
            String hex = checksum(Files.readAllBytes(new File ("C:\\Users\\ilayd\\Desktop\\örnek.zip").toPath()), md);
            System.out.println("ZIP dosyası:" + hex);

            long end2 = System.currentTimeMillis();
            long elapsedTime2 = end2 - start2; //kodun başlangıç ve biriş zamanları arasındaki süre hesaplanır
            System.out.println("Time: (ms) " + elapsedTime2);



        }


    }
}

