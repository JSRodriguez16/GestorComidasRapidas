import java.util.Scanner;


public class main {
    
    public static void main(String[] args) {
        BaseDeDatos db = new BaseDeDatos();
        db.conectar();

        ClienteService clienteService = new ClienteService(db);
        ProductoService productoService = new ProductoService(db);

        
        String menu = """
            1. Agregar cliente
            2. Actualizar cliente
            3. Eliminar cliente
            4. Listar clientes
            5. Agregar producto
            6. Actualizar producto
            7. Eliminar producto
            8. Listar productos
            9. Salir
        """;

        boolean salir = false;
        Scanner scanner = new Scanner(System.in);

        
        do {
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            int idCliente, idProducto;

            switch (opcion) {
                case 1:
                    // Lógica para agregar cliente
                    System.out.println("--------------Agregar cliente--------------");
                    
                    String nombre, direccion;
                    int telefono;

                    do {
                        System.out.println("Ingrese el ID del cliente:");
                        idCliente = scanner.nextInt();
                    } while (idCliente <= 0);

                    do {
                        System.out.println("Ingrese el nombre del cliente:");
                        nombre = scanner.next();
                    } while (nombre.isEmpty());
                    
                    do{
                        System.out.println("Ingrese el teléfono del cliente:");
                        telefono = scanner.nextInt();
                    } while (telefono <= 0);

                    do {
                        System.out.println("Ingrese la dirección del cliente:");
                        direccion = scanner.next();
                    } while (direccion.isEmpty());

                    Cliente cliente = new Cliente(idCliente, telefono, nombre, direccion);
                    clienteService.agregarCliente(cliente);

                    break;
                case 2:
                    // Lógica para actualizar cliente
                    System.out.println("--------------Actualizar cliente--------------");

                    System.out.println("Ingrese el ID del cliente a actualizar:");
                    idCliente = scanner.nextInt();

                    System.out.println("Ingrese el nuevo nombre del cliente:");
                    String nuevoNombre = scanner.next();
                    System.out.println("Ingrese el nuevo teléfono del cliente:");
                    int nuevoTelefono = scanner.nextInt();
                    System.out.println("Ingrese la nueva dirección del cliente:");
                    String nuevaDireccion = scanner.next();


                    Cliente clienteActualizado = new Cliente(idCliente, nuevoTelefono, nuevoNombre, nuevaDireccion);
                    clienteService.actualizarCliente(clienteActualizado);

                    break;
                case 3:
                    // Lógica para eliminar cliente
                    System.out.println("--------------Eliminar cliente--------------");
                    
                    System.out.println("Ingrese el ID del cliente a eliminar:");
                    idCliente = scanner.nextInt();
                    clienteService.eliminarCliente(idCliente);

                    
                    break;
                case 4:
                    // Lógica para listar clientes
                    System.out.println("--------------Listar clientes--------------");

                    String clientes = clienteService.listarClientes();
                    System.out.println(clientes);


                    break;
                case 5:
                    // Lógica para agregar producto
                    System.out.println("--------------Agregar producto--------------");

                    double precio;
                    String nombreProducto, categoria, descripcion;

                    do {
                        System.out.println("Ingrese el ID del producto:");
                        idProducto = scanner.nextInt();
                    } while (idProducto <= 0);

                    do {
                        System.out.println("Ingrese el nombre del producto:");
                        nombreProducto = scanner.next();
                    } while (nombreProducto.isEmpty());

                    do {
                        System.out.println("Ingrese la categoría del producto:");
                        categoria = scanner.next();
                    } while (categoria.isEmpty());

                    do {
                        System.out.println("Ingrese la descripción del producto:");
                        descripcion = scanner.next();
                    } while (descripcion.isEmpty());

                    do {
                        System.out.println("Ingrese el precio del producto:");
                        precio = scanner.nextDouble();
                    } while (precio <= 0);

                    Producto producto = new Producto(idProducto, nombreProducto, precio, categoria, descripcion);
                    productoService.agregarProducto(producto);

                    break;
                case 6:
                    // Lógica para actualizar producto
                    System.out.println("--------------Actualizar producto--------------");

                    System.out.println("Ingrese el ID del producto a actualizar:");
                    idProducto = scanner.nextInt();
                    System.out.println("Ingrese el nuevo nombre del producto:");
                    String nuevoNombreProducto = scanner.next();
                    System.out.println("Ingrese la nueva categoría del producto:");
                    String nuevaCategoria = scanner.next();
                    System.out.println("Ingrese la nueva descripción del producto:");
                    String nuevaDescripcion = scanner.next();
                    System.out.println("Ingrese el nuevo precio del producto:");
                    double nuevoPrecio = scanner.nextDouble();
                    
                    Producto productoActualizado = new Producto(idProducto, nuevoNombreProducto, nuevoPrecio, nuevaCategoria, nuevaDescripcion);
                    productoService.actualizarProducto(productoActualizado);

                    break;
                case 7:
                    // Lógica para eliminar producto
                    System.out.println("--------------Eliminar producto--------------");
                    System.out.println("Ingrese el ID del producto a eliminar:");

                    idProducto = scanner.nextInt();
                    productoService.eliminarProducto(idProducto);

                    break;
                case 8:
                    // Lógica para listar productos
                    System.out.println("--------------Listar productos--------------");

                    String productos = productoService.listarProductos();
                    System.out.println(productos);

                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (!salir);

        scanner.close();

    }

}
