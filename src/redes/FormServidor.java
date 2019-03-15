package redes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author Alexyz
 */
public class FormServidor extends JFrame implements Runnable
{
    public static void main(String []args)
    {
        FormServidor servidor=new FormServidor();
    }
    
    public FormServidor()
    {
        setSize(2400,2400);
        setVisible(false);
        
        Thread hilo=new Thread(this);
        hilo.start();
    }

    @Override
    public void run() 
    {
        try
        {
            ServerSocket servidor=new ServerSocket(9090);
            Socket cliente;
            
            while(true)
            {
                System.out.println("Corriendo");
                
                cliente=servidor.accept();
                ObjectInputStream flujo=new ObjectInputStream(cliente.getInputStream());
                
                Orden orden=(Orden) flujo.readObject();
                
                if(orden.getOrden().equals("Calculadora"))
                {
                    Runtime runtime=Runtime.getRuntime();
                     runtime.exec("cmd /c calc.exe");
                }
                else if(orden.getOrden().equals("Apagar"))
                {
                    Runtime runtime=Runtime.getRuntime();
                    runtime.exec("cmd /c shutdown -s -t 15");
                }
                else if(orden.getOrden().equals("Reiniciar"))
                {
                     Runtime runtime=Runtime.getRuntime();
                    runtime.exec("cmd /c shutdown -r -t 15");
                }
                else if(orden.getOrden().equals("Fin"))
                {
                   servidor.close();
                   break;
                }
                
                cliente.close();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Servidor");
        }
    }
}
