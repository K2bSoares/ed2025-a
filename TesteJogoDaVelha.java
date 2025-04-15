public class TesteJogoDaVelha {
    public static void main(String[] args) {
        System.out.println("Jogando para ganhar");
        // Instanciando o jogo da velha
        JogoDaVelhaP3 jogo = new JogoDaVelhaP3();
        jogo.poePeca(0, 0); //x
        jogo.poePeca(1,1);
        jogo.poePeca(0, 1); //x
        jogo.poePeca(2,0);
        jogo.poePeca(0, 2); //x
        jogo.poePeca(2, 2);
        jogo.poePeca(1, 0); //x
        jogo.poePeca(1, 2);
        jogo.poePeca(2, 1); //x


        System.out.println(jogo.eVencedor(1));
        System.out.println(jogo.vencedor());
        System.out.println(jogo.toString());
   }
}