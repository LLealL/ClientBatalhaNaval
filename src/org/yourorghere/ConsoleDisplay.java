/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.sql.Time;
import java.time.LocalTime;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author keyalisth
 */
public class ConsoleDisplay {
    private JTextArea display;
    
    public ConsoleDisplay(JTextArea d){
        display=d;
    }
    
    private String getTime(){
        return "["+Time.valueOf(LocalTime.now())+"] ";
    }
    
    public void displayMsg(final String s){
        SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						display.append(getTime()+s+"\n");
					}
				});
    }
}
