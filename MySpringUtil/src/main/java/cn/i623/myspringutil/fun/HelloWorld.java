package cn.i623.myspringutil.fun;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class HelloWorld {
    public static final String oldPath = "C:\\Users\\28442\\Desktop\\hy.pdf";
    public static final String newPath = "C:\\Users\\28442\\Desktop\\hy_Chane.pdf";

    /**
     * Generates a PDF file with the text 'Hello World'
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Hello World");

        // step 1: creation of a document-object
        Document document = new Document();


        try {
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));

/*            PdfReader oldReader = new PdfReader(oldPath);
            PdfDictionary dict = oldReader.getPageN(1);
            PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
            if (object instanceof PRStream) {
                PRStream stream = (PRStream) object;
                byte[] data = PdfReader.getStreamBytes(stream);
                String dd = new String(data);
                dd = dd.replace("0123456789", "0121212121212");
                dd = dd.replace("EEE:", "Our Ref:");
                dd = dd.replace("WR", "IT TEST");
                dd = dd.replace("2016", "2020");
                stream.setData(dd.getBytes());
            }
            PdfStamper stamper = new PdfStamper(oldReader, new FileOutputStream(newPath));
            PdfWriter writer = stamper.getWriter();*/

            // step 3: we open the document
            document.open();
            // step 4: we add a paragraph to the document
            document.add(new Paragraph("Hello World"));
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        // step 5: we close the document
        document.close();
    }
}