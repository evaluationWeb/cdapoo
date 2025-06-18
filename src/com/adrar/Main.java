package com.adrar;

public class Main {
    public static void main(String[] args) {
        Vehicule motorcycle = new Vehicule("Yamaha", 2, 290);
        Vehicule clio = new Vehicule("Clio", 4, 170);
        Vehicule poidLour = new Vehicule("Camion", 8, 130);

        System.out.println("le véhicule est : " + motorcycle.detect());
        System.out.println("le véhicule est : " + clio.detect());
        System.out.println("le véhicule est : " + poidLour.detect());
        System.out.println("Le véhicule à une vitesse de : " + motorcycle.getVitesse());
        motorcycle.boost();
        System.out.println("Le véhicule à une vitesse de : " + motorcycle.getVitesse());


        System.out.println("Le véhicule le plus rapide "  + poidLour.plusRapide(clio));
    }
}
