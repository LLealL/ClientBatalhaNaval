package org.yourorghere;

import game.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
private Jogador player;
    private boolean battle=false;
    
    public GLRenderer(Jogador p,boolean battleActivated){
        player=p;
        battle=battleActivated;
    }
    

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.7f, 0.9f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0, 400, 400, 0);
        gl.glMatrixMode(GL.GL_MODELVIEW);

    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
       

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        if(!battle){
            drawEffects(gl);
        }else{
            drawHiddenMap(gl);
        }
        // Move the "drawing cursor" around
       drawGrid(gl);
       
       
       
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    private void drawHiddenMap(GL gl){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(player.getTab(i, j)=='*'){
                    drawMiss(gl,i,j);
                }else if(player.getTab(i,j)=='X'){
                    drawHit(gl,i,j);
                }
            }
        }
    }
    
    private void drawEffects(GL gl){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(player.getTab(i,j)=='B'){
                    drawShip(gl,i,j);
                }else if(player.getTab(i,j)=='*'){
                    drawMiss(gl,i,j);
                }else if(player.getTab(i, j)=='X'){
                    drawHit(gl,i,j);
                }
            }
        }
    }
    
    private void drawShip(GL gl,int i,int j){
        gl.glColor3f(0.7f, 0.5f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(i*40, j*40);
        gl.glVertex2i((i+1)*40, j*40);
        gl.glVertex2i((i+1)*40, (j+1)*40);
        gl.glVertex2i(i*40, (j+1)*40);
        gl.glEnd();
    }
    
    private void drawMiss(GL gl, int i , int j){
        gl.glLineWidth(5);
        gl.glColor3f(1.0f, 0f, 0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(i*40, j*40);
        gl.glVertex2i((i+1)*40, (j+1)*40);
        gl.glVertex2i(i*40, (j+1)*40);
        gl.glVertex2i((i+1)*40, j*40);
        gl.glEnd();        
    }
    
    private void drawHit(GL gl , int i, int j){
            gl.glLineWidth(5);
            gl.glColor3f(0.0f, 1.0f, 0f);
         
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2i((i*40), j*40 +20);
            gl.glVertex2i((i*40)+20, (j+1)*40);
            gl.glVertex2i((i*40)+20, (j+1)*40);
            gl.glVertex2i((i+1)*40, j*40);
            gl.glEnd();
    }
    
    
    private void drawGrid(GL gl){
        gl.glTranslatef(0f,0f,0f);
        gl.glLineWidth(2);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        for(int i=0;i<400;i=i+40){
            gl.glVertex2i(i, 0);
            gl.glVertex2i(i, 400);
            gl.glVertex2i(0, i);
            gl.glVertex2i(400, i);
        }
        gl.glEnd();
    }
 
}