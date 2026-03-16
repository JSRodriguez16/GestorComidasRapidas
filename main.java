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
            int opcion = leerEnteroPositivo(scanner, "Seleccione una opción:");

            int idCliente, idProducto;

            switch (opcion) {
                case 1:
                    // Lógica para agregar cliente
                    System.out.println("--------------Agregar cliente--------------");
                    
                    String nombre, direccion;
                    int telefono;

                    idCliente = leerEnteroPositivo(scanner, "Ingrese el ID del cliente:");

                    nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del cliente:");
                    
                    telefono = leerEnteroPositivo(scanner, "Ingrese el teléfono del cliente:");

                    direccion = leerTextoNoVacio(scanner, "Ingrese la dirección del cliente:");

                    Cliente cliente = new Cliente(idCliente, telefono, nombre, direccion);
                    clienteService.agregarCliente(cliente);

                    break;
                case 2:
                    // Lógica para actualizar cliente
                    System.out.println("--------------Actualizar cliente--------------");
                    

                    idCliente = leerEnteroPositivo(scanner, "Ingrese el ID del cliente a actualizar:");

                    if (clienteService.listarClientes().contains("Id: " + idCliente)) {
                        System.out.println("Cliente encontrado. Proceda a ingresar los nuevos datos.");
                    } else {
                        System.out.println("Cliente con ID " + idCliente + " no encontrado.");
                        break;
                    }

                    String nuevoNombre, nuevaDireccion;
                    int nuevoTelefono;

                    nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del cliente:");

                    nuevoTelefono = leerEnteroPositivo(scanner, "Ingrese el nuevo teléfono del cliente:");

                    nuevaDireccion = leerTextoNoVacio(scanner, "Ingrese la nueva dirección del cliente:");


                    Cliente clienteActualizado = new Cliente(idCliente, nuevoTelefono, nuevoNombre, nuevaDireccion);
                    clienteService.actualizarCliente(clienteActualizado);

                    break;
                case 3:
                    // Lógica para eliminar cliente
                    System.out.println("--------------Eliminar cliente--------------");
                    
                    idCliente = leerEnteroPositivo(scanner, "Ingrese el ID del cliente a eliminar:");
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

                    idProducto = leerEnteroPositivo(scanner, "Ingrese el ID del producto:");

                    nombreProducto = leerTextoNoVacio(scanner, "Ingrese el nombre del producto:");

                    categoria = leerTextoNoVacio(scanner, "Ingrese la categoría del producto:");

                    descripcion = leerTextoNoVacio(scanner, "Ingrese la descripción del producto:");

                    precio = leerDecimalPositivo(scanner, "Ingrese el precio del producto:");

                    Producto producto = new Producto(idProducto, nombreProducto, precio, categoria, descripcion);
                    productoService.agregarProducto(producto);

                    break;
                case 6:
                    // Lógica para actualizar producto
                    System.out.println("--------------Actualizar producto--------------");

                    idProducto = leerEnteroPositivo(scanner, "Ingrese el ID del producto a actualizar:");

                    if (productoService.listarProductos().contains("Id: " + idProducto)) {
                        System.out.println("Producto encontrado. Proceda a ingresar los nuevos datos.");
                    } else {
                        System.out.println("Producto con ID " + idProducto + " no encontrado.");
                        break;
                    }

                    String nuevoNombreProducto, nuevaCategoria, nuevaDescripcion;
                    double nuevoPrecio;
                    
                    nuevoNombreProducto = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del producto:");

                    nuevoPrecio = leerDecimalPositivo(scanner, "Ingrese el nuevo precio del producto:");

                    nuevaCategoria = leerTextoNoVacio(scanner, "Ingrese la nueva categoría del producto:");

                    nuevaDescripcion = leerTextoNoVacio(scanner, "Ingrese la nueva descripción del producto:");
                    
                    Producto productoActualizado = new Producto(idProducto, nuevoNombreProducto, nuevoPrecio, nuevaCategoria, nuevaDescripcion);
                    productoService.actualizarProducto(productoActualizado);

                    break;
                case 7:
                    // Lógica para eliminar producto
                    System.out.println("--------------Eliminar producto--------------");
                    idProducto = leerEnteroPositivo(scanner, "Ingrese el ID del producto a eliminar:");
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

    private static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero mayor a 0.");
            }
        }
    }

    private static double leerDecimalPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(entrada);
                if (valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número decimal mayor a 0.");
            }
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Entrada inválida. El texto no puede estar vacío.");
        }
    }

}
