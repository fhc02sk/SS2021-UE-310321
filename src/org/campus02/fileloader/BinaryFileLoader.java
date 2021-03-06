package org.campus02.fileloader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryFileLoader extends GenericFileLoader implements Runnable {

    private ArrayList<Integer> bytes;// = new ArrayList<>();

    public BinaryFileLoader(String path) {
        super(path);
        bytes = new ArrayList<>();
    }

    @Override
    public void loadFile() throws GenericFileLoadException {

        try(BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(path))){

            int byteRead;
            while ((byteRead = bis.read()) != -1){
                incrementCountBytes(1);
                bytes.add(byteRead);
            }
            //bis.close();
        } catch (IOException e) {
            throw new GenericFileLoadException("Fehler beim Laden von: " + path, e);
        }
    }

    public ArrayList<Integer> getBytes() {
        return bytes;
    }

    @Override
    public void run() {
        try {
            loadFile();
        } catch (GenericFileLoadException e) {
            e.printStackTrace();
        }
    }
}
