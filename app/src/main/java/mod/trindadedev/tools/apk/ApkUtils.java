package mod.trindadedev.tools.apk;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ApkUtils {

    private static String apkPath;

    public static void setApkPath(String path) {
        apkPath = path;
    }

    public static String getSHA1() {
        return getSignature("SHA-1");
    }

    public static String getSHA256() {
        return getSignature("SHA-256");
    }

    private static String getSignature(String algorithm) {
        if (apkPath == null || apkPath.isEmpty()) {
            return "APK path must be set before computing signature.";
        }

        try (FileInputStream fis = new FileInputStream(apkPath)) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            return byteArrayToHex(digest.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToHex(byte[] hashBytes) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : hashBytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString().toUpperCase();
        }
    }
}
