package com.udacity.jwdnd.course1.cloudstorage.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Utility {
    public static File multipartToFile(MultipartFile multiFile) {
        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // After operating the above files, you need to delete the temporary files generated in the root directory
            File f = new File(file.toURI());
            f.delete();
        }
        return file;
    }

    public static String getFileSizeKiloBytes(File file) {
        System.out.println(file.length());
        return (double) file.length() / 1024 + "  kb";
    }
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
}
