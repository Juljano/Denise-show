module de.juljanno.denise {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires org.jsoup;
    requires json.simple;
    requires java.prefs;
    requires java.desktop;

    opens de.juljano.denise to javafx.fxml;
    exports de.juljano.denise;
}