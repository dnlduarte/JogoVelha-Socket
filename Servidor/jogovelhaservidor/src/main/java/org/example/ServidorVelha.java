import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServidorVelha {

    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket servidor = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta + ". Aguardando cliente...");

            try (Socket socket = servidor.accept();
                 Scanner entrada = new Scanner(socket.getInputStream());
                 PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                 Scanner teclado = new Scanner(System.in)) {

                System.out.println("Cliente conectado!");
                char[][] board = new char[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        board[i][j] = ' ';

                while (true) {
                    // Jogada do servidor
                    mostrar(board);
                    int row, col;
                    do {
                        System.out.println("Sua vez (X)...");
                        System.out.print("- Linha: "); row = teclado.nextInt();
                        System.out.print("- Coluna: "); col = teclado.nextInt();
                    } while (invalida(board, row, col));

                    board[row][col] = 'X';
                    saida.println(row + " " + col);
                    mostrar(board);
                    if (venceu(board, 'X')) { mostrar(board); System.out.println("Você venceu!"); break; }
                    if (empate(board)) { mostrar(board); System.out.println("Empate!"); break; }

                    // Jogada do cliente
                    System.out.println("Vez do cliente (O)...");
                    String jogada = entrada.nextLine();
                    String[] p = jogada.split(" ");
                    board[Integer.parseInt(p[0])][Integer.parseInt(p[1])] = 'O';

                    if (venceu(board, 'O')) { mostrar(board); System.out.println("Cliente venceu!"); break; }
                    if (empate(board)) { mostrar(board); System.out.println("Empate!"); break; }
                }

            } catch (Exception e) {
                System.out.println("Erro na conexão com o cliente: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    static void mostrar(char[][] b) {
        System.out.println("     0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "    ");
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("   ------------");
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

    static boolean empate(char[][] b) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (b[i][j] == ' ') return false;
        return true;
    }
}
