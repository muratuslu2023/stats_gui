package com.murat_uslu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MemoryInfo {
    static {
        try {
            // Extract the native library to a temp file
            InputStream in = MemoryInfo.class.getResourceAsStream("/native/libmemoryinfo.dylib");
            if (in == null) {
                throw new IOException("Native library not found in resources");
            }

            File tempFile = File.createTempFile("libmemoryinfo", ".dylib");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            System.load(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load native library", e);
        }
    }

    public native String getMemoryInfo();

    public static String[] parseMemoryInfo(String info){
        String[] lines = info.split("\n");

        for(int i = 0; i < lines.length; i++){
            String line = lines[i];
            String size = line.split("\\s+")[1];
            lines[i] = size;
        }

        return lines;
    }

}
