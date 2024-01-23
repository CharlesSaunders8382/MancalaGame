package mancala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Saver implements Serializable{
    private static final long serialVersionUID = -2804233256934305899L;
    /**
     * Saves the object into a file
     * @param toSave the object to save
     * @param filename the name of the file that is wanted
     */
    public static void saveObject(final Serializable toSave, final String filename) throws IOException{
        final Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        final String folderName = "assets";
        final Path assetsFolderPath = currentDirectory.resolve(folderName);
        if(!(Files.exists(assetsFolderPath) && Files.isDirectory(assetsFolderPath))){
            try{
                Files.createDirectories(assetsFolderPath);
            } catch (IOException e){
                throw new IOException("Error creating directory assets",e);
            }
        } 

        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("assets/" + filename))){
            objectOut.writeObject(toSave);
        } catch (IOException e){
            throw new IOException("Error saving file",e);
        }
    }
    /**
     * Loads an object from a file
     * @param filename the name of the file that the object is in
     * @return the object that was in the file.
     */
    public static Serializable loadObject(final String filename) throws IOException{
        Serializable object;
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("assets/" + filename))){
            object = (Serializable) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new IOException("Error loading object from file",e);
        }
        return object;
    }
}