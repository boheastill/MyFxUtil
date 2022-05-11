package cn.i623.myspringutil.fun;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChangeText {

    public static final String SRC = "C:\\Users\\28442\\Desktop\\hy.pdf";
    public static final String DEST = "C:\\Users\\28442\\Desktop\\hy_Chane2.pdf";

    public static void processNewPDF() throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        PdfReader reader = new PdfReader(SRC);


        PdfDictionary dict = reader.getPageN(1);
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

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(DEST));
        PdfWriter writer = stamper.getWriter();
        stamper.close();
        reader.close();
    }

}