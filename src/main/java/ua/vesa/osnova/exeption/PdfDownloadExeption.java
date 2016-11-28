package ua.vesa.osnova.exeption;

public class PdfDownloadExeption extends RuntimeException {
    public PdfDownloadExeption(String message) {
        super(message);
    }
}
