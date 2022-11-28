package Proyecto;

import java.util.Scanner;

public class Principal {
    
    static Scanner entrada = new Scanner(System.in);
    final static int num = 4;
    static Aeropuerto aeropuertos[] = new Aeropuerto[num];
    
    public static void main(String[] args){
        insertarDatosAeropuertos(aeropuertos);
        menu();
    }

    public static void insertarDatosAeropuertos(Aeropuerto aero[]){
        //Aeopuerto → Publico - Privado → Compañias → Vuelos → Pasajeros
        aero[0] = new AeropuertoPublico(80000000, "Jorge Chavez", "Lima", "Perú");
        aero[0].insertarCompañia(new Compañia("AeroPeru"));
        aero[0].insertarCompañia(new Compañia("LATAM"));
        aero[0].getCompañia("AeroPeru").insertarVuelo(new Vuelo("IB20", "Lima", "Mexico", 150.90, 150));
        aero[0].getCompañia("AeroPeru").insertarVuelo(new Vuelo("IB21", "Lima", "Buenos Aires", 180.99, 120));
        aero[0].getCompañia("LATAM").insertarVuelo(new Vuelo("FC12", "Lima", "Londres", 500.90, 180));
        aero[0].getCompañia("AeroPeru").getVuelo("IB20").insertarPasajero(new Pasajero("Alejandro", "20BGHP", "Peruano"));
        aero[0].getCompañia("AeroPeru").getVuelo("IB20").insertarPasajero(new Pasajero("Jean Pierre", "BGKL20", "Peruano"));
        aero[0].getCompañia("LATAM").getVuelo("FC12").insertarPasajero(new Pasajero("Raul", "JH21", "Peruano"));
        
        aero[1] = new AeropuertoPrivado("Central Ciudad Real", "Ciudad Real", "España");
        aero[1].insertarCompañia(new Compañia("AirEuropa"));
        String empresas[] = {"Cobresol","Aguila 34"};
        ((AeropuertoPrivado)aero[1]).insertarEmpresa(empresas);
        aero[1].getCompañia("AirEuropa").insertarVuelo(new Vuelo("AE025", "Madrid", "Buenos Aires", 170.10, 190));
        aero[1].getCompañia("AeroPeru").getVuelo("AE025").insertarPasajero(new Pasajero("Maria", "DFG14", "Española"));
        
        aero[2] = new AeropuertoPublico(20000000, "Aeropuerto Bogota", "Bogota", "Colombia");
        aero[2].insertarCompañia(new Compañia("AirAmerica"));
        aero[2].insertarCompañia(new Compañia("VuelaBogota"));
        aero[2].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE030", "Bogota", "Lima", 80, 120));
        aero[2].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE031", "Bogota", "Lima", 70, 100));
        aero[2].getCompañia("AirAmerica").getVuelo("AE030").insertarPasajero(new Pasajero("Carlos", "YUI17", "Colombiano"));
        aero[2].getCompañia("AirAmerica").getVuelo("AE031").insertarPasajero(new Pasajero("Luis", "THN50", "Colombiano"));
        
        aero[3] = new AeropuertoPublico(40000000, "Aeropuerto Mexico", "Mexico", "Mexico");
        aero[3].insertarCompañia(new Compañia("AeroMexico"));
        aero[3].getCompañia("AeroMexico").insertarVuelo(new Vuelo("IB2040", "Mexico", "Nueva York", 140.50, 150));
        aero[3].getCompañia("AirAmerica").insertarVuelo(new Vuelo("IB2042", "Mexico", "Lima", 100.20, 110));
        aero[3].getCompañia("AirAmerica").getVuelo("AE030").insertarPasajero(new Pasajero("Juan", "TKN74", "Mexicano"));
    }
    
    public static void menu(){
        String nombreAeropuerto;
        String nombreCompañia;
        String origen, destino;
        Compañia compañia;
        int opcion;
        Aeropuerto aeropuerto;
        do {
            System.out.println("\t.:MENU:.");
            System.out.println("1. Ver aeropuertuos gestionados(Publicos o Privados");
            System.out.println("2. Ver empresas(Privado) o suvbención(Público)");
            System.out.println("3. Listar compañias de un Aeropuerto");
            System.out.println("4. Lista de vuelos por Compañia");
            System.out.println("5. Listar posibles vuelos de Origen a Destino");
            System.out.println("6. SALIR");
            System.out.print("OPCION: ");
            opcion = entrada.nextInt();
            
            switch(opcion){
                case 1: System.out.println("");
                    mostrarDatosAeropuertos(aeropuertos);
                    break;
                case 2: System.out.println("");
                    mostrarPatrocinio(aeropuertos);
                    break;
                case 3: System.out.println("");
                        entrada.nextLine();
                        System.out.print("\nDigite el nombre del Aeropuerto");
                        nombreAeropuerto = entrada.nextLine();
                        aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                        if (aeropuerto == null) {
                            System.out.println("El aeropuerto no existe");
                        }else{
                            mostrarCompañias(aeropuerto);
                        }
                        break;
                case 4: System.out.println("");
                        entrada.nextLine();
                        System.out.println("Digite el nombre del Aeropuerto");
                        nombreAeropuerto = entrada.nextLine();
                        aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                        if (aeropuerto==null) {
                            System.out.println("El aeropuerto no existe");
                        }else{
                            System.out.print("Digite el nombre de la compalia:");
                            nombreCompañia = entrada.nextLine();
                            compañia = aeropuerto.getCompañia(nombreCompañia);
                            mostrarVuelos(compañia);
                        }
                    break;
                case 5: entrada.nextLine();
                        System.out.print("\nDigite la ciudad de Origen: ");
                        origen = entrada.nextLine();
                        System.out.print("\nDigite la ciudad de Destino: ");
                        destino = entrada.nextLine();
                        mostrarVueloOrgenDestino(origen, destino, aeropuertos);
                    break;
                case 6: 
                    break;
                default: System.out.println("ERROR - Se equivico de opción");
            }
            System.out.println("");
        } while (opcion!=6);
    }
    
