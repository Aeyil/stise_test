package project_16x16.multiplayer;

import processing.data.JSONObject;
import processing.net.Client;
import processing.net.Server;
import project_16x16.SideScroller;

import java.net.ConnectException;

public class MultiplayerServer implements Multiplayer{
    private Server server;
    private Client client;
    private JSONObject data;

    public MultiplayerServer(SideScroller player, int port) throws java.net.ConnectException {
        data = null;
        server = new Server(player, port);
        if (!server.active()) {
            throw new java.net.ConnectException();
        }
    }

    public MultiplayerServer(SideScroller player) throws ConnectException {
        this(player, 25565);
    }

    public JSONObject readData() {
        client = server.available();
        if (client != null && client.available() > 0) {
            String packet = client.readString();
            try {
                data = JSONObject.parse(packet);
            } catch (java.lang.RuntimeException e) {
            }
        }

        return data;
    }

    public void writeData(String packet) {
        server.write(packet); // write to client(s)
    }

    public void exit() {
        if (client != null) {
            client.stop();
        }
        if (server != null) { // TODO message clients.
            server.stop();
        }

    }

}
