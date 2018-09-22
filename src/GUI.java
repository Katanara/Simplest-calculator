import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    Box box = Box.createVerticalBox();              // интерфейс на основе вертикального бокса  - сверху поле, снизу кнопки
    JPanel jPanel = new JPanel();
    static JTextField jTextField = new JTextField(60);
    GridLayout gridLayout = new GridLayout(5, 4, 4, 2);
    Font font = new Font("Verdana", Font.BOLD, 55);

    String[] hints = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "*", "/", "="};      //подписи кнопок
    JButton[] buttons = new JButton[15];

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

    public void sortButton() {                  // метод, добавляющий массив кнопок в интерфейс, закрепляя за ними слушателей

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
            String s = GUI.jTextField.getText();
            GUI.jTextField.setText(s + event.getActionCommand());       // предыдущий текст + новый
        }
    }

    class ResultListener implements ActionListener {           // слушатель вычисления после знака равно

        @Override
        public void actionPerformed(ActionEvent e) {
            String first = "";                     //первая переменная
            String second = "";                   //вторая переменная
            String operation = "";                  //хранитель оператора вычисления
            String[] strings = GUI.jTextField.getText().split("");       //превращаем текст из текстового поля в массив символов
            Calculating calculating = new Calculating();                       //экземпляр вычисления

            int i = 0;        //счетчик циклов. первый цикл принимает символы первого члена
            while (!(strings[i].equals("+") || strings[i].equals("-") || strings[i].equals("*") || strings[i].equals("/"))) {
                first += strings[i];
                i++;
            }
              //условие работает на оператор, запоминая его в хранитель и записывая первый член в переменную x
            if (strings[i].equals("+") || strings[i].equals("-") || strings[i].equals("*") || strings[i].equals("/")) {
                calculating.x = Double.parseDouble(first);
                operation = strings[i];
                i++;
            }

            while (i != strings.length) {      //второй цикл для записи символов второго члена
                second += strings[i];
                i++;
            }

            calculating.y = Double.parseDouble(second);     //запись второго члена в переменную y

            if (operation.equals("+")) {              // блок условий, определяющий, какой метод вычисления будет вызван. зависит от хранителя оператора вычисления
                GUI.jTextField.setText(Double.toString(calculating.sum()));
            } else if (operation.equals("-")) {
                GUI.jTextField.setText(Double.toString(calculating.dif()));
            } else if (operation.equals("*")) {
                GUI.jTextField.setText(Double.toString(calculating.mult()));
            } else if (operation.equals("/")) {
                GUI.jTextField.setText(Double.toString(calculating.div()));
            } else {
                GUI.jTextField.setText("Кусь");
            }


        }
    }

}