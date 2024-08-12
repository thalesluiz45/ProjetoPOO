import javax.swing.*;
import java.awt.*;

public class MovieWindow extends GenericWindow {
    private JLabel labelNome;
    private JTextField textNome;
    private JButton btnOk;
    private JButton btnCancelar;
    private JPanel panelBotoes;
    private LaterWindow main;
    private Movie movie;

    public MovieWindow(LaterWindow main, Movie movie) {
        super(movie == null ? "Novo filme" : "Editar filme", 400, 300);
        this.main = main;
        this.movie = movie;
        configurar();
    }

    private void configurar() {
        labelNome = new JLabel("Nome: ");
        textNome = new JTextField(20);
        btnOk = new JButton(movie == null ? "Adicionar" : "Salvar");
        btnCancelar = new JButton("Cancelar");
        panelBotoes = new JPanel();

        if (movie != null) {
            textNome.setText(movie.getNome());
        }

        btnOk.addActionListener(e -> {
            String nome = textNome.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha o campo.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (movie == null) {
                main.addMovie(new Movie(nome));
            } else {
                movie.setNome(nome);
                main.editarMovie(movie);
            }
            frame.setVisible(false);
        });

        btnCancelar.addActionListener(e -> frame.setVisible(false));

        panelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(btnOk);
        panelBotoes.add(btnCancelar);

        configurarLayout();
    }

    private void configurarLayout() {
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Label Nome
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        frame.add(labelNome, c);

        // Campo Nome
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(textNome, c);


        // Painel de Botões
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        frame.add(panelBotoes, c);

        frame.pack();
    }
}