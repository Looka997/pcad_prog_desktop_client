package client;

import common.Brands;
import common.Targhe;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* This client should be customized to fit the needs, for instance by sending manual requests
   through an UI.
. */

public class ClientMain {
    public static void main(String[] args) throws IOException {
        int nClients = 10, maxSleepTime =  10;
        ArrayList<RemoteSleepingClient> clients = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nClients);
        Targhe targhe = new Targhe();
        int length = Brands.values().length;
        for (int i=0; i<nClients; ++i) {
            clients.add(new RemoteSleepingClient(InetAddress.getLocalHost(), 8080,targhe.nextTarga(), Brands.values()[new Random().nextInt(length)].name(),maxSleepTime));
        }
        for(RemoteSleepingClient client : clients){
            executor.submit(client);
        }
        executor.shutdown();
    }
}
