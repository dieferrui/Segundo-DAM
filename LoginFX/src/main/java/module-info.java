module es.cheste.loginfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.loginfx to javafx.fxml;
    exports es.cheste.loginfx;
}