module ma.ensa.lis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens ma.ensa.lis to javafx.fxml;
    exports ma.ensa.lis;
}