package board;

import dependency.IBoardSerialize;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class BoardPDFSerializer implements IBoardSerialize {
    private static final Log LOGGER = LogFactory.getLog(BoardPDFSerializer.class);

    @Override
    public boolean saveMindMap(Board board, String filepath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(board.toString());
            contentStream.endText();
            document.save(filepath);
            return true;
        } catch (IOException e) {
            LOGGER.error("Error saving mind map to PDF: ", e);
            return false;
        }

    }
}
