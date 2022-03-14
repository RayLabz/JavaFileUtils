package com.raylabz.javafileutils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provides utility methods for managing files and directories.
 */
public class FileManager {

    /**
     * Writes text data to a file.
     * @param path The path of the file to write the content to.
     * @param content The content to write to the file.
     * @throws IOException Throws IOException when the file cannot be opened for a write.
     */
    public static void writeFile(String path, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }

    /**
     * Writes binary data to a file.
     * @param path The path of the file to write the content to.
     * @param content The content to write to the file.
     * @throws IOException Throws IOException when the file cannot be opened for a write.
     */
    public static void writeFile(String path, byte[] content) throws IOException {
        FileOutputStream writer = new FileOutputStream(path);
        writer.write(content);
    }

    /**
     * Reads text data from a file.
     * @param path The path of the file to read.
     * @return Returns a String.
     * @throws IOException Throws IOException when the file cannot be opened for a read.
     */
    public static String readTextFile(String path) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(path)));
        return data;
    }

    /**
     * Reads binary data from a file.
     * @param path The path of the file to read.
     * @return Returns a byte array.
     * @throws IOException Throws IOException when the file cannot be opened for a read.
     */
    public static byte[] readBinaryFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * Checks if a file exists.
     * @param path The path of the file to check.
     * @return Returns true if the file exists, false otherwise.
     */
    public static boolean fileExists(String path) {
        File tmpDir = new File(path);
        return tmpDir.exists();
    }

    /**
     * Deletes a file.
     * @param path The path of the file to delete.
     * @return Returns true if the file was deleted, false otherwise.
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    /**
     * Lists files in a filepath.
     * @param path The filepath to list the files of.
     * @return Returns a String[] containing the paths of the files.
     */
    public static String[] listFiles(String path) {
        File file = new File(path);
        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                File file = new File(current, name);
                return !file.isDirectory();
            }
        });
    }

    /**
     * Returns the size of a file in bytes.
     * @param path The path of the file.
     * @return Returns a long.
     */
    public static long getFileSize(String path) {
        File file = new File(path);
        return file.length();
    }

    //TODO - Search files

    /**
     * Checks if a given path is a directory.
     * @param path The path.
     * @return Returns true if the path is a directory, false otherwise.
     */
    public static boolean isDirectory(String path) {
        File tmpDir = new File(path);
        return tmpDir.isDirectory();
    }

    /**
     * Creates a directory.
     * @param path The path of the directory to create.
     * @param overwrite Setting to overwrite any existing directories at this location - Overwrites if true.
     * @return Returns true if the directory was created, false otherwise.
     */
    public static boolean createDirectory(String path, boolean overwrite) {
        if (overwrite) {
            File dir = new File(path);
            if (dir.exists()) {
                deleteDirectory(path);
            }
        }
        return new File(path).mkdirs();
    }

    /**
     * Deletes a directory.
     * @param path The path of the directory to delete.
     * @return Returns true if the directory was deleted, false otherwise.
     */
    public static boolean deleteDirectory(String path) {
        File index = new File(path);
        String[] entries = index.list();
        for (String s : entries){
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
        return index.delete();
    }

    /**
     * Lists directories in a filepath.
     * @param path The filepath to list the directories of.
     * @return Returns a String[] containing the paths of the directories.
     */
    public static String[] listDirectories(String path) {
        File file = new File(path);
        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
    }

    //TODO - UNTESTED
    public static long getDirectorySize(String path) {
        long length = 0;

        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                }
                else {
                    length += getDirectorySize(file.getPath());
                }
            }
        }
        return length;
    }

    //TODO - Search directories

}
