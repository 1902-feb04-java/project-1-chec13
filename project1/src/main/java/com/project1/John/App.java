package com.project1.John;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.print("username: ");
        String s = scr.nextLine();

        System.out.print("\nPassword: ");
        s = scr.next();
        System.out.print("\n");
        //scr.close();
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        //scr.close();

        System.out.print("\nusername: ");
        try {
            s = br.readLine();
            System.out.print("\nPassword: ");
            s = br.readLine();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scr.close();


    }
}
