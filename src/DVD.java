public class DVD extends ItemBiblioteca {
    public int duracao;

    @Override
    public String toString() {
        return super.toString() + ", Duração: " + duracao + " minutos";
    }
}