    public static void mostrarDatosAeropuertos(Aeropuerto aeropuertos[]){
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] instanceof  AeropuertoPrivado) {
                System.out.println("Aeropuerto Privado");
                System.out.println("NOMBRE: "+aeropuertos[i].getNombre());
                System.out.println("CIUDAD: "+aeropuertos[i].getCiudad());
                System.out.println("PAIS: "+aeropuertos[i].getPais());
            }else{
                System.out.println("Aeropuerto Publico");
                System.out.println("NOMBRE: "+aeropuertos[i].getNombre());
                System.out.println("CIUDAD: "+aeropuertos[i].getCiudad());
                System.out.println("PAIS: "+aeropuertos[i].getPais());
            }
            System.out.println("");
        }
    }
    
    public static void mostrarPatrocinio(Aeropuerto aeropuertos[]){
        String empresas[];
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] instanceof AeropuertoPrivado) {
                System.out.println("Aeropuerto Privado: "+aeropuertos[i].getNombre());
                empresas = ((AeropuertoPrivado)aeropuertos[i]).getListaEmpresas();
                System.out.println("Empresas: ");
                for (int j = 0; j < empresas.length; j++) {
                    System.out.println(empresas[j]);
                }
            }else{
                System.out.println("Aeropuerto Publico: "+aeropuertos[i].getNombre());
                System.out.println("Subvencion: "+((AeropuertoPublico)aeropuertos[i]).getSubvencion());
            }
        }
    }
    
    public static Aeropuerto buscarAeropuerto(String nombre, Aeropuerto aeropuertos[]){
        boolean encontrado=false;
        int i=0;
        Aeropuerto aero =null;
        while((!encontrado) && (i<aeropuertos.length)){
            if (nombre.equals(aeropuertos[i].getNombre())) {
                encontrado = true;
                aero = aeropuertos[i];
            }
            i++;
        }
        return aero;
    }
    
    public static void mostrarCompañias(Aeropuerto aeropuerto){
        System.out.println("\nLas Compañias del Aeropuerto: "+aeropuerto.getNombre());
        for (int i = 0; i < aeropuerto.getNumCompañia(); i++) {
            System.out.println(aeropuerto.getCompañia(i).getNombre());
        }
    }
    
    public static void mostrarVuelos(Compañia compañia){
        Vuelo vuelo;   
        System.out.println("Los vuelos de la compañia: "+compañia.getNombre());
        for (int i = 0; i < compañia.getNumVuelo(); i++) {
            vuelo = compañia.getVuelo(i);
            System.out.println("Identidicador: "+vuelo.getIdentificador());
            System.out.println("Ciudad Origen: "+vuelo.getCiudadOrigen());
            System.out.println("Ciudad Destino: "+vuelo.getCiudadDestino());
            System.out.println("Precio del vuelo: $"+vuelo.getPrecio());
            System.out.println("");
        }
    }
    
    public static Vuelo[] buscarVuelosOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]) {
        Vuelo vuelo;
        Vuelo listaVuelos[];
        int contador = 0 ;
        for (int i = 0; i < aeropuertos.length; i++) {//recorremos aeropuertos
            for (int j = 0; j < aeropuertos[i].getNumCompañia(); j++) {//recorremos compañias
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) { //recorremos los vuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        contador++;
                    }
                }
            }
        }
        listaVuelos = new Vuelo[contador];
        int q=0;
        
        for (int i = 0; i < aeropuertos.length; i++) {
            for (int j = 0; j < aeropuertos[i].getNumCompañia(); j++) {
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) {
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        listaVuelos[q] = vuelo;
                        q++;
                    }
                }
            }
        }
        
        return listaVuelos;
    }
    
    public static void mostrarVueloOrgenDestino(String origen, String destino, Aeropuerto aeropuertos[]) {
        Vuelo vuelos[];
        vuelos = buscarVuelosOrigenDestino(origen, destino, aeropuertos);
        if (vuelos.length==0) {
            System.out.println("No existen vuelos de esa ciudad Origen a Destino");
        }else{
            System.out.println("\nVuelos encontrados: ");
            for (int i = 0; i < vuelos.length; i++) {
                System.out.println("Identificador de vuelo: "+vuelos[i].getIdentificador());
                System.out.println("Ciudad de origen: "+vuelos[i].getCiudadOrigen());
                System.out.println("Ciudad de destino: "+vuelos[i].getCiudadDestino());
                System.out.println("Precio: $"+vuelos[i].getPrecio());
                System.out.println("");
            }
        }
        
    }
}
