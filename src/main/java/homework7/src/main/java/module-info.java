module ru.miroshka.homework7 {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.miroshka.homework7.client;
    opens ru.miroshka.homework7.client to javafx.fxml;

}