package Base;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.Color;
import java.io.IOException;

public class PDFReportGenerator {
	private String overallStatus = ""; // new variable

	public void setOverallStatus(String status) {
	    this.overallStatus = status;
	}
    private PDDocument document;
    private PDPageContentStream contentStream;

    public PDFReportGenerator() {
    	
        document = new PDDocument();
    }
    private float drawRow(String label, String value, float x, float y, float labelWidth, float valueWidth, float rowHeight) throws IOException {
        String[] lines = splitTextIntoLines(value, 70);
        float totalHeight = lines.length * rowHeight;

        // Draw outer box
        contentStream.addRect(x, y - totalHeight, labelWidth + valueWidth, totalHeight);
        contentStream.stroke();

        // Draw vertical divider
        contentStream.moveTo(x + labelWidth, y);
        contentStream.lineTo(x + labelWidth, y - totalHeight);
        contentStream.stroke();

        // Draw label
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x + 5, y - 15);
        contentStream.showText(label);
        contentStream.endText();

        // Draw value lines
        float z = y;
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        for (String line : lines) {
            z -= rowHeight;
            contentStream.beginText();
            contentStream.newLineAtOffset(x + labelWidth + 5, z + 10);
            contentStream.showText(line);
            contentStream.endText();
        }

        return y - totalHeight;
    }


    public void addStep(String objective, String expected, String actual, String status, String screenshotPath) throws IOException {
        // Close previous content stream if still open
        if (contentStream != null) {
            contentStream.close();
        }

        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);

        float x = 30;
        float y = page.getMediaBox().getHeight() - 50;
        float labelWidth = 100;
        float valueWidth = 400;
        float rowHeight = 25;

        y = drawRow("Objective:", objective, x, y, labelWidth, valueWidth, rowHeight);
        y = drawRow("Expected:", expected, x, y, labelWidth, valueWidth, rowHeight);
        y = drawRow("Actual:", actual, x, y, labelWidth, valueWidth, rowHeight);
        y = drawRow("Status:", status, x, y, labelWidth, valueWidth, rowHeight);

        if (screenshotPath != null) {
            PDImageXObject screenshot = PDImageXObject.createFromFile(screenshotPath, document);
            y -= 15;

            float imgX = 100;
            float imgY = y - 270;
            float imgWidth = 400;
            float imgHeight = 270;

            contentStream.drawImage(screenshot, imgX, imgY, imgWidth, imgHeight);

            contentStream.setStrokingColor(0, 0, 0);
            contentStream.addRect(30, imgY+2, 500, imgHeight+10);
            contentStream.stroke();

            y = imgY - 30;
        }

        contentStream.close();
        contentStream = null;
    }
    private PDPageContentStream cover;
    float cover1;
    public void addCoverPage(String testCaseName, String description) throws IOException {
        PDPage coverPage = new PDPage(PDRectangle.A4);
        document.addPage(coverPage);

        if (contentStream != null) {
            contentStream.close();
        }

        contentStream = new PDPageContentStream(document, coverPage);
        cover = contentStream;

        float pageWidth = coverPage.getMediaBox().getWidth();
        float y = coverPage.getMediaBox().getHeight() - 100;

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
        contentStream.newLineAtOffset((pageWidth - 300) / 2, y);
        contentStream.showText("Test Execution Report");
        contentStream.endText();

        y -= 50;
        y = drawRow("Objective:", description, 30, y, 120, 430, 25);
        y = drawRow("Tester:", "Aman Garg", 30, y, 120, 430, 25);
        y = drawRow("Employee ID:", "872907", 30, y, 120, 430, 25);
        y = drawRow("Operating System", "Windows 11", 30, y, 120, 430, 25);
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        y = drawRow("Timestamp", timestamp, 30, y, 120, 430, 25);
        
        // Draw empty "Status" row
        drawRow("Status", "", 30, y, 120, 430, 25);
        
        // Save coordinates to write later
        cover1 = y;

        contentStream.close();
    }
    public void addstatustocoverpage(String status) throws IOException {
        PDPage coverPage = document.getPage(0); // First page is the cover
        PDPageContentStream statusStream = new PDPageContentStream(document, coverPage, PDPageContentStream.AppendMode.APPEND, true);
        // Set color using java.awt.Color
        if ("Pass".equalsIgnoreCase(status)) {
            statusStream.setNonStrokingColor(Color.GREEN.darker());
        } else if ("Fail".equalsIgnoreCase(status)) {
            statusStream.setNonStrokingColor(Color.RED);
        } else {
            statusStream.setNonStrokingColor(Color.BLACK);
        }

        float x = 30 + 120 + 5;  // labelWidth + padding
        float y = cover1 - 15;   // center inside the "Status" row

        statusStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        statusStream.beginText();
        statusStream.newLineAtOffset(x, y);
        statusStream.showText(status);
        statusStream.endText();

        statusStream.close();
    }

    
    private String[] splitTextIntoLines(String text, int maxLineLength) {
        return text.split("(?<=\\G.{" + maxLineLength + "})");
    }

    public void save(String outputPath) throws IOException {
        if (contentStream != null) {
            contentStream.close(); // Ensure stream is closed before saving
        }
        document.save(outputPath);
        document.close();
    }
}
