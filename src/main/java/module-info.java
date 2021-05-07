module com.deniz.gymmanagement {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens com.deniz.gymmanagement to javafx.fxml;
    exports com.deniz.gymmanagement;
}
