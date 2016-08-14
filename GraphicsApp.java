
import java.awt.*;

import javax.imageio.ImageIO;

import com.sun.glass.ui.Menu;
import com.sun.glass.ui.MenuBar;

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

        ///Items for file tab
        menuItem1 = new MenuItem("Open...");
        menu.add(menuItem1);
        menuItem1.addActionListener(this);
        
        menuItem2 = new MenuItem("Save As...");
        menu.add(menuItem2);
        menuItem2.addActionListener(this);
        
        menuItem3 = new MenuItem("Undo");
        menu.add(menuItem3);
        menuItem3.addActionListener(this);
        
        menuItem4= new MenuItem("Exit");
        menu.add(menuItem4);
        menuItem4.addActionListener(this);
        
        //MenuBar containing file menu
        menubar.add(menu);
        setMenuBar(menubar);;
        
        //Dialog for when a user wants to save or open
        dialog = new FileDialog(this,"File Dialog");
        
 
    }
    public void actionPerformed(ActionEvent event){
    	
    	
    	if(event.getSource()==menuItem1){
    		
    		dialog.setMode(FileDialog.LOAD);
    		dialog.setVisible(true);
    		
    		//Try to open file
    		try{
    			if(!dialog.getFile().equals("")){
    				
    				File input = new File(dialog.getDirectory()+dialog.getFile());
    				//ImageIO allows you to read and write in various formats
    				bufferedImage = ImageIO.getImageReader(input);
    				
    				//resize window to match image
    				setSize(getInsets().left + getInsets().right+
    						Math.max(400,bufferedImage.getWidth()+60),
    						getInsets().top+getInsets().bottom+
    						Math.max(340,bufferedImage.getHeight()+60));
    				
    				//Move buttons to fit image 
    				button1.setBounds(30,getHeight()-30,60,20);
    				button2.setBounds(100,getHeight()-30,60,20);
    				button3.setBounds(170,getHeight()-30,60,20);
    				button4.setBounds(240,getHeight()-30,60,20);
    				button5.setBounds(310,getHeight()-30,60,20);
    				
    				
    				
    			}
    		}catch(Exception e){
    				System.out.println(e.getMessage());
    			
    		}
    			repaint();
    		
    	  //Saving the file
    	} if (event.getSource()==menuItem2){
    		
    		dialog.setMode(FileDialog.SAVE);
    		dialog.setVisible(true);
    		
    		try{
    			
    			if(!dialog.getFile().equals("")){
    				
    				String outfile=dialog.getFile();
    				File outputFile = new File(dialog.getDirectory()+outfile);
    				
    				//wrtie image to disk
    				ImageIO.write(bufferedImage,outfile.substring(outfile.length()-3,
    						outfile.length()),outputFile);
    			}
    		}catch(Exception e ){
    			System.out.println(e.getMessage());
    		}
    	}
    }
    
    public void paint(Graphics g){
    	
    	if(bufferedImage!=null){
    		//redraws window with image centered
    		g.drawImage(bufferedImage,getSize().width/2-bufferedImage.getWidth()/2,
    				getInsets().top+20,this);
    	}
    }

}