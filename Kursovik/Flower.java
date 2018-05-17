import  java.awt.*;
import  java.awt.geom.*;
import  javax.swing.*; 
public class Flower extends JComponent{
  protected void paintComponent (Graphics g){
    Graphics2D g2d=(Graphics2D)g.create();
    float [ ][ ] dashed={{10,10},{1,50}}; //варианты прерывистой линии
    BasicStroke [ ] Pen = new BasicStroke [4]; //5 видов перьев
    Pen[0] = new BasicStroke(8,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    Pen[1] = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,10, dashed[0],0);
    Pen[2] = new BasicStroke(20,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL,10, dashed[1],0);
    Pen[3] = new BasicStroke(15,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    // Параметры конструктора: ширина, окончание, соединение,
    Color c1 = new Color(0x3CB371);//Темнозеленый
    Color c2 = new Color(0xADFF2F);//Светлозелёный
    Color c3 = new Color(0x8B4513);//Коричневый
    Color c4 = new Color(0xEE82EE);// лепестки
    Color c5 = new Color(0xFAEBD7);//для раскраски горшка
    Color c6 = new Color(0xF0E68C);//для семян
    g2d.setColor(c5);
    g2d.setStroke(Pen[3]);
    g2d.fillRect(200,520,160,130);
    g2d.fillRect(180,475,210,60);
    g2d.drawLine(170,465,205,630);
    g2d.drawLine(180,475,215,630);
    g2d.drawLine(180,475,215,630);
    g2d.drawLine(395,460,355,630);
    g2d.drawLine(380,475,340,630);
    g2d.setStroke(Pen[0]); //выбрано перо 1
    g2d.setColor(c3);
    g2d.fillOval(158, 368, 245, 126);//земля
    g2d.setColor(c4);//Color.green);
    g2d.fillOval(230, 40, 90, 90);//лепесток лев верх
    g2d.fillOval(300, 70, 90, 90);//лепесток прав верх
    g2d.fillOval(310, 145, 90, 90);//лепесток прав центр
    g2d.fillOval(255, 190, 90, 90);//лепесток прав нижн
    g2d.fillOval(160, 70, 90, 90);//лепесток лев верх
    g2d.fillOval(145, 145, 90, 90);//лепесток лев цент
    g2d.fillOval(200, 190, 90, 90);//лепесток лев нижн
    g2d.setColor(c1);
    g2d.fillOval(200,95,150,150);//лицо
    g2d.setStroke(Pen[0]);
    g2d.setColor(c5);
    g2d.drawOval(155, 365, 250, 130);// контур горшка
    g2d.drawLine(155,440,200,650);//лев граница горшка
    g2d.drawLine(405,440,360,650);//прав граница горшка
    g2d.drawLine(200,650,360,650);//дно
    g2d.setColor(c2);//Color.yellow); //смена цвета
    g2d.drawOval(200, 95, 150, 150);//контур лица
    g2d.drawLine(265,250,265,400);//лев контур ствола
    g2d.drawLine(285,250,285,400);//прав контур ствола
    g2d.fillOval(230, 140, 30, 30); //левый глаз
    g2d.fillOval(290, 140, 30, 30);// правый глаз
    g2d.drawArc(238, 165, 75, 50, 180, 180);//улыбка
    g2d.drawLine(290,320,440,250);//верх контур прав руки
    g2d.drawLine(290,340,440,270);//ниж контур прав руки
    g2d.drawLine(260,320,140,250);//-"- лев рук
    g2d.drawLine(260,340,140,270);//-"- лев рук
    g2d.drawOval(438,237,35,35);//прав запястье
    g2d.drawOval(107,235,35,35);//лев запястье
    g2d.setColor(c1);
    g2d.setStroke(Pen[3]);
    g2d.drawLine(275,240,275,400);//ствол
    g2d.drawLine(280,335,445,257);//прав рука
    g2d.drawLine(270,336,135,257);//лев рука
    g2d.fillOval(440,240,30,30);//прав рука
    g2d.fillOval(110,237,30,30);//лев рука
    g2d.fillOval(462,245,40,12);
    g2d.fillOval(450,210,12,40);
    g2d.fillOval(450,260,12,40);
    g2d.fillOval(80,245,40,12);
    g2d.fillOval(117,205,12,40);
    g2d.fillOval(117,255,12,40);
    g2d.setColor(Color.black);
    g2d.setFont(new Font("Serif", Font.BOLD, 15));
    g2d.drawString("Курсовой проект", 150,690);
    g2d.drawString("по дисциплине \"Программирование\"",150,710);
    g2d.drawString("cтудента группы ИВТ/б-22о",150,730);
    g2d.drawString("Жиленкова И.В.",150,750);
    g2d.drawString("СевГУ - 2016",150,770);
    g2d.setColor(c4);
    g2d.setStroke(Pen[1]); //выбрано перо 3
    g2d.drawArc(175,495,210,60,180,180);//пунктир на горшке
    g2d.setColor(c6);
    g2d.setStroke(Pen[2]);
    g2d.drawArc(50,20,470,420,0,180);//семена
    g2d.setStroke(Pen[0]); //выбрано перо 1
    g2d.setColor(c3);
    g2d.fillOval(220, 400, 50, 30);//цеток растёт из земли
    g2d.setColor(Color.white);
    g2d.setStroke(Pen[0]);
    g2d.fillOval(233, 143, 25, 25);
    g2d.fillOval(293, 143, 25, 25);
    g2d.setColor(Color.black);
    g2d.fillOval(237, 144, 15, 15);
    g2d.fillOval(297, 144, 15, 15);
    g2d.dispose();
  }
}