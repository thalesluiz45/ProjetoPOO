import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LittleBoxd extends GenericWindow {
    private JButton mvsBtn;
    private JButton laterBtn;
    private JLabel titleLabel;

    public LittleBoxd() {
        super("LittleBoxd", 500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título
        titleLabel = new JLabel("LittleBoxd", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Botões
        mvsBtn = new JButton("Filmes");
        laterBtn = new JButton("Ver mais tarde");

        // Adiciona ActionListener ao botão "Ver mais tarde"
        laterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LaterWindow laterWindow = new LaterWindow();
                laterWindow.show();
            }
        });

        // Frame
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.ipadx = 20;
        c.ipady = 20;
        c.gridy = 0;
        frame.add(titleLabel, c);

        frame.add(Box.createRigidArea(new Dimension(0, 60)));

        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(mvsBtn, c);

        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(laterBtn, c);
    }
}
