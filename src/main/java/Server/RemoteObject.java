package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Define the remote interface
public interface RemoteObject extends Remote {
    String sayHello() throws RemoteException;
}