module es.cheste.cara {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.cara to javafx.fxml;
    exports es.cheste.cara;
}