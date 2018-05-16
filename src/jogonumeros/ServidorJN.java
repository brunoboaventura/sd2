/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogonumeros;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Aluno IFBA
 */
public class ServidorJN {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(9000);
        while (true) {
            Socket s = servidor.accept();
            
            TrabalhadorJN t = new TrabalhadorJN (s);
            t.start();
        }
    }
}