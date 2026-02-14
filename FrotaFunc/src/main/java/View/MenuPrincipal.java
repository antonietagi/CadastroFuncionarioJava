/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        initComponents();
    }

    private void initComponents() {

        JLabel lblTitulo = new JLabel("Sistema de Gestão");
        JButton btnFrota = new JButton("Frota");
        JButton btnFuncionario = new JButton("Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setSize(400, 250);
        setLocationRelativeTo(null);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Fonte maior e cor preta
        Font botaoFonte = new Font("Segoe UI", Font.BOLD, 16);
        
        btnFrota.setFont(botaoFonte);
        btnFrota.setForeground(Color.BLACK);
        btnFrota.setBackground(new Color(173, 216, 230)); // Azul clarinho
        btnFrota.setPreferredSize(new Dimension(200, 40)); // Tamanho maior

        btnFuncionario.setFont(botaoFonte);
        btnFuncionario.setForeground(Color.BLACK);
        btnFuncionario.setBackground(new Color(144, 238, 144)); // Verde clarinho
        btnFuncionario.setPreferredSize(new Dimension(200, 40)); // Tamanho maior

        // Ações
        btnFrota.addActionListener(e -> {
            new ViewFrota().setVisible(true);
            dispose();
        });

        btnFuncionario.addActionListener(e -> {
            new ViewFuncionario().setVisible(true);
            dispose();
        });

        JPanel panelBotoes = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotoes.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBotoes.add(btnFrota);
        panelBotoes.add(btnFuncionario);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotoes, BorderLayout.CENTER);

        add(panelPrincipal);

        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
