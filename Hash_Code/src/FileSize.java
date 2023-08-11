import java.io.*;
import java.security.NoSuchAlgorithmException;

public class FileSize {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Sha_512 sha512 = new Sha_512();
        String filePath = sha512.ChooseFile();
    }
/*
    public static void kosul(String filePath, Sha_512 sha512) throws IOException {
        long sizeFile = new File(filePath).length();

        if (sizeFile < 1024) {
            filePath = sha512.ChooseFile();
            if (filePath != null) {
                kosul(filePath, sha512);
            }
        } else {
            bolmeIslemi(filePath, filePath, 1);
        }
    }

    private static void bolmeIslemi(String kaynakDosya, String hedefKlasor, int boyut) throws IOException {
        int tamponBoyutu = 1024;
        byte[] tampon = new byte[tamponBoyutu];

        try (InputStream girisAkisi = new FileInputStream(kaynakDosya)) {
            String dosyaAdi = new File(kaynakDosya).getName();
            int parcaNo = 1;

            while (true) {
                int okunanBayt = girisAkisi.read(tampon);
                if (okunanBayt == -1) {
                    break; // Dosyanın sonuna gelindi
                }

                String hedefDosyaYolu = hedefKlasor + dosyaAdi + "_part" + parcaNo + ".dat";
                try (OutputStream cikisAkisi = new FileOutputStream(hedefDosyaYolu)) {
                    cikisAkisi.write(tampon, 0, okunanBayt);
                }

                parcaNo++;
            }
        }


    }
    */
}
