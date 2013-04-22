package br.com.mobiplus.android.commons.version;

import android.os.Build;

public class VersionUtil {

	/**
	 * Retorna true se a versão do dispositivo for maior que 4 (API Level 14)
	 * 
	 * @return
	 */
	public static boolean isAndroid_4() {
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 14) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna true se a versão do dispositivo for maior que 3 (API Level 11)
	 * 
	 * @return
	 */
	public static boolean isAndroid_3() {
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 11) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna <code>true</code> se é Android 2.x "Eclair, Froyo ou Gingerbread" e menor que 3.x (API Level 7)
	 * 
	 * @return
	 */
	public static boolean isAndroid_2() {
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 7 && apiLevel < 11) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna true se a API informada for maior ou igual a API do aparelho
	 * 
	 * @param apiLevelToCompare
	 *            número da API level para comparação. ex:14
	 * @return
	 */
	public static boolean isEqualsOrGreaterThan(int apiLevelToCompare) {
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= apiLevelToCompare) {
			return true;
		}
		return false;
	}
}
