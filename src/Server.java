import java.net.*;
import java.io.*;
public class Server {
    ServerSocket server;
    Socket socket;

    //Reader
    BufferedReader br;
    //writer..
    PrintWriter out;
    //Constracter....
    public Server ()
    {
        try{
            server= new ServerSocket(62038);
            System.out.println("Server is ready to acept cnnection");
            System.out.println("Wating...");
           socket= server.accept();

           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out=new PrintWriter(socket.getOutputStream());

           startReading();
           startWriting();
        }
        catch(Exception e) {
            //TODO: HANDLE exception
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
                        System.out.println("Clint terminal the chat..");
                        break;
                    }
                    System.out.println("Rehan ka beta Sraj :"+msg);
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
                    BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
                    String content= br1.readLine();
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
        System.out.println("This Your Server : ");
        new Server();
    }
}
