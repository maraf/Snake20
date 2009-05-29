/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

/**
 *
 * @author Marek SMM
 */
public class ServerThread implements Runnable {
    private Server SnakeServer;
    
    ServerThread() {
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        SnakeServer = new Server();
        SnakeServer.startServer();
    }
    
    public Server getServer() {
        return SnakeServer;
    }
}
