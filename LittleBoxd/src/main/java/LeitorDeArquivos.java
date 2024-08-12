import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class LeitorDeArquivos {
    private static File currentFile = null; // Arquivo atualmente aberto

    public static void main(String[] args) {
        //JFrame
        JFrame frame = new JFrame("Filmes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Painel para os botões
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton openButton = new JButton("Abrir arquivo");
        JButton saveButton = new JButton("Salvar arquivo");

        //Tabela
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // Adicionar os botões e a tabela ao painel e à janela
        panel.add(openButton);
        panel.add(saveButton);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Adicionar ActionListener ao botão "Abrir arquivo"
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir um seletor de arquivos
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();
                    // Ler e exibir o conteúdo do arquivo
                    try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                        DefaultTableModel model = new DefaultTableModel();
                        model.setColumnIdentifiers(new String[]{"Filme", "Review", "Nota"});

                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Dividir o texto da linha usando ";"
                            String[] parts = line.split(";");

                            // Colunas
                            String[] row = new String[3];
                            for (int i = 0; i < 3; i++) {
                                if (i < parts.length) {
                                    row[i] = parts[i].trim();
                                } else {
                                    row[i] = "";
                                }
                            }

                            model.addRow(row);
                        }

                        // Atualizar a tabela com os dados lidos
                        table.setModel(model);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Adicionar ActionListener ao botão "Salvar arquivo"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile != null) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        for (int i = 0; i < model.getRowCount(); i++) {
                            StringBuilder line = new StringBuilder();
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                if (j > 0) {
                                    line.append(";");
                                }
                                line.append(model.getValueAt(i, j));
                            }
                            writer.write(line.toString());
                            writer.newLine();
                        }
                        writer.flush();
                        JOptionPane.showMessageDialog(frame, "Arquivo salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Não há arquivo aberto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
