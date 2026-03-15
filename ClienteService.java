import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteService {
    private final BaseDeDatos baseDeDatos;

    public ClienteService(BaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (id_cliente, telefono, nombre, direccion) VALUES (?, ?, ?, ?)";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getTelefono());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getDireccion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }
    }

    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET telefono = ?, nombre = ?, direccion = ? WHERE id_cliente = ?";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getTelefono());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setInt(4, cliente.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT id_cliente, nombre, telefono, direccion FROM cliente ORDER BY id_cliente";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append("Id: ").append(rs.getInt("id_cliente"))
                  .append(", Nombre: ").append(rs.getString("nombre"))
                  .append(", Telefono: ").append(rs.getInt("telefono"))
                  .append(", Direccion: ").append(rs.getString("direccion"))
                  .append("\n");
            }
            return sb.toString();
        } catch (SQLException e) {
            return "Error al consultar clientes: " + e.getMessage();
        }
    }
}