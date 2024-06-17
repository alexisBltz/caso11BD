package org.app;

public class App {
    public static void main(String[] args) {
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        // Crear un nuevo Trabajador
        Trabajador trabajador0 = new Trabajador();
        trabajador0.setTrabajadorId(2);
        trabajador0.setTrabajadorNombre("Alexis");
        trabajador0.setTrabajaTipoTraId('F');
        trabajador0.setTraFecIng(new java.util.Date());
        trabajador0.setTraFecCes(new java.util.Date());
        trabajador0.setTraFecUltSalVac(new java.util.Date());
        trabajador0.setTraEstTra('A');
        trabajador0.setTraEstRegTra('A');
        trabajador0.setCueCorNum("127636127378");
        trabajador0.setCodCenCos('A');
        trabajadorDAO.createTrabajador(trabajador0);

        // Crear un nuevo Trabajador
        Trabajador trabajador = new Trabajador();
        trabajador.setTrabajadorId(1);
        trabajador.setTrabajadorNombre("Juan Pérez");
        trabajador.setTrabajaTipoTraId('F');
        trabajador.setTraFecIng(new java.util.Date());
        trabajador.setTraFecCes(new java.util.Date());
        trabajador.setTraFecUltSalVac(new java.util.Date());
        trabajador.setTraEstTra('A');
        trabajador.setTraEstRegTra('A');
        trabajador.setCueCorNum("121212121212");
        trabajador.setCodCenCos('I');
        trabajadorDAO.createTrabajador(trabajador);

        // Leer y listar todos los Trabajadores
        System.out.println("Lista de Trabajadores:");
        for (Trabajador t : trabajadorDAO.readTrabajadores()) {
            System.out.println("ID: " + t.getTrabajadorId() + ", Nombre: " + t.getTrabajadorNombre());
        }

        // Actualizar un Trabajador
        trabajador.setTrabajadorNombre("Carlos López");
        trabajadorDAO.updateTrabajador(trabajador);

        // Leer y listar todos los Trabajadores después de la actualización
        System.out.println("Lista de Trabajadores después de la actualización:");
        for (Trabajador t : trabajadorDAO.readTrabajadores()) {
            System.out.println("ID: " + t.getTrabajadorId() + ", Nombre: " + t.getTrabajadorNombre());
        }

        // Eliminar un Trabajador
        trabajadorDAO.deleteTrabajador(1);

        // Leer y listar todos los Trabajadores después de la eliminación
        System.out.println("Lista de Trabajadores después de la eliminación:");
        for (Trabajador t : trabajadorDAO.readTrabajadores()) {
            System.out.println("ID: " + t.getTrabajadorId() + ", Nombre: " + t.getTrabajadorNombre());
        }
    }
}
