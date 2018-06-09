/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao2;

import avaliacao1.*;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Avaliacao2Trabalhador extends Thread {

    private Socket s;
    private int n;
    private int tentativas;

    private static String gabarito = "dcbadecabd";
    private int acertos;

    public Avaliacao2Trabalhador(Socket s) {
        this.s = s;
        this.acertos = 0;
    }

    public void run() {
        try {

            PrintWriter saida = new PrintWriter(s.getOutputStream(), true);
            Scanner entrada = new Scanner(s.getInputStream());

            String nomeAluno;
            String respostasCliente;
            
            File arquivoNotas = new File ("/home/bruno/notas.txt");


            Scanner lerArquivo;

            respostasCliente = entrada.nextLine();


            
            if (respostasCliente.equalsIgnoreCase("--listar-notas"))
            {
                lerArquivo = new Scanner(arquivoNotas);
                while (lerArquivo.hasNextLine()) {
                    saida.println (lerArquivo.nextLine());
                }
                lerArquivo.close();
                s.close();
                return;
            }
            

            for (int i = 0; i < gabarito.length(); i++) {
                char cCliente = respostasCliente.charAt(i);
                char cGabarito = gabarito.charAt(i);

                if (cCliente == cGabarito) {
                    acertos++;
                }
            }

            nomeAluno = respostasCliente.substring(gabarito.length());

            saida.println("Você acertou "
                    + this.acertos
                    + " questões .");

            s.close();

            System.out.println(nomeAluno);
            System.out.println(respostasCliente);

            List<String> listaNotas = new ArrayList<String>();
            
            lerArquivo = new Scanner(arquivoNotas);
            while (lerArquivo.hasNextLine()) {
                String linha = lerArquivo.nextLine();
                listaNotas.add(linha);
                System.out.println (linha);
            }
            
            lerArquivo.close();
            
            listaNotas.add(acertos + " " + nomeAluno);
            
            Collections.sort(listaNotas);
                       
            PrintWriter arquivo = new PrintWriter("/home/bruno/notas.txt");
            
            for (int i = listaNotas.size() - 1; i >= 0; i--){
                arquivo.println(listaNotas.get(i));
            }
                        
            arquivo.flush();
            arquivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Avaliacao2Trabalhador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
