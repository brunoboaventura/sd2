/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogonumeros;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Aluno IFBA
 */
public class ClienteJN {

    public static void main(String[] args) throws UnknownHostException, IOException {
        InetAddress endereco = InetAddress.getLocalHost();
        Socket s = new Socket(endereco, 9000);

        Scanner teclado = new Scanner(System.in);
        Scanner entrada = new Scanner(s.getInputStream());
        PrintWriter saida = new PrintWriter(s.getOutputStream(), true);

        while (s.isConnected()) {
            String mensagem = teclado.next();
            saida.println(mensagem);
            String retorno = entrada.nextLine();
            System.out.println(retorno);
            if (retorno.startsWith("Você adivinhou"))
                break;
        }
        s.close();
    }
}