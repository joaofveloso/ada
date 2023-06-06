package br.com.ada.funcionalidades;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private static final List<String> LOCAIS = new ArrayList<>();

    {
        LOCAIS.add("LCL-2");
        LOCAIS.add("LCL-4");
        LOCAIS.add("LCL-6");
        LOCAIS.add("LCL-10");
        LOCAIS.add("LCL-14");
        LOCAIS.add("LCL-16");
        LOCAIS.add("LCL-34");
        LOCAIS.add("LCL-45");
        LOCAIS.add("LCL-49");
        LOCAIS.add("LCL-50");
        LOCAIS.add("LCL-51");
        LOCAIS.add("LCL-56");
        LOCAIS.add("LCL-70");
        LOCAIS.add("LCL-90");
    }

    public static void adicionarLocal(String local) {
        LOCAIS.add(local);
    }

    public static boolean localJaEstaCadastrado(String local) {
        return LOCAIS.contains(local);
    }

    public static void verificaSeExistemLocaisDuplicados() {
        if (LOCAIS.size() != LOCAIS.stream().distinct().count()) {
            System.out.println("Existem locais duplicados cadastrados!!!");
        }
    }
}
