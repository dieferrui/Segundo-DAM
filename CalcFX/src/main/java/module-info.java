module es.cheste.calcfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.calcfx to javafx.fxml;
    exports es.cheste.calcfx;
}