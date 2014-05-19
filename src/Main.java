import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.io.*;
import java.util.Scanner;


/**
 * Created by Павел Яковлев and modified by Ринат Даутов on 14.03.14 till the end.
 * Класс, контролирующий всю внутренню деятельность системы. То бишь main
 */
public class Main {

    public static void main(String [ ] args){
        final Ant[] AntColony;                                   // инициализация колонии муравьев.
        final GGraph testGGraph = GGraph.myread();               // тестовый граф считанный из файла

        testGGraph.adjust();                                     // пружины
        AntColony = new Ant[15];                                 // содание колонии

        System.out.println("граф создан");                       // сервисная информация

        Unconscious Un1 = new Unconscious(testGGraph,AntColony);    // создание бессознательного
        Un1.start();                                                // старт потока

        // Создание окна отдельным потоком
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppWindow(testGGraph, AntColony);
            }
        });
    }
}