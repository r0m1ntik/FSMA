module com.fsma {
    requires javafx.fxml;
    requires jade;
    requires java.desktop;
    requires org.jetbrains.annotations;

    opens View to javafx.fxml;
    exports View;
}