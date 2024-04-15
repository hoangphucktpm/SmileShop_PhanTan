package Server;



import Database.ConnectDatabase;
import xuly.CountDownThread;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

public class server {

    private static final String URL = "rmi://192.168.1.26:2951/";

    public static void main(String[] args) {
        try {
            try {
                // Start the database connection
                ConnectDatabase.getInstance().connect();

                // Start the countdown thread
                CountDownThread countDownThread = new CountDownThread();
                countDownThread.start();

                // Create the RMI registry
                Registry registry = LocateRegistry.createRegistry(2951);

                // Create a remote object
                RemoteObjectImpl remoteObject = new RemoteObjectImpl();

                // Export the remote object
                RemoteObject stub = (RemoteObject) UnicastRemoteObject.exportObject(remoteObject, 0);

                // Bind the remote object's stub in the registry
                registry.bind("RemoteObject", stub);

                System.out.println("Server is running...");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}