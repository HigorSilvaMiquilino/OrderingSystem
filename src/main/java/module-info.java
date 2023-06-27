module com.example.orderingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.base;



    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.sql.rowset;
    requires java.transaction.xa;

    opens com.example.orderingsystem to javafx.fxml;
    exports com.example.orderingsystem;
    exports com.example.orderingsystem.form;
    opens com.example.orderingsystem.form to javafx.fxml;


}