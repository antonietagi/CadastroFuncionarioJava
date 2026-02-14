/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioTableModel extends AbstractTableModel {

    private List<Funcionario> lista = new ArrayList<>();
    private String[] colunas = {"Nome", "Cargo", "Salário"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario f = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return f.getNome();
            case 1: return f.getCargo();
            case 2: return f.getSalario();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // Métodos auxiliares

    public void addRow(Funcionario f) {
        lista.add(f);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public void removeRow(int index) {
        lista.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void updateRow(int index, Funcionario f) {
        lista.set(index, f);
        fireTableRowsUpdated(index, index);
    }

    public Funcionario getRow(int index) {
        return lista.get(index);
    }
}

