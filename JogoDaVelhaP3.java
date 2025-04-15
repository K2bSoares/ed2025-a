import java.util.Random;
import java.util.Scanner;

public class JogoDaVelhaP3 {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int[][] tabuleiro;
    protected int jogador;
    protected int dimensao;
    Random random = new Random();

    public JogoDaVelhaP3() {
// Construtor padrão com dimensão 3
        setDimensao(3);
    }

    public JogoDaVelhaP3(int dimensao) {
        setDimensao(dimensao);
    }

    public void setDimensao(int dimensao) {
        if (dimensao < 3) {
            throw new IllegalArgumentException("A dimensão deve ser pelo menos 3");
        }
        this.dimensao = dimensao;
        this.tabuleiro = new int[dimensao][dimensao];
        limpaTabuleiro();
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogador = X;
    }

    public void poePeca(int i, int j) {
        if (i < 0 || i >= dimensao || j < 0 || j >= dimensao) {
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (tabuleiro[i][j] != VAZIO) throw new IllegalArgumentException("Posição Ocupada");
        tabuleiro[i][j] = jogador;
        jogador = -jogador;
    }

    public boolean eVencedor(int marca) {
        // Verifica linhas
        for (int i = 0; i < dimensao; i++) {
boolean venceuLinha = true;
            for (int j = 0; j < dimensao; j++) {
            if (tabuleiro[i][j] != marca) {
venceuLinha = false;
                    break;
                }
            }
            if (venceuLinha) return true;
                    }

        // Verifica colunas
        for (int j = 0; j < dimensao; j++) {
            boolean venceuColuna = true;
            for (int i = 0; i < dimensao; i++) {
                if (tabuleiro[i][j] != marca) {
                    venceuColuna = false;
                    break;
                }
            }
            if (venceuColuna) return true;
        }

        // Verifica diagonal principal
        boolean venceuDiagonal1 = true;
        for (int i = 0; i < dimensao; i++) {
            if (tabuleiro[i][i] != marca) {
                venceuDiagonal1 = false;
                break;
            }
        }
        if (venceuDiagonal1) return true;

        // Verifica diagonal secundária
        boolean venceuDiagonal2 = true;
        for (int i = 0; i < dimensao; i++) {
            if (tabuleiro[i][dimensao - 1 - i] != marca) {
                venceuDiagonal2 = false;
                break;
        }
}
        return venceuDiagonal2;
    }

    public int vencedor() {
        if (eVencedor(X)) {
            return X;
        } else if (eVencedor(O)) {
            return O;
        } else {
            // Verifica se há empate
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
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
                    int i = (int) (Math.random() * dimensao);
                    int j = (int) (Math.random() * dimensao);
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
        StringBuilder retorno = new StringBuilder();
        for (int i = 0; i < dimensao; i++) {
            retorno.append("| ");
            for (int j = 0; j < dimensao; j++) {
                if (tabuleiro[i][j] == X) {
                    retorno.append("X | ");
                } else if (tabuleiro[i][j] == O) {
                    retorno.append("O | ");
                } else {
                    retorno.append("  | ");
                }
            }
            retorno.append("\n");
        }
        return retorno.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha com dimensão variável!");
        
        System.out.println("Escolha a dimensão do tabuleiro (3 para 3x3, 5 para 5x5, etc.):");
        int dimensao = 3; // Valor padrão
        
        try {
            dimensao = Integer.parseInt(scanner.nextLine());
            if (dimensao < 3) {
                System.out.println("Dimensão inválida. Usando dimensão padrão 3x3.");
                dimensao = 3;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Usando dimensão padrão 3x3.");
        }
        
        System.out.println("Iniciando jogo com tabuleiro " + dimensao + "x" + dimensao);
        
        JogoDaVelhaP3 jogo = new JogoDaVelhaP3(dimensao);
        jogo.jogarContraSiMesma();
    }
}