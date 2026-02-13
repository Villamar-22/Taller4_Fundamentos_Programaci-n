package TALLER4_FP;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class RegistroFacturacion {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Registro de Facturas");
        frame.setSize(420, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 5, 5));

        JLabel lb_numero = new JLabel("Número de factura:");
        JTextField txtNumero = new JTextField();

        JLabel lb_cliente = new JLabel("Nombre del cliente:");
        JTextField txtCliente = new JTextField();

        JLabel lb_monto = new JLabel("Monto:");
        JTextField txtMonto = new JTextField();

        JCheckBox checkPagada = new JCheckBox("Factura pagada");

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnGuardar = new JButton("Guardar en archivo");
        JButton btnSalir = new JButton("Salir");

        ArrayList<Object[]> facturas = new ArrayList<>();

        // REGISTRAR
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String numero = txtNumero.getText();
                String cliente = txtCliente.getText();
                String monto = txtMonto.getText();
                String estado = checkPagada.isSelected() ? "Pagada" : "Pendiente";

                Object[] factura = {numero, cliente, monto, estado};
                facturas.add(factura);

                JOptionPane.showMessageDialog(frame,
                        "Factura registrada correctamente");

                txtNumero.setText("");
                txtCliente.setText("");
                txtMonto.setText("");
                checkPagada.setSelected(false);
            }
        });

        // CONSULTAR POR NÚMERO
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String buscar = JOptionPane.showInputDialog(
                        frame, "Ingrese el número de factura:");

                boolean encontrada = false;

                for (Object[] f : facturas) {
                    if (f[0].equals(buscar)) {
                        JOptionPane.showMessageDialog(frame,
                                "Factura encontrada:\n"
                              + "Número: " + f[0] + "\n"
                              + "Cliente: " + f[1] + "\n"
                              + "Monto: $" + f[2] + "\n"
                              + "Estado: " + f[3]);
                        encontrada = true;
                        break;
                    }
                }

                if (!encontrada) {
                    JOptionPane.showMessageDialog(frame,
                            "La factura no se encuentra registrada");
                }
            }
        });

        // GUARDAR EN ARCHIVO
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw = new BufferedWriter(
                            new FileWriter("facturas_yamileth.txt", true)
                    );

                    for (Object[] f : facturas) {
                        bw.write(f[0] + " | " + f[1] + " | $" + f[2] + " | " + f[3]);
                        bw.newLine();
                    }

                    bw.close();
                    JOptionPane.showMessageDialog(frame,
                            "Facturas guardadas en archivo");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Error al guardar archivo");
                }
            }
        });

        // SALIR
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(lb_numero);
        panel.add(txtNumero);
        panel.add(lb_cliente);
        panel.add(txtCliente);
        panel.add(lb_monto);
        panel.add(txtMonto);
        panel.add(checkPagada);
        panel.add(btnRegistrar);
        panel.add(btnConsultar);
        panel.add(btnGuardar);
        panel.add(btnSalir);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
