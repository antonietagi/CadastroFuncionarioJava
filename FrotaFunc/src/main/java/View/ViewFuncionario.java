package View;

import Model.Funcionario;
import Model.FuncionarioDAO;
import Model.FuncionarioTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ViewFuncionario extends JFrame {

    private FuncionarioTableModel tableModel = new FuncionarioTableModel();

    public ViewFuncionario() {
        initComponents();
        JTableFuncionario.setModel(tableModel);
        carregarDadosBanco();

        JTableFuncionario.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && JTableFuncionario.getSelectedRow() != -1) {
                int selectedRow = JTableFuncionario.getSelectedRow();
                Funcionario f = tableModel.getRow(selectedRow);
                txtNome.setText(f.getNome());
                txtCargo.setText(f.getCargo());
                txtSalario.setText(String.valueOf(f.getSalario()));
            }
        });
    }

    private void carregarDadosBanco() {
        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            List<Funcionario> lista = dao.listar();
            for (Funcionario f : lista) {
                tableModel.addRow(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
    }

    private void initComponents() {
        JScrollPane scrollPane = new JScrollPane();
        JTableFuncionario = new JTable();
        txtNome = new JTextField(15);
        txtCargo = new JTextField(15);
        txtSalario = new JTextField(10);
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCargo = new JLabel("Cargo:");
        JLabel lblSalario = new JLabel("Salário:");
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");
        btnVoltar = new JButton("Voltar ao Menu");
        JLabel lblTitulo = new JLabel("Cadastro de Funcionários");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Funcionários");
        setSize(600, 400);
        setLocationRelativeTo(null);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCampos.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelCampos.add(lblNome);
        panelCampos.add(txtNome);
        panelCampos.add(lblCargo);
        panelCampos.add(txtCargo);
        panelCampos.add(lblSalario);
        panelCampos.add(txtSalario);

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

        JTableFuncionario.setModel(tableModel);
        scrollPane.setViewportView(JTableFuncionario);
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
            Funcionario f = new Funcionario();
            f.setNome(txtNome.getText());
            f.setCargo(txtCargo.getText());
            f.setSalario(Double.parseDouble(txtSalario.getText()));

            FuncionarioDAO dao = new FuncionarioDAO();
            dao.inserir(f);

            tableModel.addRow(f);
            limparCampos();
            JOptionPane.showMessageDialog(this, "Funcionário adicionado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + e.getMessage());
        }
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = JTableFuncionario.getSelectedRow();
        if (selectedRow != -1) {
            try {
                Funcionario f = tableModel.getRow(selectedRow);
                f.setNome(txtNome.getText());
                f.setCargo(txtCargo.getText());
                f.setSalario(Double.parseDouble(txtSalario.getText()));

                FuncionarioDAO dao = new FuncionarioDAO();
                dao.atualizar(f);

                tableModel.updateRow(selectedRow, f);
                limparCampos();
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para editar.");
        }
    }

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = JTableFuncionario.getSelectedRow();
        if (selectedRow != -1) {
            try {
                Funcionario f = tableModel.getRow(selectedRow);
                FuncionarioDAO dao = new FuncionarioDAO();
                dao.deletar(f.getId());

                tableModel.removeRow(selectedRow);
                limparCampos();
                JOptionPane.showMessageDialog(this, "Funcionário deletado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para deletar.");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ViewFuncionario().setVisible(true));
    }

    private JTable JTableFuncionario;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnVoltar;
    private JTextField txtNome;
    private JTextField txtCargo;
    private JTextField txtSalario;
}
