public class Livro extends ItemBiblioteca {
    public String autor;

    @Override
    public String toString() {
        return super.toString() + ", Autor: " + autor;
    }
}