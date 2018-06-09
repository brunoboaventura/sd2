/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import avaliacao1.Avaliacao1Trabalhador;

/**
 *
 * @author bruno
 */
public class Avaliacao1Servidor{
      public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(8000);
        while (true) {
            Socket s = servidor.accept();
            
            Avaliacao1Trabalhador t = new Avaliacao1Trabalhador (s);
            t.start();
        }
    }
}
