import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ListaCumparaturi {


    public static LinkedList<String> linkedList = new LinkedList<String>();
    // adauga Cumparaturi  la lista
    public void adaugaCumparaturi(){

        System.out.println("Adauga cumparaturile linie dupa linie. Alege -q pentru a finaliza");
        Scanner citeste = new Scanner(System.in);
        while(citeste.hasNextLine()){
         String cumparaturi = citeste.nextLine();
            if (cumparaturi.contains("-q")){
                break;

            } else{
                linkedList.add(cumparaturi);
            }
        }
    }
    // sorteaza Cumparaturile adaugate in lista
    public LinkedList<String> sortareCumparaturi(LinkedList<String> lista){
        if(!lista.isEmpty()){
            String temp= lista.get(0);
            LinkedList<String> inainte = new LinkedList<String>();
            LinkedList<String> curent = new LinkedList<String>();
            LinkedList<String> dupa = new LinkedList<>();

            for( String continut : lista){
                if(continut.compareTo(temp)<0){
                inainte.add(continut);
                }
                else if(continut.compareTo(temp)>0){
                dupa.add(continut);
                }
                else{
                    curent.add(continut);
                }

            }

            inainte = sortareCumparaturi(inainte);
            dupa = sortareCumparaturi(dupa);

            inainte.addAll(curent);
            inainte.addAll(dupa);
            return inainte;
        }
        return lista;
    }
    // Salveaza intr-un document de tip text Cumparaturile
    public void exportListaCumparaturi(){
        try {
            PrintWriter printWriter = new PrintWriter("Files\\Cumparaturi.txt");
            for(String line : linkedList){
                printWriter.println(line);
                printWriter.flush();

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu este disponibil!");
        }
    }

    public static void main(String[] args) {

       ListaCumparaturi listaObiecte = new ListaCumparaturi();
        listaObiecte.adaugaCumparaturi();
       linkedList = listaObiecte.sortareCumparaturi(linkedList);
        listaObiecte.exportListaCumparaturi();
    }
}
