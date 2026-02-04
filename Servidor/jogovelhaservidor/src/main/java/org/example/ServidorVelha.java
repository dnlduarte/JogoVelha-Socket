import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServidorVelha {

    public static void main(String[] args) throws Exception {

        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor aguardando conexão...");

        Socket socket = servidor.accept();
        System.out.println("Cliente conectado!");

        Scanner entrada = new Scanner(socket.getInputStream());
        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        Scanner teclado = new Scanner(System.in);

        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        while (true) {

            mostrar(board);
            System.out.println("Sua vez (X)");
            System.out.print("Linha: ");
            int row = teclado.nextInt();
            System.out.print("Coluna: ");
            int col = teclado.nextInt();

            if (invalida(board, row, col)) {
                System.out.println("Jogada inválida.");
                continue;
            }

            board[row][col] = 'X';
            saida.println(row + " " + col);

            if (venceu(board, 'X')) {
                mostrar(board);
                System.out.println("Você venceu!");
                break;
            }

            System.out.println("Vez do cliente (O)...");
            String jogada = entrada.nextLine();
            String[] p = jogada.split(" ");
            board[Integer.parseInt(p[0])][Integer.parseInt(p[1])] = 'O';

            if (venceu(board, 'O')) {
                mostrar(board);
                System.out.println("Cliente venceu!");
                break;
            }
        }

        socket.close();
        servidor.close();
    }

    static void mostrar(char[][] b) {
        System.out.println("   0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "  " + b[i][0] + " | " + b[i][1] + " | " + b[i][2]);
            if (i < 2) System.out.println("  ---+---+---");
        }
    }

    static boolean invalida(char[][] b, int r, int c) {
        return r < 0 || r > 2 || c < 0 || c > 2 || b[r][c] != ' ';
    }

    static boolean venceu(char[][] b, char p) {
        return
                (b[0][0]==p && b[0][1]==p && b[0][2]==p) ||
                        (b[1][0]==p && b[1][1]==p && b[1][2]==p) ||
                        (b[2][0]==p && b[2][1]==p && b[2][2]==p) ||
                        (b[0][0]==p && b[1][0]==p && b[2][0]==p) ||
                        (b[0][1]==p && b[1][1]==p && b[2][1]==p) ||
                        (b[0][2]==p && b[1][2]==p && b[2][2]==p) ||
                        (b[0][0]==p && b[1][1]==p && b[2][2]==p) ||
                        (b[0][2]==p && b[1][1]==p && b[2][0]==p);
    }
}
