package dropbox;

import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class FindDuplicateFiles {
    public static void main(String[] args) {
        Byte b = (byte)0b11;
        System.out.println(b);
        int a = b<<1;
        System.out.println(a);

        /////////////////////////////

        String filePath = "src/resources/md5file1.txt";
        String filePath2 = "./src/resources/md5file2.txt";
        String filePath3 = ".";

        System.out.println("\nhow to indicate if a path is directory or file");
        File file = new File(filePath);
        File file2 = new File(filePath2);
        File file3 = new File(filePath3);
        System.out.println("isFile: " + file.isFile());
        System.out.println("isFile: " + file2.isFile());
        System.out.print("isFile: " + file3.isFile() + " ,"); System.out.println("isDirectory: " + file3.isDirectory());

        System.out.println("\nhow to get all files/folders in cur path");
        File[] fileList = file3.listFiles();
        if (fileList != null) {
            for (File f : fileList) {
                if (f.isFile()) {
                    System.out.println(f.getAbsolutePath() + " is file and size is " + f.length() + " bytes");
                }
                if (f.isDirectory()) {
                    System.out.print(f.getName() + " is directory");
                    if (f.listFiles() != null && f.listFiles().length > 0) {
                        System.out.print(", this directory is not empty.");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println();


        // get all files under a path and their md5 checksum
        System.out.println("\nhow to get all files/folders in recursively path");
        File rootFolder = new File("./");
        List<File> allTheFiles = new ArrayList<>();

        getAllFiles(rootFolder, allTheFiles);
        System.out.println("number of files under current folder: " + allTheFiles.size());

        for (File pathStr : allTheFiles) {
//            System.out.println(getMD5OfFile(pathStr.getPath()) + " " + pathStr);
        }

        String md5Str = getMD5OfFile(filePath);

        List<List<String>> listOfDuplicateFiles = findDuplicateFiles(filePath3);
        System.out.println("\nDuplicate files under current directory:");
        for (List<String> dupFiles : listOfDuplicateFiles) {
            for (String filepath : dupFiles) {
                System.out.print(filepath + " ");
            }
            System.out.println();
        }


        // Three ways to read a file
        // BufferedReader
        System.out.println();
        System.out.println("1. bufferReader.readLine(FileReader)");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String curLine = null;
            while ((curLine = br.readLine()) != null) {
                System.out.println(curLine);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files.readAllLines, outOfMemory if too big
        System.out.println("2. Files.readAllLines(Path)");
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Java 8 stream to avoid outOfMemory
        System.out.println("3. Files.lines(Path)");
        try {
            Files.lines(Paths.get(filePath)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<List<String>> findDuplicateFiles(String filePath) {

        // key -> size; value -> list of files with same size
        Map<Long, List<File>> sizeMap = new HashMap<>();
        List<File> allTheFiles = new ArrayList<>();
        getAllFiles(new File(filePath), allTheFiles);
        for (File file: allTheFiles) {
            if (sizeMap.containsKey(file.length())) {
                sizeMap.get(file.length()).add(file);
            } else {
                List<File> newList = new ArrayList<>();
                newList.add(file);
                sizeMap.put(file.length(), newList);
            }
        }

        // key -> hashcode of file; value -> list of files with same hashcode
        Map<String, List<File>> hashMap = new HashMap<>();

        Iterator<Map.Entry<Long, List<File>>> it = sizeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, List<File>> entry = it.next();
            if (entry.getValue().size() <= 1) {
                // remove entries that only has single values, which not possible to duplicate
                it.remove();
            } else {
                // update hashMap
                for (File file : entry.getValue()) {
                    String hashcode = getMD5OfFile(file.getPath());
                    if (hashMap.containsKey(hashcode)) {
                        hashMap.get(hashcode).add(file);
                    } else {
                        List<File> fileList = new ArrayList<>();
                        fileList.add(file);
                        hashMap.put(hashcode, fileList);
                    }
                }
            }
        }

        // filter out non-duplicate files
        List<List<String>> result = new ArrayList<>();
        for (String hashcode : hashMap.keySet()) {
            if (hashMap.get(hashcode).size() > 1) {
                List<String> files = new ArrayList<>();
                for (File file : hashMap.get(hashcode)) {
                    files.add(file.getPath());
                }
                result.add(files);
            }
        }

        return result;
    }

    private static void getAllFiles(File folder, List<File> allTheFiles) {
        if (folder.listFiles() == null) {
            return;
        }
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                allTheFiles.add(file);
            } else {
                getAllFiles(file, allTheFiles);
            }
        }
    }

    private static String getMD5OfFile(String filePath) {
        try {
            /* Method 1: read all content of file at one time -- start */
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5ByteArr = messageDigest.digest(Files.readAllBytes(Paths.get(filePath)));
            /* Method 1: read all content of file at one time -- end */


            /* Method 2: read certain number of bytes from file at one time -- start */
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
//
//            byte[] buffer = new byte[1024]; // 1kB at a time
//            int numRead = inputStream.read(buffer);
//            while (numRead != -1) {
//                messageDigest.update(buffer, 0, numRead);
//                numRead = inputStream.read(buffer);
//            }
//            inputStream.close();
//            byte[] md5ByteArr = messageDigest.digest();
            /* Method 2: read certain number of bytes from file at one time -- end */


            String md5Str = "";
            for (int i=0; i < md5ByteArr.length; i++) {
                md5Str += Integer.toString( ( md5ByteArr[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return md5Str;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
