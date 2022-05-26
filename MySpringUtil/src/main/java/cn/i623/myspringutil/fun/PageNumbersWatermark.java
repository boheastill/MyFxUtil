/*
 * $Id: PageNumbersWatermark.java 3838 2009-04-07 18:34:15Z mstorer $
 *
 * This code is part of the 'OpenPDF Tutorial'.
 * You can find the complete tutorial at the following address:
 * https://github.com/LibrePDF/OpenPDF/wiki/Tutorial
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 *
 */
package cn.i623.myspringutil.fun;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Demonstrates the use of templates to add Watermarks and Pagenumbers.
 */
public class PageNumbersWatermark extends PdfPageEventHelper {


    public static final String oldPath = "C:\\Users\\28442\\Desktop\\hy.pdf";
    public static final String newPath = "C:\\Users\\28442\\Desktop\\hy_Chane.pdf";
    private static final Queue<Integer> queue = new LinkedList<>();

    public static void processNewPDF() throws IOException, DocumentException {

    }

    /**
     * Generates a document with a header containing Page x of y and with a Watermark on every page.
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) throws IOException {
//        File newFile = new File(newPath);
//        newFile.getParentFile().mkdirs();
        //获得writer
/*        PdfReader oldReader = new PdfReader(oldPath);
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
        stamper.close();
        oldReader.close();
        PdfWriter writer = stamper.getWriter();*/

        //构造参数
        queue.offer(222);
        queue.offer(9);
        queue.offer(16);
        queue.offer(4);
        queue.offer(015);
        processNewPDF();
        try {
            // step 1: creating the document
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            doc=new Document(new PdfReader(oldPath).getPageSize(1));

            // step 2: creating the writer
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("pageNumbersWatermark.pdf"));

            // step 3: initialisations + opening the document
            writer.setPageEvent(new PageNumbersWatermark());

            doc.open();
            // step 4: adding content
            String text = "some padding text ";
//            for (int k = 0; k < 10; ++k)
                text += text;
            Paragraph p = new Paragraph(text);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);
            // step 5: closing the document
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*添加页码*/
    public void onEndPage(PdfWriter writer, Document document) {
        try {
//            /字体
            BaseFont helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
            PdfContentByte cb = writer.getDirectContent();
//            cb.saveState();
            //文本
            Integer pageNumber = writer.getPageNumber();
            Integer poll;
            if ((poll = queue.poll()) != null) {
                pageNumber = poll;
            }
            String text = "PageNum:" + pageNumber;
            float textSize = helv.getWidthPoint(text, 16);
            float textBase = document.top() + 20;//页码向上偏移
            cb.beginText();
            cb.setFontAndSize(helv, 16);
            cb.setTextMatrix(document.right() - textSize, textBase);//水平位置
            cb.showText(text);
            cb.endText();
//            cb.restoreState();
            cb.sanityCheck();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }
}
