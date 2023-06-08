package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedInputStream;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Personas p1 = new Personas("Agustin", "Carnessali", 22, "Colinas", "12345678", "Estudiante");
        Personas p2 = new Personas("Juan", "Perez", 22, "Puerto", "12345679", "Estudiante");
        Personas p3 = new Personas("Leo", "Chiessa", 30, "Chauvin", "12345680", "Profe");
        Personas p4 = new Personas("Mateo", "Rodriguez", 23, "San carlos", "12345681", "Estudiante");
        Personas p5 = new Personas("Guido", "Carnessali", 16, "Colinas", "12345682", "Streamer");

        Integer cantidadReactivos = 1;
        Scanner scanner = new Scanner(System.in);
        Map<String, Personas> mapa = new LinkedHashMap<>();
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapa.put(p1.getDni(), p1);
            mapa.put(p2.getDni(), p2);
            mapa.put(p3.getDni(), p3);
            mapa.put(p4.getDni(), p4);
            mapa.put(p5.getDni(), p5);
            Integer tamanio = mapa.size();
            System.out.println(tamanio);
            if (cantidadReactivos < tamanio) {
                throw new ReactivoException();

            }
        } catch (ReactivoException e) {
            while (cantidadReactivos < mapa.size()) {
                System.out.println("cuenta con mas reactivos? indique cuantos mas");
                cantidadReactivos = scanner.nextInt();
            }
        }
        //ASIGNACION DE NUMERO DE KIT
        Map<Long, Personas> mapaconkit = new LinkedHashMap<>();
        Long num, num2, num3, num4, num5;
        Integer i = 0;

        mapaconkit.put(num = random.nextLong(1000, 5000), p1);
        mapaconkit.put(num2 = random.nextLong(1000, 5000), p2);
        mapaconkit.put(num3 = random.nextLong(1000, 5000), p3);
        mapaconkit.put(num4 = random.nextLong(1000, 5000), p4);
        mapaconkit.put(num5 = random.nextLong(1000, 5000), p5);

        Map<Long, Registro> mapaTesteo = new HashMap<>();
        List<Registro> registros = new ArrayList<>();
        for (Personas p : mapaconkit.values()) {
            Integer temp = testear(p);
            registros.add(new Registro(p, temp));
        }

        mapaTesteo.put(num, registros.get(0));
        mapaTesteo.put(num2, registros.get(1));
        mapaTesteo.put(num3, registros.get(2));
        mapaTesteo.put(num4, registros.get(3));
        mapaTesteo.put(num5, registros.get(4));

        Integer tamanio = mapaTesteo.size();
        Integer cont = 0;

        List<Registro> sanos = new ArrayList<>();
        List<Registro> aislados = new ArrayList<>();
        try {
            File file = new File("urgente.dat");
            String reg = "";
            for (Registro registro : mapaTesteo.values()) {
                if (aislar(registro.getTemp())) {
                    aislados.add(registro);
                } else {
                    sanos.add(registro);
                }

                reg += registro.getPersonas().getBarrio() + "/" +mapaTesteo.keySet();

            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, reg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Printeando sanos" + sanos.toString());
        System.out.println("Printeando enfermos" + aislados.toString());
        try{
            File sano = new File("sanos.json");
            File enfermos = new File("enfermos.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(sano,sanos);
            mapper.writerWithDefaultPrettyPrinter().writeValue(enfermos,aislados);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Integer testear(Personas p) {
        Random random = new Random();
        Integer temp = random.nextInt(36, 39);
        return temp;
    }

    public static Boolean aislar(Integer temp) {
        Boolean var = false;
        try {
            if (temp >= 38) {
                var = true;
                throw new tempException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var;
    }
}