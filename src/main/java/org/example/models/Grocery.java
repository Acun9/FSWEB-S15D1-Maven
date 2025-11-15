package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Grocery {
    public static List<String> groceryList = new ArrayList<>();

    public static void startGrocery(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Grocery App'e hoş geldiniz!");
        System.out.println("0: Çıkış | 1: Ekle | 2: Sil");

        while(true){
            String choise = scan.nextLine().trim();
            if(choise.equals("0")) {
                System.out.println("Çıkış yapılıyor...");
                break;
            }else if(choise.equals("1")){
                System.out.println("Eklenecek öğeleri girin (tek ya da virgülle):");
                String input = scan.nextLine();
                addItems(input);
                printSorted();
            }else if(choise.equals("2")){
                System.out.println("Silinecek öğeleri girin (tek ya da virgülle):");
                String input = scan.nextLine();
                removeItems(input);
                printSorted();
            }else{
                System.out.println("Geçersiz seçim. Lütfen 0/1/2 girin.");
            }
        }
        scan.close();
    }

    public static void addItems(String input) {
        for (String item : parse(input)) {
            if (!checkItemIsInList(item)) {
                groceryList.add(item);
            }
        }
        sortList();
    }
    public static void removeItems(String input) {
        for (String item : parse(input)) {
            int idx = indexOfIgnoreCase(item);
            if (idx >= 0) {
                groceryList.remove(idx);
            }
        }
        sortList();
    }
    public static boolean checkItemIsInList(String product) {
        return indexOfIgnoreCase(product) >= 0;
    }
    private static List<String> parse(String input) {
        if (input == null || input.isBlank()) return Collections.emptyList();
        String[] parts = input.split(",");
        List<String> out = new ArrayList<>();
        for (String p : parts) {
            String v = p.trim();
            if (!v.isEmpty()) out.add(v);
        }
        return out;
    }
    private static void sortList() {
        groceryList.sort(String.CASE_INSENSITIVE_ORDER);
    }
    public static void printSorted() {
        sortList();
        System.out.println("----- Sıralı Grocery Listesi -----");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println((i + 1) + ". " + groceryList.get(i));
        }
        System.out.println("--------------------------------");
        System.out.println("0: Çıkış | 1: Ekle | 2: Sil");
    }
    private static int indexOfIgnoreCase(String s) {
        for (int i = 0; i < groceryList.size(); i++) {
            if (groceryList.get(i).equalsIgnoreCase(s)) return i;
        }
        return -1;
    }
}
