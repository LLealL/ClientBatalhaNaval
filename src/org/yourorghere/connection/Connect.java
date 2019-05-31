/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class Connect {
    static Socket servidor;
    
    static ObjectInputStream istream;
    static ObjectOutputStream ostream;
    static DataInputStream dis;
    static DataOutputStream dos;
    
    public static void connect(String ip, int porta) throws UnknownHostException, IOException{
        servidor = new Socket (InetAddress.getByName(ip),porta);
        ostream = new ObjectOutputStream(servidor.getOutputStream());
        istream = new ObjectInputStream(servidor.getInputStream());
        
        dos = new DataOutputStream(servidor.getOutputStream());
        dis = new DataInputStream(servidor.getInputStream());
    }
    
    public static ObjectInputStream getInputStream(){
        return istream;
    }
    
    public static ObjectOutputStream getOutputStream(){
        return ostream;
    }
    
        public static DataInputStream getDataInputStream(){
        return dis;
    }
    
    public static DataOutputStream getDataOutputStream(){
        return dos;
    }
    
    public static void closeConnection(){
        try {
            istream.close();
            ostream.close();
            dos.close();
            dis.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
