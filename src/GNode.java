import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Павел Яковлев on 28.03.14.
 * Класс, создающий вершинки, подготовленные для отрисовки на графе. Задаются координаты вершинок
 */

public class GNode  {
    private static int  nodesCount = 0; // Счетчик числа вершин
    public int id; // номер вершины
    private int x; //координата х вершины
    private int y; // координата у вершины
    private volatile float pheromonLevel = 0;
    ArrayList<Edge> listIn; // динамический массив ссылок на входящие вершин.
    ArrayList<Edge> listOut; // динамический массив ссылок на исходящие вершины.

    //конструктор
    public GNode(int x, int y){
        this.id = nodesCount;
        nodesCount++;
        listIn = new ArrayList<Edge>();
        listOut = new ArrayList<Edge>();
        this.x = x;
        this.y = y;
    }
    // возвращают координаты GNode
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public synchronized void setPheromonLevel(){
        pheromonLevel += 0.5;
    }

    public void evaporatePheromon(){
        pheromonLevel = pheromonLevel * (float)0.95 ; //испарение на 5%
    }

    public float getPheromonLevel(){
        return pheromonLevel;
    }


    /*public void printLinks(){                                  // печатает id всех входящих и выходящих вершины
        for (int i = 0; i < listIn.size(); i++ ){
            System.out.println(id + " in = " + listIn.get(i).id);
        }
        for (int i = 0; i < listOut.size(); i++ ){
            System.out.println(id + " out = " + listOut.get(i).id);
        }
    }*/

    public void addOut (GNode gNodeFinish){          // функция, добавляющая связи между двумя вершинами
        listOut.add(new Edge (gNodeFinish));                  // добавляем out вершину NodeFinish  в массив искомого Node
        gNodeFinish.listIn.add(new Edge(this));               // заносим искомую вершину в in массив конечной вершины NodeFinish
    }

    // пишет статистику, которая нам не особо нужна сейчас
    public void print(){
        System.out.println("My ID  = " + id);
        System.out.println("X = " + x);
        System.out.println("Y = " + y);
    }
}

class Edge {                                   // ребро - основное отличие от просто выходящей вершины в том,
                                               // что кол-во феромона откладывается на ребре, феромон зависит
                                               // от i и j вершины
    private GNode startGNode,
                  finishGNode;
    private volatile float pheromonLevel = 0;


    Edge (GNode finishGNode){
        this.finishGNode = finishGNode;
    }

    public synchronized void setPheromonLevel(){
        pheromonLevel += 0.5;
    }

    public void evaporatePheromon(){
        pheromonLevel = pheromonLevel * (float)0.95 ; //испарение на 5%
    }
    public float getPheromonLevel(){
        return pheromonLevel;
    }

    public GNode getFinishGNode(){
        return finishGNode;
    }
}
