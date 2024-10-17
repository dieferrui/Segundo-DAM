public enum TipoTanque {
    LIGERO("Ligero"),
    MEDIANO("Mediano"),
    PESADO("Pesado");

    public String tipo;

    TipoTanque(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
