package UF3.TCPejemplo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivo {

    /**
     * Este metodo constructor sirve para crear el csv
     * @param jugadores Recibe una lista con la cual va a trabajar para escribir en el csv
     * @param file Recibe un file con el cual va a trabajar a la hora de borrar el archivo o a la g¡hora de crear/escribir dentro de el.
     */
    public void Escribir(List<Jugador> jugadores, File file){
        CSVWriter csvWriter;
        String[] score =  new String[2];
        file.delete();
        for (Jugador j: jugadores) {
            score[0] = j.getApodo();
            score[1] = String.valueOf(j.getPuntuacion());

            try {
                csvWriter = new CSVWriter(new FileWriter(file, true));
                csvWriter.writeNext(score);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ruta del archivo " + file.getPath());
    }

    public List<Jugador> Leer(File file){
        List<Jugador> scoreList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("scores.csv"));
            scanner.useDelimiter(",|\n");

            while (scanner.hasNext()) {
                String apodo = scanner.next();
                int puntuacion = scanner.nextInt();

                scoreList.add(new Jugador(apodo, puntuacion));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}