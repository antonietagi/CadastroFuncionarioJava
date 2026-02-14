package View;

import Model.Frota;
import Model.FrotaDAO;
import Model.FrotaTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ViewFrota extends JFrame {

    private FrotaTableModel tableModel = new FrotaTableModel();

    public ViewFrota() {
        initComponents();
        JTFrota.setModel(tableModel);
        carregarDadosBanco();

        JTFrota.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && JTFrota.getSelectedRow() != -1) {
                int selectedRow = JTFrota.getSelectedRow();
                Frota f = tableModel.getRow(selectedRow);
                txtPlaca.setText(f.getPlaca());
                txtModelo.setText(f.getModelo());
                txtAno.setText(String.valueOf(f.getAno()));
            }
        });
    }

    private void carregarDadosBanco() {
        try {
            FrotaDAO dao = new FrotaDAO();
            List<Frota> lista = dao.listar();
            for (Frota f : lista) {
                tableModel.addRow(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtPlaca.setText("");
        txtModelo.setText("");
        txtAno.setText("");
    }

    private void initComponents() {
        JScrollPane scrollPane = new JScrollPane();
        JTFrota = new JTable();
        txtModelo = new JTextField(15);
        txtPlaca = new JTextField(15);
        txtAno = new JTextField(5);
        JLabel lblModelo = new JLabel("Modelo:");
        JLabel lblPlaca = new JLabel("Placa:");
        JLabel lblAno = new JLabel("Ano:");
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");
        btnVoltar = new JButton("Voltar ao Menu");
        JLabel lblTitulo = new JLabel("Cadastro de Frota");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Frota");
        setSize(600, 400);
        setLocationRelativeTo(null);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCampos.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelCampos.add(lblPlaca);
        panelCampos.add(txtPlaca);
        panelCampos.add(lblModelo);
        panelCampos.add(txtModelo);
        panelCampos.add(lblAno);
        panelCampos.add(txtAno);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnAdicionar.setBackground(new Color(70, 130, 180));
        btnAdicionar.setForeground(Color.WHITE);

        btnEditar.setBackground(new Color(255, 140, 0));
        btnEditar.setForeground(Color.WHITE);

        btnDeletar.setBackground(new Color(220, 20, 60));
        btnDeletar.setForeground(Color.WHITE);

        btnVoltar.setBackground(new Color(169, 169, 169));
        btnVoltar.setForeground(Color.BLACK);

        btnAdicionar.addActionListener(evt -> btnAdicionarActionPerformed(evt));
        btnEditar.addActionListener(evt -> btnEditarActionPerformed(evt));
        btnDeletar.addActionListener(evt -> btnDeletarActionPerformed(evt));
        btnVoltar.addActionListener(evt -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnDeletar);
        panelBotoes.add(btnVoltar);

        JTFrota.setModel(tableModel);
        scrollPane.setViewportView(JTFrota);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.WEST);
        panelPrincipal.add(panelBotoes, BorderLayout.CENTER);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        add(panelPrincipal);
        pack();
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Frota f = new Frota();
            f.setPlaca(txtPlaca.getText());
            f.setModelo(txtModelo.getText());
            f.setAno(Integer.parseInt(txtAno.getText()));

            FrotaDAO dao = new FrotaDAO();
            dao.inserir(f);

            tableModel.addRow(f);
            limparCampos();
            JOptionPane.showMessageDialog(this, "Frota adicionada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + e.getMessage());
        }
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = JTFrota.getSelectedRow();
        if (selectedRow != -1) {
            try {
                Frota f = tableModel.getRow(selectedRow);
                f.setPlaca(txtPlaca.getText());
                f.setModelo(txtModelo.getText());
                f.setAno(Integer.parseInt(txtAno.getText()));

                FrotaDAO dao = new FrotaDAO();
                dao.atualizar(f);

                tableModel.updateRow(selectedRow, f);
                limparCampos();
                JOptionPane.showMessageDialog(this, "Frota atualizada com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para editar.");
        }
    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = JTFrota.getSelectedRow();
        if (selectedRow != -1) {
            try {
                Frota f = tableModel.getRow(selectedRow);
                FrotaDAO dao = new FrotaDAO();
                dao.deletar(f.getId());

                tableModel.removeRow(selectedRow);
                limparCampos();
                JOptionPane.showMessageDialog(this, "Frota deletada com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para deletar.");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ViewFrota().setVisible(true));
    }

    private JTable JTFrota;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnVoltar;
    private JTextField txtModelo;
    private JTextField txtPlaca;
    private JTextField txtAno;
}
