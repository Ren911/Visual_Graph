import javax.swing.*;
import java.awt.*;
import java.lang.*;


/**
 * Created by Павел Яковлев and modified by Ринат Даутов on 14.03.14 till the end.
 * Класс, контролирующий всю внутренню деятельность системы. То бишь main
 */
public class Driver {

    private GraphWriter graphWriter; // класс, занимающийся отрисовкой графа

    public  Driver (GGraph graph, Ant[] AntColony) {
        try {
            // Make look and feel, system specific
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        int fringe = 40; // размер рамки

        // создаем graphWriter
        graphWriter = new GraphWriter(800, 600, fringe, graph, AntColony);

        // Panel  - в ней будет отрисовываться сам граф
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.add(graphWriter, BorderLayout.CENTER);

        // оконное приложение, с которым происходит работа
        AppWindow appwin = new AppWindow();                // создание окна
        appwin.setSize(800, 600);                          // установка размера, названия и добавления панели отрисовки
        appwin.setTitle("An Ant visualization");
        appwin.add(graphPanel, BorderLayout.CENTER);
        appwin.setVisible(true);                           // делаем видимым
    }


    public static void main(String [ ] args){
        Ant[] AntColony;                                    // инициализация колонии муравьев.

        GGraph testGGraph = new GGraph(3,100);            // создание тестового графа

        AntColony = new Ant[10];                            // содание колонии

        System.out.println("граф создан");                  // сервисная информация

        for (int i = 0; i < AntColony.length; i++ ){        // инициализация муравьев.
            System.out.println("вход в цикл создания муравьев");
            AntColony[i] = new Ant(testGGraph);
            AntColony[i].start();
            System.out.println("Муравей создан" + i);
        }

        Unconscious Un1 = new Unconscious(testGGraph);    // создание бессознательного
        Un1.start();                                      // старт потока
        new Driver(testGGraph, AntColony);                // визуализация тест. графа
       }
    }

