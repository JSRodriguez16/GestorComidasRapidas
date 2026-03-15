public class Cliente {
    int idCliente;
    int telefono;
    String nombre;
    String direccion;
    
    public Cliente(int idCliente, int telefono, String nombre, String direccion) {
        this.idCliente = idCliente;
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}