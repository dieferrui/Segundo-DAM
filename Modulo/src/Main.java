public class Main {

    public static void main(String[] args) {

        String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies",
                "Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial",
                "Empresa i iniciativa emprenedora"};
        int[] hores = {6, 3, 6, 5, 5, 3};
        double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

        Modulo[] modulos = generarModulos(moduls, hores, notes);

        EscritorLector.guardarModulos(modulos);
        Modulo[] modulosCargadosOrdenados = EscritorLector.cargarModulos();
        mostrarModulos(modulosCargadosOrdenados);

        EscritorLector.guardarModulosCSV(modulos);
        Modulo[] modulosDesdeCSV = EscritorLector.cargarModulosCSV();
        mostrarModulos(modulosDesdeCSV);

    }

    private static Modulo[] generarModulos(String[] moduls, int[] hores, double[] notes) {

        Modulo[] modulos = new Modulo[moduls.length];

        for (int i = 0; i < moduls.length; i++) {

            Modulo nuevoModulo = new Modulo(moduls[i], hores[i], notes[i]);
            modulos[i] = nuevoModulo;

        }

        return modulos;
    }

    private static void mostrarModulos(Modulo[] modulos) {

        for (Modulo modulo : modulos) {

            System.out.println(modulo);

        }
    }
}