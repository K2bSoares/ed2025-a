public class JogoDaVelha {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int tabuleiro[][] = new int[3][3];
    protected int jogador;

    public JogoDaVelha() {
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
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += tabuleiro[i][j];
            }
            if (sum == marca * 3) {
                return true;
            }
            sum = 0;
        }
        if (diagonalDireita(marca) || diagonalEsquerda(marca)) {
            return true;
        }
        return false;
    }

    private boolean diagonalDireita(int marca) {
        int diagonal = 0;
        for (int i = 0; i < 3; i++) {
            diagonal += tabuleiro[i][i];
        }
        if (diagonal == marca * 3) {
            return true;
        }
        return false;
    }

    private boolean diagonalEsquerda(int marca) {
        int diagonal = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j > 0; j--) {
                diagonal += tabuleiro[i][j];
            }
        }
        if (diagonal == marca * 3) {
            return true;
        }
        return false;
    }

    public int vencedor() {
        if (eVencedor(1)) {
            return 1;
        } else if (eVencedor(-1)) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        String retorno = "";
        for (int i = 0; i < 3; i++) {
            retorno += "| ";
            for (int j = 0; j < 3; j++) {
                retorno += (tabuleiro[i][j] == X ? "X" : "O") + " | ";
            }
            retorno += "\n";
        }
        return retorno;
    }
}