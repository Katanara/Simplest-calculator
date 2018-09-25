import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultListener implements ActionListener {           // слушатель вычисления после знака равно

    @Override
    public void actionPerformed(ActionEvent e) {
        String first = "";                     //первая переменная
        String second = "";                   //вторая переменная
        char operation = ' ';                  //хранитель оператора вычисления
        String text = GUI.jTextField.getText();
        Calculating calculating = new Calculating();                       //экземпляр вычисления

        int i = 0;        //счетчик циклов. первый цикл принимает символы первого члена
        while (!(text.charAt(i) == '+' || text.charAt(i) == '-' || text.charAt(i) == '*' || text.charAt(i) == '/')) {
            first += text.charAt(i);
            i++;
        }
        //условие работает на оператор, запоминая его в хранитель и записывая первый член в переменную x
        if (text.charAt(i) == '+' || text.charAt(i) == '-' || text.charAt(i) == '*' || text.charAt(i) == '/') {
            calculating.x = Double.parseDouble(first);
            operation = text.charAt(i);
            i++;
        }

        while (i != text.length()) {      //второй цикл для записи символов второго члена
            second += text.charAt(i);
            i++;
        }

        calculating.y = Double.parseDouble(second);     //запись второго члена в переменную y

        switch (operation) {
            case ('+'):
                GUI.jTextField.setText(Double.toString(calculating.sum()));
                break;
            case ('-'):
                GUI.jTextField.setText(Double.toString(calculating.dif()));
                break;
            case ('*'):
                GUI.jTextField.setText(Double.toString(calculating.mult()));
                break;
            case ('/'):
                GUI.jTextField.setText(Double.toString(calculating.div()));
                break;
        }
    }
}
