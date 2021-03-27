import java.io.BufferedReader;
import java.io.*;
import java.net.*;

class Client {
    Socket socket;
    //Reader
    BufferedReader br;
    //writer..
    PrintWriter out;
    public Client(){
        try{
            System.out.println("Sending request to server");
            socket=new Socket("127.0.01",62038);
            System.out.println("Connection done.");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        }catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();

        }
    }

    public void startReading()
    {
        //thread-read krke deta rahega

        Runnable r1=()->{
            System.out.println("Reader started..");
            while (true){
                try {
                    String msg=br.readLine();
                    if(msg.equals(("exit"))){
                        System.out.println("Server the terminal the chat..");
                        break;
                    }
                    System.out.println("Seraj Ke papa Rehan :"+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        };
        new Thread(r1).start();
    }


    public void startWriting()
    {
        //thread data user lega and the send krega clint ko

        Runnable R2=()->{
            System.out.println("Writer star...");
            while (true){
                try {
                    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                    String content=br.readLine();
                    out.println(content);
                    out.flush();

                }catch (Exception e){
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }

        };
        new Thread(R2).start();
    }
    public static void main(String[] args) {
        System.out.println("Hello..");
        new Client();
    }
}
