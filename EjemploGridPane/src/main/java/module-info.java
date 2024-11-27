module es.cheste.ejemplogridpane {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.ejemplogridpane to javafx.fxml;
    exports es.cheste.ejemplogridpane;
}