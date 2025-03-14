module ma.ensa.lis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;
    requires com.google.zxing;



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
    requires com.google.zxing.javase;
    requires kernel;
    requires layout;
    requires io;
    requires java.mail;
    requires activation;
    requires com.fazecast.jSerialComm;
    opens ma.ensa.lis.controllers to javafx.fxml;
    opens ma.ensa.lis to javafx.fxml;
    opens ma.ensa.lis.models to javafx.base;

    exports ma.ensa.lis;
    exports ma.ensa.lis.controllers;
    exports ma.ensa.lis.utils;


}