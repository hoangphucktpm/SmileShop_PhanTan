package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implement the remote interface
public class RemoteObjectImpl extends UnicastRemoteObject implements RemoteObject {
    public RemoteObjectImpl() throws RemoteException {
        super();
    }
    @Override
    public String sayHello() throws RemoteException {
        return "Hello from the server!";
    }
}