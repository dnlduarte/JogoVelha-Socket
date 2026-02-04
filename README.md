# #️⃣ Jogo da Velha Multiplayer em Java (Socket)

Projeto simples de um **Jogo da Velha em multiplayer** desenvolvido em **Java**, utilizando **Sockets TCP** no modelo **cliente-servidor**.  
O jogo é executado via **terminal (CMD)** e permite que dois jogadores joguem em computadores diferentes dentro da mesma rede.

---

## 📌 Objetivo do Projeto

Este projeto foi criado com fins educacionais, com o objetivo de praticar Java...

- Comunicação em rede com Sockets
- Arquitetura cliente-servidor
- Organização de projetos Java (Maven)
- Versionamento com Git e GitHub

---

## 🧠 Como funciona

- O **Servidor** inicia primeiro e fica aguardando a conexão do cliente.
- O **Cliente** se conecta ao servidor via IP e porta.
- Cada jogador faz sua jogada alternadamente pelo terminal/CMD.
- O servidor controla o fluxo do jogo e envia as jogadas ao cliente.

---

## 🔔 Observações

- **IP do Servidor**  
  Antes de executar o cliente, alterar o endereço IP do servidor no arquivo:
  `ClienteVelha.java`

  Exemplo:
   ```java
   String ipServidor = "000.000.0.00";

  ### 🔹 Pré-requisitos
- Java JDK atualizado
- Maven (gerar `.jar`)
- Dois terminais/dois computadores
- Conexão em rede entre cliente e servidor

---

```md
> ⚠️ Este projeto é voltado para fins educacionais e aprendizado de redes em Java.
