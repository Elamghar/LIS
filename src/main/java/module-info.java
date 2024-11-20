module ma.ensa.lis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;
    requires java.desktop;
    requires com.google.gson;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.management;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    opens ma.ensa.lis.controllers to javafx.fxml;
    opens ma.ensa.lis to javafx.fxml;
    opens ma.ensa.lis.models to javafx.base;
    opens javafx.application to javafx.base;

    exports ma.ensa.lis;
}