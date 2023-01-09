package project_16x16.multiplayer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.ConnectException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import processing.data.JSONObject;
import processing.net.Client;
import processing.net.Server;
import project_16x16.SideScroller;
import project_16x16.multiplayer.Multiplayer;

@ExtendWith(MockitoExtension.class)
public class MultiplayerTest {

	@Mock
	private SideScroller player;

	private Multiplayer multiplayer;

	@AfterEach
	void tearDown() {
		player=null;
		multiplayer=null;
	}

	@Test
	public void callingConstructorAsServer_ok() {
		ConnectException ce = null;
		try {
			multiplayer = new MultiplayerServer(player);
		}
		catch (ConnectException e) {
			ce = e;
		}

		assertNull(ce);
	}

	@Test
	public void callingConstructorAsClient_ok() {
		ConnectException ce = null;
		try {
			multiplayer = new MultiplayerClient(player);
		}
		catch (ConnectException e) {
			ce = e;
		}
		assertNotNull(ce);
	}

	@Test
	public void callingConstructorAsServer_raisesException() {
		ConnectException ce = null;
		try (MockedConstruction<Server> mocked = mockConstruction(Server.class, (mock, context) -> {
			when(mock.active()).thenReturn(false);
		})) {
			try {
				multiplayer = new MultiplayerServer(player);
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNotNull(ce);
		}
	}

	@Test
	public void callingConstructorAsClient_raisesException() {
		ConnectException ce = null;
		try (MockedConstruction<Client> mocked = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(false);
		})) {
			try {
				multiplayer = new MultiplayerClient(player);
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNotNull(ce);
		}
	}

	@Test
	public void callingReadDataAsServerWithNoClient_returnsNullData() {
		ConnectException ce = null;
		JSONObject data = null;
		try (MockedConstruction<Server> mocked = mockConstruction(Server.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
			when(mock.available()).thenReturn(null);
		})) {
			try {
				multiplayer = new MultiplayerServer(player);
				data = multiplayer.readData();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
			assertNull(data);
		}
	}

	@Test
	public void callingReadDataAsServerWithClient_returnsData() {
		ConnectException ce = null;
		JSONObject data = null;

		Client client = mock(Client.class);
		when(client.available()).thenReturn(1);
		when(client.readString()).thenReturn("{}");
		try (MockedConstruction<Server> mocked = mockConstruction(Server.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
			when(mock.available()).thenReturn(client);
		})) {
			try {
				multiplayer = new MultiplayerServer(player);
				data = multiplayer.readData();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
			assertNotNull(data);
			assertTrue(data instanceof JSONObject);
		}
	}

	@Test
	public void callingReadDataAsClientWithNoAvailableData_returnsNullData() {
		ConnectException ce = null;
		JSONObject data = null;
		try (MockedConstruction<Client> client = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
			when(mock.available()).thenReturn(0);
		})) {
			try {
				multiplayer = new MultiplayerClient(player);
				data = multiplayer.readData();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
			assertNull(data);
		}
	}

	@Test
	public void callingReadDataAsClient_returnsData() {
		ConnectException ce = null;
		JSONObject data = null;
		try (MockedConstruction<Client> client = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
			when(mock.available()).thenReturn(1);
			when(mock.readString()).thenReturn("{}");
		})) {
			try {
				multiplayer = new MultiplayerClient(player);
				data = multiplayer.readData();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
			assertNotNull(data);
			assertTrue(data instanceof JSONObject);
		}
	}

	@Test
	public void callingWriteDataAsServer() {
		ConnectException ce = null;
		try (MockedConstruction<Server> mocked = mockConstruction(Server.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
		})) {
			try {
				multiplayer = new MultiplayerServer(player);
				multiplayer.writeData("");

				assertFalse(mocked.constructed().isEmpty());
				verify(mocked.constructed().get(0), times(1)).write("");
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
		}
	}

	@Test
	public void callingWriteDataAsClient() {
		ConnectException ce = null;
		try (MockedConstruction<Client> mocked = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
		})) {
			try {
				multiplayer = new MultiplayerClient(player);
				multiplayer.writeData("");

				assertFalse(mocked.constructed().isEmpty());
				verify(mocked.constructed().get(0), times(1)).write("");
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
		}
	}

	@Test
	public void callingWriteDataAsClientNotActive_doNotWrites() {
		ConnectException ce = null;
		try (MockedConstruction<Client> mocked = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
		})) {
			try {
				multiplayer = new MultiplayerClient(player);

				assertFalse(mocked.constructed().isEmpty());
				when(mocked.constructed().get(0).active()).thenReturn(false);
				multiplayer.writeData("");
				verify(mocked.constructed().get(0), times(0)).write("");
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
		}
	}

	@Test
	public void callingExitAsServer() {
		ConnectException ce = null;
		try (MockedConstruction<Server> mocked = mockConstruction(Server.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
		})) {
			try {
				multiplayer = new MultiplayerServer(player);
				multiplayer.exit();

				assertFalse(mocked.constructed().isEmpty());
				verify(mocked.constructed().get(0), times(1)).stop();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
		}
	}

	@Test
	public void callingExitAsClient() {
		ConnectException ce = null;
		try (MockedConstruction<Client> mocked = mockConstruction(Client.class, (mock, context) -> {
			when(mock.active()).thenReturn(true);
		})) {
			try {
				multiplayer = new MultiplayerClient(player);
				multiplayer.exit();

				assertFalse(mocked.constructed().isEmpty());
				verify(mocked.constructed().get(0), times(1)).stop();
			}
			catch (ConnectException e) {
				ce = e;
			}

			assertNull(ce);
		}
	}

}
