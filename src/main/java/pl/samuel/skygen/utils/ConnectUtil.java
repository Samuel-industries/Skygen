package pl.samuel.skygen.utils;

import org.bukkit.entity.*;
import org.bukkit.plugin.*;

import pl.samuel.skygen.core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ConnectUtil {
	public static void connect(final Player p, final String srv) {
		final ByteArrayOutputStream b = new ByteArrayOutputStream();
		final DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(srv);
			b.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(core.getPlugin(), "BungeeCord", b.toByteArray());
	}
}