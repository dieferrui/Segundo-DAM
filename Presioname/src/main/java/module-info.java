module es.cheste.presioname {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.presioname to javafx.fxml;
    exports es.cheste.presioname;
}