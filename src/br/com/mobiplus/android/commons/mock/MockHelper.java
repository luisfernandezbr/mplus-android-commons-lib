package br.com.mobiplus.android.commons.mock;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class MockHelper {

	/**
	 * Esse método tem um delay de espera de 1 segundo.
	 * <p>
	 * <code>Thread.sleep(1000)</code>
	 * 
	 * @param context
	 * @param rawResource
	 * @return
	 * @throws IOException
	 */
	public static String getJson(Context context, int rawResource) throws IOException {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		InputStream is = context.getResources().openRawResource(rawResource);
		byte[] buffer = new byte[is.available()];
		while (is.read(buffer) != -1)
			;
		String jsontext = new String(buffer);
		return jsontext;
	}

	/**
	 * 
	 * @param context
	 * @param rawResource
	 * @param waitTime
	 *            Tempo de espera antes do response
	 * @return
	 * @throws IOException
	 */
	public static String getJson(Context context, int rawResource, int waitTime) throws IOException {

		if (waitTime > 0) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		InputStream is = context.getResources().openRawResource(rawResource);
		byte[] buffer = new byte[is.available()];
		while (is.read(buffer) != -1)
			;
		String jsontext = new String(buffer);
		return jsontext;
	}
}
