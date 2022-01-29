module cn.i623.myfxutil {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires java.net.http;

    requires org.apache.commons.codec;
    requires poi;


    exports cn.i623.myfxutil;
    exports cn.i623.myfxutil.contorl;

    opens cn.i623.myfxutil to javafx.fxml;
    opens cn.i623.myfxutil.contorl;

}