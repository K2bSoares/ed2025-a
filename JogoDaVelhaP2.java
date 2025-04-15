import java.util.Scanner;

public class JogoDaVelhaP2 {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int tabuleiro[][] = new int[3][3];
    protected int jogador;

    public JogoDaVelhaP2() {
        limpaTabuleiro();
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogador = X;
    }

    public void poePeca(int i, int j) {
        if (i < 0 || i > 2 || j < 0 || j > 2) {
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (tabuleiro[i][j] != VAZIO) throw new IllegalArgumentException("Posição Ocupada");
        tabuleiro[i][j] = jogador;
        jogador = -jogador;
    }

    public boolean eVencedor(int marca) {
        // Verifica linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == marca && tabuleiro[i][1] == marca && tabuleiro[i][2] == marca) {
                return true;
            }
                    }

        // Verifica colunas
        for (int j = 0; j < 3; j++) {
        if (tabuleiro[0][j] == marca && tabuleiro[1][j] == marca && tabuleiro[2][j] == marca) {
            return true;
        }
        }

        // Verifica diagonais
        if (tabuleiro[0][0] == marca && tabuleiro[1][1] == marca && tabuleiro[2][2] == marca) {
            return true;
        }
        if (tabuleiro[0][2] == marca && tabuleiro[1][1] == marca && tabuleiro[2][0] == marca) {
            return true;
        }

        return false;
    }

    public int vencedor() {
        if (eVencedor(X)) {
            return X;
        } else if (eVencedor(O)) {
            return O;
        } else {
            // Verifica se há empate
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tabuleiro[i][j] == VAZIO) {
                        return 0; // Jogo ainda não terminou
                    }
                }
            }
            return 2; // Empate
        }
    }

    public void jogarContraSiMesma() {
Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            limpaTabuleiro();
        while (vencedor() == 0) {
            boolean jogadaValida = false;
            while (!jogadaValida) {
                int i = (int) (Math.random() * 3);
                int j = (int) (Math.random() * 3);
                if (tabuleiro[i][j] == VAZIO) {
                    poePeca(i, j);
                    jogadaValida = true;
                }
            }
            System.out.println(toString());
if (vencedor() == 0) {
                    System.out.println("O jogo ainda não acabou!\n");
                }
        }

        int resultado = vencedor();
        if (resultado == 1) {
            System.out.println("1 venceu!");
        } else if (resultado == -1) {
            System.out.println("-1 venceu!");
        } else if (resultado == 2) {
            System.out.println("2 Empate!");
        }

            System.out.println("Deseja jogar novamente? (s/n)");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }

        scanner.close();
    }

    public String toString() {
        String retorno = "";
        for (int i = 0; i < 3; i++) {
            retorno += "| ";
            for (int j = 0; j < 3; j++) {
if (tabuleiro[i][j] == X) {
                retorno += "X | ";
                } else if (tabuleiro[i][j] == O) {
                    retorno += "O | ";
                } else {
                    retorno += "  | ";
}
            }
            retorno += "\n";
        }
        return retorno;
    }

    public static void main(String[] args) {
        JogoDaVelhaP2 jogo = new JogoDaVelhaP2();
        jogo.jogarContraSiMesma();
    }
}