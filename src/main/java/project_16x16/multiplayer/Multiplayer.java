package project_16x16.multiplayer;

import processing.data.JSONObject;

public interface Multiplayer {
	public JSONObject readData();
	public void writeData(String packet);

	public void exit();
}