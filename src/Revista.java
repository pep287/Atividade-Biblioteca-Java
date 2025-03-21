public class Revista extends ItemBiblioteca {
    public String Edição;

    @Override
    public String toString() {
        return super.toString() + ", Edição: " + Edição;
    }
}