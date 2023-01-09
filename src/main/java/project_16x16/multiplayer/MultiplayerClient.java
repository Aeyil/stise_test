package project_16x16.multiplayer;

import processing.data.JSONObject;
import processing.net.Client;
import project_16x16.SideScroller;

import java.net.ConnectException;

public class MultiplayerClient implements Multiplayer{
    private Client client;
    private JSONObject data;

    public MultiplayerClient(SideScroller player, String hostIP, int port) throws ConnectException {
        data = null;
        client = new Client(player, hostIP, port);
        if (!client.active()) {
            throw new java.net.ConnectException();
        }
    }

    public MultiplayerClient(SideScroller player) throws ConnectException {
        this(player, "127.0.0.1", 25565);
    }

    public JSONObject readData() {
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
        if (client.active()) {
            client.write(packet); // write to server
        }
    }

    public void exit() {
        if (client != null) {
            client.stop();
        }
    }

}
