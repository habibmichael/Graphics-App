public class GraphicsApp extends Frame implements ActionListener{
   
   BufferedImage bufferedImage, bufferedImageBackup;
   Image image;
   Menu menu;
   MenuBar menubar;
   MenuItem menuItem1,menuItem2,menuItem3,menuItem4;
   Button button1, button2, button3, button4, button5;
   FileDialog dialog;

    public static void main(String[] args){
        new GraphicsApp();
    }

    //Constructor that creates the window
    public GraphicsApp(){

        setSize(400,360);
        setTitle("The Graphic App");
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(
                    WindowEvent e){
                System.exit(0);
            }
            )
        });

        //Emboss Button
        button1= new Button("Emboss");
        button1.setBounds(30,getHeight()-50,60,20);
        add(button1);
        button1.addActionListener(this);

        //Sharpen Button
        button2 = new Button("Sharpen");
        button2.setBounds(100,getHeight()-50,60,20);
        add(button2);
        button2.addActionListener(this);

        //Brighten Button
        button3= new Button("Brighten");
        button3.setBounds(170,getHeight()-50,60,20);
        add(button3);
        button3.addActionListener(this);

        //Blur Button
        button4 = new Button("Blur");
        button4.setBounds(240,getHeight()-50,60,20);
        add(button4);
        button4.addActionListener(this);

        //Reduce Button
        button5 = new Button("Reduce");
        button5.setBounds(310,getHeight()-50,60,20);
        add(button5);
        button5.addActionListener(this);

        //File menu
        menubar = new MenuBar();
        menu = new Menu("File");

        //Items for file tab
        menuItem1 = new MenuItem("Open...");
        menu.add(menuItem1);
        menuItem1.addActionListener(this);





    }
   



}