import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class MainFrame implements ActionListener{
    public String directoryName ="";//имя директории, в которой расположен 
                                    // текущий файл базы данных
    public String fileName="";//абсолютное имя текущего файла базы данных
    File curFile;//текущий файловый объект (связан с текущим файлом базы данных)
    JFrame frame;//главный фрейм
    JFrame calcframe;
    JPanel pMain;//главная панель фрейма
    public JLabel MSG;//сообщение в нижней части окна
    JTable jExamTab;
    JTable jCalcTab;
    ArrayList<Exam> exam;
    ArrayList<Budget> budget;
    Table tModel = new Table(exam);
    CalcTable calcT = new CalcTable(budget);
    MenuIS s = new MenuIS();
    JButton btnSortId = new JButton("Сортировка по паспорту");
    JButton btnSortBall = new JButton("Сортировка по баллу");
    JButton btnDelById = new JButton("Удаление по id");
    JButton calcbtn = new JButton("Расчет");
    public   MainFrame(){
      int WinSizeG=700;//начальный размер окна по горизонтали
      int WinSizeV=550;//начальный размер окна по вертикали
      frame = new JFrame("Главный фрейм");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.gray);//gray);
      Container myC = frame.getContentPane();
      myC.setLayout(new BorderLayout(5,5));
      frame.setJMenuBar(s.mb1);//добавление меню в окно

      JPanel EC = new JPanel();//компановщик BorderLaypout для того, чтобы FlowLayout был в центре
      myC.add(EC,BorderLayout.EAST);
      EC.setLayout(new BorderLayout());

      JPanel ECC = new JPanel();//компановщик BoxLayout для кнопкок
      EC.add(ECC,BorderLayout.CENTER);
      ECC.setLayout(new BoxLayout(ECC, BoxLayout.Y_AXIS));
      
      //Организация прослушивания пунктов меню
      s.newFile.addActionListener(this);
      s.openFile.addActionListener(this);
      s.saveFile.addActionListener(this);
      s.saveAsFile.addActionListener(this);
      s.closeFile.addActionListener(this);
      s.startEdit.addActionListener(this);
      s.stopEdit.addActionListener(this);
      s.help1.addActionListener(this);
      
      s.stopEdit.setEnabled(false);
      
      exam = new ArrayList<Exam>();
      tModel = new Table(exam);
      jExamTab = new JTable(tModel);
      JScrollPane jscrlp = new JScrollPane(jExamTab);
      jExamTab.setPreferredScrollableViewportSize(new Dimension(300, 200));
      myC.add(jscrlp);
      myC.add(new JLabel("Данные о студентах и специальностях",
                            JLabel.CENTER),BorderLayout.NORTH);
                         
        btnDelById.addActionListener(event ->{ 
           String l =JOptionPane.showInputDialog("Введите номер паспорта"); 
           delExam(l);
           MSG.setText("Запись с номером паспорта = "+l+" Успешно удалена из таблицы");         
        });                
                                   
       calcbtn.setToolTipText("Для расчета необходимо выполнить сортировку по баллу");
       calcbtn.setEnabled(false);
       calcbtn.addActionListener(event ->{
          String a, b, c;
          calcframe = new JFrame("Расчет бюджетных мест");
          calcframe.setDefaultCloseOperation(calcframe.DISPOSE_ON_CLOSE);
          calcframe.setLocationRelativeTo(null);//Эта стриочка должна выводить фрейм посреди экрана, если не раотает - удалить
          Container CC = calcframe.getContentPane();
          CC.setLayout(new BorderLayout(0,20));
          JPanel CN = new JPanel();// компановщик FlowLayout для кнопкок
          CC.add(CN,BorderLayout.NORTH);
          CN.setLayout(new BoxLayout(CN, BoxLayout.Y_AXIS));
          budget = new ArrayList<Budget>();                             
          for(int i=exam.size()-25;i<exam.size();i++ ){  
            a=exam.get(i).getId();
            b=exam.get(i).getCipher();
            c=exam.get(i).getBall();
            budget.add(new Budget(a, b, c));
          }
          calcT = new CalcTable(budget);
          jCalcTab = new JTable(calcT);
          JScrollPane jscrlpane = new JScrollPane(jCalcTab);
          jCalcTab.setPreferredScrollableViewportSize(new Dimension(300, 200));
          MSG = new JLabel("Данные о студентах, поступивших на бюджет:");
          CN.add(MSG);
          MSG = new JLabel("Бюджетных мест - 25; абитуриентов - "+exam.size()+";");
          CN.add(MSG);
          if (exam.size()>budget.size()){
            MSG = new JLabel("Бюджетный план не выполнен. Бюджетных мест всем не хватило.");}
          else{MSG = new JLabel("Бюджетный план выполнен. Бюджетных мест хватило всем.");}
          CN.add(MSG);
          MSG = new JLabel("Проходной балл - "+budget.get(0).getBall()+".");
          CN.add(MSG);
          CC.add(jscrlpane,BorderLayout.CENTER);
          calcframe.setSize(WinSizeG,WinSizeV);
          calcframe.setLocation(550,155);
          calcframe.setSize(450,570);
          calcframe.setVisible(true);
       });

       btnSortId.addActionListener(event ->{ 
          for (int n = 1;n<exam.size();n++){
             sort(2);
          }
          if (exam.size()!=0){
             MSG.setText("Записи отсортированы по паспорту");
             tModel.fireTableDataChanged();}
          else{ MSG.setText("Оп, ошибочка вышла");
             JOptionPane.showMessageDialog(frame,"Таблица пуста");
             MSG.setText("Сортировка отменена");}
       });
        
       btnSortBall.addActionListener(event ->{ 
          for (int n = 1;n<exam.size();n++){
             sort(1);
          }
          if (exam.size()!=0){
             calcbtn.setEnabled(true);
             MSG.setText("Записи отсортированы по баллу");
             tModel.fireTableDataChanged();}
          else{ MSG.setText("Оп, ошибочка вышла");
             JOptionPane.showMessageDialog(frame,"Таблица пуста");
             MSG.setText("Сортировка отменена");}
       });
        
       JButton btnAdd = new JButton("Добавить запись"); 
       btnAdd.addActionListener(event ->{
          MSG.setText("Добавление записи");
          boolean flag = false;
          int result = JOptionPane.showConfirmDialog(null,
                         "Вы хотите дабавить новый элемент в таблицу?",
                         "Добавление нового элемента", JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE); 
          switch(result){ 
            case JOptionPane.YES_OPTION:{
              String s4 =JOptionPane.showInputDialog("Введите номер паспорта"); 
              String s5 =JOptionPane.showInputDialog("Введите шифр специальности");
              String s6 =JOptionPane.showInputDialog("Введите средний балл"); 
              for(int i=0;i<exam.size();i++ ){   
                if(exam.get(i).getId().equals(s4)){
                   flag=true;MSG.setText("Запись с указанным Id уже существует"); break;}
              } 
              if(flag==false){
                 exam.add(new Exam(s4, s5, s6));
                 MSG.setText("Запись добавлена");}
              tModel.fireTableDataChanged();
              Enabled();
              if (exam.size()<2){ btnSortBall.setEnabled(false);
                 btnSortId.setEnabled(false);}
            }
            break; 
               case JOptionPane.NO_OPTION: MSG.setText("Вы отказались добавлять элемент");
            break; 
               case JOptionPane.CANCEL_OPTION: MSG.setText("Вы нажали кнопку отмена"); 
            break; 
               case JOptionPane.CLOSED_OPTION: MSG.setText("Вы закрыли окно запроса"); 
            break; 
          }
       });
        
      MSG = new JLabel("Добавление и удаление записей:  ");
      ECC.add(MSG);   
      ECC.add(btnAdd);
      ECC.add(btnDelById);
      MSG = new JLabel("Сортировки: ");
      ECC.add(MSG);
      ECC.add(btnSortId);
      ECC.add(btnSortBall);
      MSG = new JLabel("Расчет: ");
      ECC.add(MSG);
      ECC.add(calcbtn);
  
      Enabled();
      // Метка для сообщений системы
      MSG = new JLabel(
      "СевГУ - 2016  Курсовой проект по дисциплине \"Программирование\". Жиленкова И.В.");
      MSG.setFont(new Font("Serif", Font.BOLD,14));
      myC.add(MSG,BorderLayout.SOUTH);
      // устанавливаем размеры и локализацию фрейма
      frame.setSize(WinSizeG,WinSizeV);
      frame.setLocation(500,120);
      frame.setVisible(true);
  }
  //**********методы для пункта меню "Файл"********************************************
  public void NewFile(){
     int resul = JOptionPane.showConfirmDialog(null, 
                  "Вы хотите сохранить текущую таблицу?", "Сохранить?",
                   JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); 
     switch(resul){ 
        case JOptionPane.YES_OPTION:
            SaveDialog();
            exam.clear();
            tModel.fireTableDataChanged();
            MSG.setText("Новая таблица успешно создана");
            Enabled();
            calcbtn.setEnabled(false);  break;
        case JOptionPane.NO_OPTION: 
            MSG.setText("Вы отказались сохранять текущую  таблицу");
            exam.clear();
            tModel.fireTableDataChanged();
            MSG.setText("Новая таблица успешно создана");
            Enabled();
            calcbtn.setEnabled(false);  break; 
        case JOptionPane.CANCEL_OPTION: MSG.setText("Вы нажали кнопку отмена"); 
            break; 
        case JOptionPane.CLOSED_OPTION: MSG.setText("Вы закрыли окно запроса"); 
            break;   
     }
  }

  public void setFileFilter(JFileChooser fch){//установка фильтра для диалога выбора файла
     TextFilter text_filter=new TextFilter();
     File f_file=new File("f1.txt");
     text_filter.accept(f_file);
     f_file=new File("f1.bd");
     text_filter.accept(f_file);
     fch.setFileFilter(text_filter);
  }

  public void OpenFile() {
     MSG.setText("Открытие файла");
     int rez; int n;
     JFileChooser fch=new JFileChooser(directoryName);
     fch.setDialogTitle("Открытие файла");
     setFileFilter(fch);  
     rez=fch.showDialog(frame,"Open");
     if (rez==fch.APPROVE_OPTION){
        curFile=fch.getSelectedFile();
        fileName=curFile.getAbsolutePath();   
        LinkedHashSet<Exam> readed=new LinkedHashSet<Exam>();
        try {
          if((readed=IO.inp(fileName))!=null){
             Iterator it = readed.iterator();
             while(it.hasNext()){
                Exam tmp = new Exam();
                tmp = (Exam) it.next();
                exam.add(new Exam(tmp.getId(),tmp.getCipher(),tmp.getBall()));
                tModel.fireTableDataChanged();
             }
          }
        }catch (IOException e) {MSG.setText("Оп, ошибочка вышла");}
        n=fileName.lastIndexOf('\\');
        directoryName=fileName.substring(0,n+1);        
        MSG.setText(" Ввод базы данных успешно выполнен");
        Enabled();
     }else{MSG.setText(
        "СевГУ - 2016   Курсовой проект по дисциплине \"Программирование\". Жиленкова И.В.");
        return;}
  }

  private void SaveDialog(){//открывает окно диалога для сохранения файла
     int rez; int n,p;
     String Name;
     JFileChooser fch=new JFileChooser(directoryName);
     fch.setDialogTitle("Сохранение файла");
     setFileFilter(fch); 
     rez=fch.showDialog((Component)frame,"Save");
     if (rez==fch.APPROVE_OPTION){
        curFile=fch.getSelectedFile();
        fileName=curFile.getAbsolutePath();
        n=fileName.lastIndexOf('\\');
        directoryName=fileName.substring(0,n+1);
     }
     else return;
     p=fileName.lastIndexOf('\\');
     Name=fileName.substring(p+1);
     try{IO.saveas(fileName, exam,1);tModel.fireTableDataChanged();}
     catch(IOException e){MSG.setText("Оп, ошибочка вышла");;}
     JOptionPane.showMessageDialog(null, "Сохранение ...\\"+Name+" успешно завершено.",
                                  "Сохранение", JOptionPane.INFORMATION_MESSAGE);
     MSG.setText("Таблица успешно сохранена в файл под названием: "+Name);
  } 
    
  public void SaveFile(){   
     String way="Новая таблица.txt";
     try{IO.saveas(way, exam,1);tModel.fireTableDataChanged();}
     catch(IOException e){MSG.setText("Оп, ошибочка вышла");;}
     JOptionPane.showMessageDialog(null, "Сохранение ...\\"+way+" успешно завершено.", "Сохранение", 
                                         JOptionPane.INFORMATION_MESSAGE);
     MSG.setText("Таблица успешно сохранена в файл под названием: "+way);
  }
 
  public void CloseWindow(){ frame.dispose();}
  //********************методы для пункта меню "Редактирование"*************************************************
  public void StartEdit(){
     if (tModel.getIsCellEditable()==false)MSG.setText("Включен режим редактирования");
     tModel.setIsCellEditable(true);
     MSG.setText("   Редактирование базы данных");
     s.stopEdit.setEnabled(true);
     s.startEdit.setEnabled(false);
  }

  public void StopEdit(){
     if (tModel.getIsCellEditable()== true)MSG.setText("Редактирование завершено");
     tModel.setIsCellEditable(false);
     MSG.setText("   Просмотр базы данных");
     s.stopEdit.setEnabled(false);
     s.startEdit.setEnabled(true);
  }
    
  private static void createAndShowGUI(){
     JFrame frame=new JFrame("О программе");
     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     Flower f=new Flower();
     frame.getContentPane().setBackground(Color.gray);
     frame.getContentPane().add(f);
     frame.setSize(650,830);
     frame.setLocation(500,20);
     frame.setVisible(true);
  }
    
  public void actionPerformed (ActionEvent e){
     String s; int l; char ch; boolean f;
     if("Новый".equals(e.getActionCommand()))NewFile(); 
     else if("Открыть".equals(e.getActionCommand()))OpenFile();
     else if ("Сохранить".equals(e.getActionCommand()))SaveFile(); 
     else  if ("Сохранить как".equals(e.getActionCommand()))SaveDialog();
     else  if ("Закрыть".equals(e.getActionCommand()))CloseWindow();
     else  if ("Начать редактирование".equals(e.getActionCommand()))StartEdit();
     else  if ("Закончить редактирование".equals(e.getActionCommand())) StopEdit();
     else  if ("О программе".equals(e.getActionCommand()))createAndShowGUI();
  }

  public void delExam(String id){
     for(int i=0;i<exam.size();i++ ){   
      if(exam.get(i).getId().equals(id)){exam.remove(i);}}
     tModel.fireTableDataChanged();
  }
  
  private void sort(int m){
     Exam temp = null;
     try{for(int j = 0 ; j < exam.size(); j++){
            for(int i = 0 ; i < exam.size() - j; i++){
               if( m==1){
                  if(exam.get(i).getBall().compareTo(exam.get(i+1).getBall()) > 0){
                   temp = exam.get(i);
                   exam.set(i,exam.get(i+1)) ;
                   exam.set((i+1),temp);}
               }
               else{if(exam.get(i).getId().compareTo(exam.get(i+1).getId()) > 0){
                  temp = exam.get(i);
                  exam.set(i,exam.get(i+1)) ;
                  exam.set((i+1),temp);}}}}
     }catch(Exception ex){MSG.setText("Оп, ошибочка вышла");}}
  
  public void Enabled(){
     if (exam.size()>0){
        s.newFile.setEnabled(true);
        s.saveFile.setEnabled(true);
        s.saveAsFile.setEnabled(true);
        s.startEdit.setEnabled(true);
        btnDelById.setEnabled(true);
        btnSortBall.setEnabled(true);
        btnSortId.setEnabled(true);
     }else{
        s.newFile.setEnabled(false);
        s.saveFile.setEnabled(false);
        s.saveAsFile.setEnabled(false);
        s.startEdit.setEnabled(false);
        btnDelById.setEnabled(false);
        btnSortBall.setEnabled(false);
        btnSortId.setEnabled(false);
     }  
  }
}