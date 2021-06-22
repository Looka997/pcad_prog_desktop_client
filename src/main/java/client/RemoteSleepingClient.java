package client;
import common.Targa;
import java.net.InetAddress;
import java.util.Random;

public class RemoteSleepingClient extends RemoteClient implements Runnable {

    private int maxSleepTime;


    public RemoteSleepingClient(InetAddress address, int port, Targa targa, String marca, int maxSleepTime) {
        super(address, port, targa, marca);
        this.maxSleepTime = maxSleepTime;
    }
    @Override
    public void run() {
        try {
            if (!park()) {
                System.out.println("client " + getTarga() + " got late to the parking");
                return;
            }
            int sleepyTime = maxSleepTime == 0? 0 : new Random().nextInt(maxSleepTime);
            if (sleepyTime != 0)
                System.out.println("client " + getTarga() + " is sleeping for " + sleepyTime + "s");
            Thread.sleep((sleepyTime) * 1000);
            unpark();
        } catch (InterruptedException e) {
            unpark();
            return;
        }
    }
}
