package ua.vesa.osnova.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.vesa.osnova.exeption.PdfDownloadExeption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@Component
public class FileOIUtils {
    @Autowired
    private ServletContext context;
    private File file;

    private File getFile() {

        String urlFile = System.getProperty("user.home") + File.separator + "document";
        File file = new File(urlFile);
        if (!file.exists()) {
            file.mkdirs();
//            System.out.println("Файл создан");
        }else {
//            System.out.println("Есть такой файл");
        }

        return file;
//        return new File(context.getRealPath("/WEB-INF/documents/"));  //from linux
    }

    public byte[] getDataDoc(String title) {
        File file = new File(getFile().toString() + File.separator + title + ".pdf");
        byte[] fileByte = new byte[(int) file.length()];
        try (InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            fis.read(fileByte);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileByte;
    }

    public void saveDataDoc(String fileName, Part data) {
        file = new File(getFile().toString() + File.separator + fileName + ".pdf");

        try (InputStream inputStream = data.getInputStream()) {
            FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validatePdf(Part instruction) throws PdfDownloadExeption {
        if (!instruction.getContentType().equals("application/pdf")) {
            throw new PdfDownloadExeption("Only PDF file");
        }
    }

    public boolean deleteDataDoc(String fileName) {
        file = new File(getFile().toString() + File.separator + fileName + ".pdf");
        return file.delete();
    }

    public void streamReport(HttpServletResponse response, byte[] data, String name, boolean save) {
        response.setContentType("application/pdf; charset=UTF-8");
        if (save)
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
        try (OutputStream out = response.getOutputStream()) {
            response.setContentLength(data.length);
            out.write(data);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
