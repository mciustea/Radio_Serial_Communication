import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MeniuClasses1_11 extends PApplet {



MenuBox Graph;
MenuBox SerialPort;
MenuBox Exit;
MenuBox SerialInput;
Serial myPort;

int STAGE = 1 ;
int PORT;
int valA; 

float XCoord;
float YCoordG;
float YCoordS;
float YCoordE;
float YCoordSI;
float XBox;
float YSerialInput;
float indent; 
float Ypozitie;
float YposSerial;
float rX;
float rY;
float[] vals;

PFont f;
String integerSafe = "0";
String intermediar;
String typing = "";//Serial Port Selection
String saved = "";
//String PORTS[] = "";

public void setup(){
  size(displayWidth/2, displayHeight/2);
  
  f = createFont("Arial",16,true);
  rX = displayWidth/1920;
  rY = displayHeight/1080;
  float XBox = displayWidth/4;
  float YGraph = displayHeight*1/10;
  float YSerial = displayHeight*2/10;
  float YExit = displayHeight*3/10;
  float YSerialInput = displayHeight*3/10;
 
  XCoord = XBox;
  YCoordG = YGraph;
  YCoordS = YSerial;
  YCoordE = YExit;
  YCoordSI = YSerialInput;
  
  Graph = new MenuBox(color(255, 0, 0), XBox, YGraph);
  SerialPort = new MenuBox(color(255, 0, 0), XBox, YSerial);
  Exit = new MenuBox(color(255, 0, 0), XBox, YExit); 
  SerialInput = new MenuBox(color(255, 0, 0), XBox, YSerialInput);
  
}
//=================================================================
//=================================================================
public void draw(){
 Selectii();
  if(STAGE == 1){
StageI();
 }
 if(STAGE == 2){
   GraphFct();
 }
 if(STAGE == 3){
   ComPorts();
 }
 if(STAGE == 4){
  Exit();
 } 

    
}
//=================================================================
//=================================================================
class MenuBox {
  int c;
  float xpos;
  float ypos;
  
  MenuBox(int tempC, float tempXpos,float tempYpos){
    c = tempC;
    xpos = tempXpos;
    ypos = tempYpos;
  }
  
  public void display(){
    stroke(0);
    fill(c);
    if(STAGE != 3){
    if(mouseX >= xpos-100 && mouseX <= xpos+100){
      if(mouseY >= ypos-25 && mouseY <= ypos+25){
      fill(c-200);
      }
    }
    }
    rectMode(CENTER);
    rect(xpos, ypos, 200, 50, 10);
    
      
  }
  public void textG(){
    textAlign(CENTER);
    textSize(32);
    fill(0);
    text("GRAPH",xpos, ypos+10);
  }
  public void textS(){
    textAlign(CENTER);
    textSize(32);
    fill(0);
    text("COM PORTS",xpos, ypos+10);
  }
  public void textE(){
    textAlign(CENTER);
    textSize(32);
    fill(0);
    text("EXIT",xpos, ypos+10);
  }
  public void textPI(){
    textAlign(CENTER);
    textSize(20*rX);
    fill(0);
    text("SELECTED COM PORT:",xpos-(rX*220), ypos+10*rY);
  }
  
}

public void StageI(){
  
background(0, 0, 255);
  Graph.display();
  Graph.textG();
  SerialPort.display();
  SerialPort.textS();
  Exit.display();
  Exit.textE();

}

public void GraphFct(){
  background(0, 255, 0);
  
}
public void ComPorts(){
  background(255, 255, 0);
  TableFct();
  SerialInput.display();
  SerialInput.textPI();
  
   indent = displayWidth/4;
   Ypozitie = displayHeight*2/10;
   YposSerial = displayHeight*3/10 + 5;
  
  textFont(f);
  textAlign(CENTER);
  textSize(18*rX);
  //fill(0);
  
  text("Enter Serial PORT NUMBER [0-99999] from Port Table \n Hit Enter to Save the Selection. \n Hit 'M' Key to Return to Menu ", indent, Ypozitie);
  text("Desired PORT NUMBER:_____________",indent -100*rX, Ypozitie + 75*rY); 
  text(typing,indent - 30*rX, Ypozitie + 75*rY);
  //text(saved,indent,YposSerial);
  text(PORT,indent,YposSerial);
  
  //String portName = Serial.list();
  String[] PORTS = Serial.list();
  //String[] trimmed = trim(PORTS);
  
  String txt = join(PORTS, "\n" );
  int index = 0;
  int count = 0;
 
  while(txt.indexOf("COM", index) >= 0)
  {
    String Indexul = "[" + index + "]";
    text(Indexul, indent + (rX*300), Ypozitie+count*25);
    count++;
   index=txt.indexOf("COM", index) + 1;
  } 
  text(txt, indent+400*rX, Ypozitie);
  
 
  
  //myPort = new Serial(this, portName, 9600);
  //myPort.bufferUntil(lf);

  
}
  

public void Exit(){
  background(0, 0, 255);
  exit();
}

public void Selectii(){
  if(STAGE == 1){
  if(mouseX >= XCoord-100 && mouseX <= XCoord+100){
      if(mouseY >= YCoordG-25 && mouseY <= YCoordG+25){
      if(mousePressed){
        STAGE = 2;
      }
      }
}

   if(mouseX >= XCoord-100 && mouseX <= XCoord+100){
      if(mouseY >= YCoordS-25 && mouseY <= YCoordS+25){
      if(mousePressed){
        STAGE = 3;
      }
      }
}

if(mouseX >= XCoord-100 && mouseX <= XCoord+100){
      if(mouseY >= YCoordE-25 && mouseY <= YCoordE+25){
      if(mousePressed){
        STAGE = 4;
      }
      }
    }
  }
if(keyPressed  && key == 'm' || key == 'M')
{
  STAGE = 1;
}

}





public void keyPressed() {
  if(STAGE == 3){
  // If the return key is pressed, save the String and clear it
  
  if (key == '\n' )
  {
    saved = typing;
        //PS = Integer.parseInt(saved); 
        //saved = "";
        intermediar = integerSafe + saved;
        if(intermediar.length() >= 5)
        { 
          intermediar = "0";
        }
        
        PORT = Integer.parseInt(intermediar);
        
    // A String can be cleared by setting it equal to ""
    typing = ""; 
  }
 
 if (keyCode == BACKSPACE && typing.length() > 0) 
{
  typing = typing.substring(0, typing.length() - 1);
}

else
if (key != CODED && key >= '0' && key <= '9' ) typing += key;
}
}

public void TableFct()
{
  
  
  fill(255, 255, 0);
  
  rect(indent+rX*350, Ypozitie+rY*200, 200*rX, 500*rY);
  rect(indent+rX*350, Ypozitie+rY*200, 0, 500*rY);
  rect(indent+rX*350, Ypozitie-15*rY, 200*rX, 0);
  
  fill(0);
  textMode(CENTER);
  textSize(16*rY);
  text("Port Number", indent+rX*300, Ypozitie-20*rY);
  text("Port Name", indent+400*rX, Ypozitie-20*rY);
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MeniuClasses1_11" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
