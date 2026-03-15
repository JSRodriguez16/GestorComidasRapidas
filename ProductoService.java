import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoService {
    private final BaseDeDatos baseDeDatos;

    public ProductoService(BaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (id_producto, nombre, precio, categoria, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getCategoria());
            ps.setString(5, producto.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }

    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, categoria = ?, descripcion = ? WHERE id_producto = ?";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getCategoria());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public String listarProductos() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT id_producto, nombre, precio, categoria, descripcion FROM producto ORDER BY id_producto";
        try (Connection conn = baseDeDatos.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append("Id: ").append(rs.getInt("id_producto"))
                  .append(", Nombre: ").append(rs.getString("nombre"))
                  .append(", Precio: ").append(rs.getDouble("precio"))
                  .append(", Categoria: ").append(rs.getString("categoria"))
                  .append(", Descripcion: ").append(rs.getString("descripcion"))
                  .append("\n");
            }
            return sb.toString();
        } catch (SQLException e) {
            return "Error al consultar productos: " + e.getMessage();
        }
    }
}