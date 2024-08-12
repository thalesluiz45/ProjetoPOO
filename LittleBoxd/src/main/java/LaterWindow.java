import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LaterWindow extends GenericWindow {
    private JList<Movie> list;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;
    private List<Movie> movie;

    public LaterWindow() {
        super("Ver mais tarde", 400, 400);
        movie = new ArrayList<>();
        movie.add(new Movie("Ratatouille"));
        movie.add(new Movie("Cidade de Deus"));
        list = new JList<>();
        update();
        list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");

        btnAdicionar.addActionListener(e -> {
            new MovieWindow(this, null).show();
        });

        btnEditar.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                Movie m = movie.get(index);
                new MovieWindow(this, m).show();
            }
        });

        btnRemover.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index >= 0){
                movie.remove(index);
                update();
            }
        });

        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.ipady = 100;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 4;
        frame.add(new JScrollPane(list), c);

        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        frame.add(btnAdicionar, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        frame.add(btnEditar, c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        frame.add(btnRemover, c);

        frame.pack();
    }

    public void addMovie(Movie m) {
        movie.add(m);
        update();
    }

    public void editarMovie(Movie m) {
        int index = movie.indexOf(m);
        if (index >= 0) {
            movie.get(index).setNome(m.getNome());
            update();
        }
    }

    public void update() {
        movie.sort(Comparator.comparing(Movie::getNome));
        list.setListData(movie.toArray(new Movie[0]));
    }
}