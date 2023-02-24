module com.fsma {
    requires javafx.fxml;
    requires jade;
    requires java.desktop;

    opens com.fsma to javafx.fxml;
    exports com.fsma;
}