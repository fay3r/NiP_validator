import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NipAppServer{
    public NipAppServer() {
        try {
            NipApp c = new NipAppImpl();
	        Naming.rebind("rmi://localhost:1099/NipService", c);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void main(String args[]) throws RemoteException {

        LocateRegistry.createRegistry(1099);
        new NipAppServer();
    }

}
