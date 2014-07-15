package grafikod.ders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import grafikod.ders.StarAssault;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="grafikKOd desktop title";
		config.useGL30=false;
		config.width=1280;
		config.height=720;
		config.fullscreen=false;

		new LwjglApplication(new StarAssault(), config);
	}
}
