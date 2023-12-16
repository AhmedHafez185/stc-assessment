package com.stc.assessment.utils;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
public class FileUtils {
    public static String createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.createNewFile();
        return filePath;
    }
    public static String createDirectory(String dirPath) throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    public static String getResourcesPath() throws IOException {
        File file = ResourceUtils.getFile("resources");
        String resourcePath = file.getAbsolutePath();
        return resourcePath;
    }
    public static void createPdf(String filePath, String content) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.newLineAtOffset(20, 700); // Adjust the position as needed
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.showText(content);
                contentStream.endText();
            }

            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
