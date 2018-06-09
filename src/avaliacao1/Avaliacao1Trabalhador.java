/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Avaliacao1Trabalhador extends Thread {

    private Socket s;
    private int n;
    private int tentativas;

    private static String gabarito = "dcbadecabd";
    private int acertos;

    public Avaliacao1Trabalhador(Socket s) {
        this.s = s;
        this.acertos = 0;
    }

    public void run() {
        try {

            PrintWriter saida = new PrintWriter(s.getOutputStream(), true);
            Scanner entrada = new Scanner(s.getInputStream());

            String respostasCliente;

            respostasCliente = entrada.nextLine();

            for (int i = 0; i < respostasCliente.length(); i++) {
                char cCliente = respostasCliente.charAt(i);
                char cGabarito = gabarito.charAt(i);
                
                if (cCliente == cGabarito)
                    acertos++;
            }

            saida.println("Você acertou "
                    + this.acertos
                    + " questões .");

            s.close();

        } catch (IOException ex) {
            Logger.getLogger(Avaliacao1Trabalhador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
