import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultListener implements ActionListener {           // слушатель вычисления после знака равно

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

        switch (operation) {
            case ("+"):
                GUI.jTextField.setText(Double.toString(calculating.sum()));
                break;
            case ("-"):
                GUI.jTextField.setText(Double.toString(calculating.dif()));
                break;
            case ("*"):
                GUI.jTextField.setText(Double.toString(calculating.mult()));
                break;
            case ("/"):
                GUI.jTextField.setText(Double.toString(calculating.div()));
                break;
        }
    }
}
