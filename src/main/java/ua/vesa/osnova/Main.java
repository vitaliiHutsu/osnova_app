package ua.vesa.osnova;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        String urlFile = System.getProperty("user.home") + File.separator + "document" + File.separator;

        File file = new File(urlFile);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("Файл создан");
        }else {
            System.out.println("Есть такой файл");
        }


        System.out.println(urlFile);
    }
}
