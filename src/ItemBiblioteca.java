import java.time.LocalDate;

public class ItemBiblioteca {
    public String titulo;
    public int ano;
    public int numerocopias;
    public LocalDate dataInicioEmprestimo;
    public LocalDate dataFimEmprestimo;

    @Override
    public String toString() {
        return "Título: " + titulo + ", Ano: " + ano + ", Número de Cópias: " + numerocopias +
               (dataInicioEmprestimo != null ? ", Empréstimo: " + dataInicioEmprestimo + " até " + dataFimEmprestimo : "");
    }
}