import java.io.*;
import java.awt.*;
import java.lang.Math;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class GraphicsApp extends Frame implements ActionListener{

	private static final long serialVersionUID = 1L;
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

        setSize(400,400);
        setTitle("The Graphic App");
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(
                WindowEvent e){
                    System.exit(0);
                }
            }
        );

        //Emboss Button
        button1= new Button("Emboss");
        button1.setBounds(30,getHeight()-50,60,20);
        add(button1);
        button1.addActionListener(this);
        
          //Sharpen Button
        button2 = new Button("Sharpen");
        button2.setBounds(100,getHeight()-50, 60, 20);
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
    				bufferedImage = ImageIO.read(input);
    				
    				//resize window to match image
    				   setSize(getInsets().left + getInsets().right + 
    	                        Math.max(400, bufferedImage.getWidth() + 60), 
    	                        getInsets().top + getInsets().bottom + 
    	                        Math.max(340, bufferedImage.getHeight() + 60));
    				
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
         repaint();
    	}
    	
    	//Emboss functionality
    	if(event.getSource()==button1){
    		
    		bufferedImageBackup=bufferedImage;
    		
    		int width = bufferedImage.getWidth();
    		int height = bufferedImage.getHeight();
    		int pixels[] = new  int[width*height];
    		
    		PixelGrabber pg = new PixelGrabber(bufferedImage,0,0,
    				width,height,pixels,0,width);
    		
    		try{
    			//Store pixels in array (pixels)
    			pg.grabPixels();
    		} catch(InterruptedException e){
    			
    			System.out.println(e.getMessage());
    		}
    		
    		///Fills in a two pixel border around the image with gray
    		for (int x =0;x<=1; x++){
    			for(int y = 0; y<height -1; y++){
    				pixels[x+y*width]= 0x88888888;
    			}
    		}
    		
    		for ( int x = width-2;x<=width-1;x++){
    			for(int y = 0 ; y<height-1;y++){
    				pixels[x+y*width]=0x88888888;
    			}
    		}
    		for(int x = 0 ; x<width-1;x++){
    			for ( int y = 0 ; y<= 1 ; y++){
    				pixels[x+y*width]=0x88888888;
    			}
    		}
    		
    		//loop through each row & column of pixels 
    		for (int x = 2 ;x<width-1;x++){
    			for(int y =2 ; y<height-1;y++){
    				
    				int red = ((pixels[(x + 1) + y * width + 1] & 0xFF) 
    	                    - (pixels[x + y * width] & 0xFF)) + 128;
    				
    				  int green = (((pixels[(x + 1) + y * width + 1] 
    	                        & 0xFF00) / 0x100) % 0x100 - 
    	                        ((pixels[x + y * width] 
    	                        & 0xFF00) / 0x100) % 0x100) + 128;

    	                    int blue = (((pixels[(x + 1) + y * width + 1] & 
    	                        0xFF0000) / 0x10000) % 0x100 - 
    	                        ((pixels[x + y * width] & 
    	                        0xFF0000) / 0x10000) % 0x100) + 128;
    	            
    	                    int avg = (red + green + blue) / 3;

    	                    pixels[x + y * width] = (0xff000000 | avg << 16 | 
    	                        avg << 8 | avg);
    			}
    		}
    		
    		//Get Image back to bufferedImage
    		image=createImage(new MemoryImageSource(width,height,
    				pixels,0,width));
    		
    		bufferedImage = new BufferedImage(width,height,
    				BufferedImage.TYPE_INT_BGR);
    		
    		bufferedImage.createGraphics().drawImage(image,
    				0, 0,this);
    		
    		repaint();
    		
    		
    		
    	//Sharpening an Image	
    	} if (event.getSource()==button2){
    		bufferedImageBackup=bufferedImage;
    		Kernel kernel = new Kernel(3,3,new float[]{
    				0.0f,-1.0f,0.0f,-1.0f,5.0f,-1.0f,
    				0.0f,-1.0f,0.0f
    		});
    		ConvolveOp convolveOp= 
    				new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);
    		
    		BufferedImage temp = new BufferedImage(
    				bufferedImage.getWidth(),bufferedImage.getHeight(),
    				BufferedImage.TYPE_INT_ARGB);
    		
    		convolveOp.filter(bufferedImage, temp);
    		
    		bufferedImage=temp;
    		repaint();
    		
    		//Brighten Image
    	} if(event.getSource()==button3){
    		bufferedImageBackup=bufferedImage;
    		Kernel kernel = new Kernel(1,1,new float[]{3});
    		
    		ConvolveOp convolveOp = new ConvolveOp(kernel);
    		
    		BufferedImage temp = new BufferedImage(
    				bufferedImage.getWidth(),bufferedImage.getHeight(),
    				BufferedImage.TYPE_INT_ARGB);
    		
    		convolveOp.filter(bufferedImage, temp);
    		
    		bufferedImage=temp;
    		repaint();
    		
    		
    		//Blurring an Image
    	} if(event.getSource()==button4){
    		
    		bufferedImageBackup = bufferedImage;
    		Kernel kernel = new Kernel(3,3,new float[]{
    				.25f,0,.25f,0,0,0,.25f,0,.25f
    		});
    		
    		ConvolveOp convolveOp = new ConvolveOp(kernel);
    		BufferedImage temp = new BufferedImage(
    				bufferedImage.getWidth(),bufferedImage.getHeight(),
    				BufferedImage.TYPE_INT_ARGB);
    		
    		convolveOp.filter(bufferedImage,temp);
    		bufferedImage = temp;
    		repaint();
    		
    	} //Reducing an image by a factor of 2
    	if(event.getSource()==button5){
    		image= bufferedImage.getScaledInstance(bufferedImage.getWidth()/2,
    				bufferedImage.getHeight()/2,0);
    		
    		bufferedImage = new BufferedImage(bufferedImage.getWidth()/2,
    				bufferedImage.getHeight()/2,BufferedImage.TYPE_INT_ARGB);
    		
    		bufferedImage.createGraphics().drawImage(image, 0, 0, this);
    		
    		setSize(getInsets().left+getInsets().right+Math.max(400,bufferedImage.getWidth()+60),
    				getInsets().top+getInsets().bottom+Math.max(340, bufferedImage.getHeight()+60));
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