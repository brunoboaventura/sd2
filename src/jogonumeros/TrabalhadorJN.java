/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogonumeros;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunoifba2
 */
public class TrabalhadorJN extends Thread {

    private Socket s;
    private int n;
    private int tentativas;

    public TrabalhadorJN(Socket s) {
        this.s = s;
        //numero para adivinhar
        this.n = (int) (Math.random() * 100);
        this.tentativas = 0;
    }

    public void run() {
        try {

            PrintWriter saida = new PrintWriter(s.getOutputStream(), true);
            Scanner entrada = new Scanner(s.getInputStream());

            String msg;
            int numero;

            do {

                msg = entrada.nextLine();
                numero = Integer.parseInt(msg);

                if (numero > this.n) {
                    saida.println("Tente um número menor.");
                }
                if (numero < this.n) {
                    saida.println("Tente um número maior.");
                }

                tentativas++;

            } while (numero != this.n);

            saida.println("Você adivinhou o número "
                    + this.n
                    + " com "
                    + this.tentativas + " tentativas.");

            s.close();

        } catch (IOException ex) {
            Logger.getLogger(TrabalhadorJN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}