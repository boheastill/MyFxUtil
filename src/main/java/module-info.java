module cn.i623.myfxutil {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires java.net.http;

    requires org.apache.commons.codec;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    exports cn.i623.myfxutil;
    exports cn.i623.myfxutil.contorl;

    requires org.apache.pdfbox;
    opens org.apache.pdfbox;

    opens cn.i623.myfxutil to javafx.fxml;
    opens cn.i623.myfxutil.contorl;
}