import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class grafic_mobil_text3 extends PApplet {

// Learning Processing
// Daniel Shiffman
// http://www.learningprocessing.com

// Example: a graph of random numbers

float[] vals;

public void setup() {
  background(200);
  size(400,400);
  smooth();
  // An array of values
  vals = new float[width];
  for (int i = 0; i < vals.length;  i++) {
    vals[i] = mouseY;
    //
  }
}


public void draw() {

  background(255);
  // Draw lines connecting all points
  for (int i = 0; i < vals.length-1; i++) {
    stroke(0);
    strokeWeight(1);
    line(i,vals[i],i+1,vals[i+1]);
  }
  
  // Slide everything down in the array
  for (int i = 0; i < vals.length-1; i++) {
    vals[i] = vals[i+1]; 
  }
  // Add a new random value
  vals[vals.length-1] = mouseY;
  textSize(32);
  fill(0);
  text(mouseY, width-70, height-20);
  fill(0);
  text("Y: ", width-100, height-20);
  

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "grafic_mobil_text3" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
