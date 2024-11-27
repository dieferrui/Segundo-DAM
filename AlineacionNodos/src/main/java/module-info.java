module es.cheste.alineacionnodos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.cheste.alineacionnodos to javafx.fxml;
    exports es.cheste.alineacionnodos;

    opens es.cheste.cara to javafx.fxml;
    exports es.cheste.cara;

    opens es.cheste.convertidormetrico to javafx.fxml;
    exports es.cheste.convertidormetrico;

    opens es.cheste.ejemploborderpane to javafx.fxml;
    exports es.cheste.ejemploborderpane;

    opens es.cheste.ejemplogridpane to javafx.fxml;
    exports es.cheste.ejemplogridpane;

    opens es.cheste.ejemplostackpane to javafx.fxml;
    exports es.cheste.ejemplostackpane;

    opens es.cheste.presioname to javafx.fxml;
    exports es.cheste.presioname;

    opens es.cheste.renctangulogui to javafx.fxml;
    exports es.cheste.renctangulogui;

    opens es.cheste.bordes to javafx.fxml;
    exports es.cheste.bordes;

    opens es.cheste.titledpane to javafx.fxml;
    exports es.cheste.titledpane;

    opens es.cheste.fuentes to javafx.fxml;
    exports es.cheste.fuentes;
}