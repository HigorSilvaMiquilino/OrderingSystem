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
    requires kernel;
    requires layout;
    requires io;
    requires org.slf4j;
    requires com.github.librepdf.openpdf;

    opens com.example.orderingsystem.controller to javafx.fxml;
    exports com.example.orderingsystem;


}