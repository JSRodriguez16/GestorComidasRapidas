import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDatos {
    private static final String HOST = System.getenv("HOST") != null ? System.getenv("HOST") : "localhost";
    private static final String PORT = System.getenv("PORT") != null ? System.getenv("PORT") : "5432";
    private static final String DATABASE = System.getenv("DATABASE") != null ? System.getenv("DATABASE") : "gestor_comidas_rapidas";

    private final String url;
    private final String username;
    private final String password;

    public BaseDeDatos() {
        this.url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
        this.username = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "postgres";
        this.password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "postgres";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void conectar() {
        try (Connection conn = getConnection()) {
            conn.isValid(2);
            System.out.println("Conexion establecida con la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}