import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private Box box = Box.createVerticalBox();  // интерфейс на основе вертикального бокса  - сверху поле, снизу кнопки
    public static JTextField jTextField = new JTextField(60);      //здесь нужен public
    private JPanel jPanel = new JPanel();
    private GridLayout gridLayout = new GridLayout(5, 4, 4, 2);
    private Font font = new Font("Verdana", Font.BOLD, 55);

    private  String[] hints = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "*", "/", "="};      //подписи кнопок
    private  JButton[] buttons = new JButton[15];

    GUI(String title) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(430, 600);
        setResizable(true);
        getContentPane().add(box);
        box.add(jTextField);
        jTextField.setMaximumSize(new Dimension(425, 160));
        jTextField.setFont(font);
        box.add(jPanel);
        jPanel.setMaximumSize(new Dimension(425, 500));
        jPanel.setLayout(gridLayout);                  // применение табличной формы для будущих кнопок
        sortButton();

    }

    private void sortButton() {                  // метод, добавляющий массив кнопок в интерфейс, закрепляя за ними слушателей

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(hints[i]);
            jPanel.add(buttons[i]);
            if (i < 14)
                buttons[i].addActionListener(new ButtonListener());
            else if (i==14)
                buttons[14].addActionListener(new ResultListener());         // для знака равно другой слушатель

        }

    }

    class ButtonListener implements ActionListener {                 // слушатель ввода. выводит текст из аргумента кнопки в поле
        @Override
        public void actionPerformed(ActionEvent event) {
            String previousText = GUI.jTextField.getText();
            GUI.jTextField.setText(previousText + event.getActionCommand());       // предыдущий текст + новый
        }
    }

}

