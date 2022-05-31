package com.uniandes.lithub.controller;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.uniandes.lithub.model.Project;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public final class SavedProjectInfo {
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("dd-MM-yyyy");
    private final File folderRoute;
    private Map<String, Project> repositories = new HashMap<String, Project>();

    /**
     * Constructor used to create a SavedProjectInfo and save/load all data
     *
     * @param dirRoute <i>The route of the directory to read and write the object's data</i>
     * @throws IOException If the folder cannot be accessed by any way
     */
    public SavedProjectInfo(String dirRoute) throws IOException {
        this.folderRoute = new File(dirRoute);
        if (!folderRoute.exists())
            throw new IOException("La carpeta con la data ha sido borrada");
    }

    /**
     * Method used to load(read) all object's data from several .dat files
     *
     * @return The project's respective names
     * @throws IOException If the load process cannot be fulfilled
     */
    public String load() throws IOException {
        File[] fProj = folderRoute.listFiles();
        String fNames = "";

        if (fProj != null) {
            for (File projFile : fProj) {
                fNames += projFile.getName().replace(".dat", "") + "\n";

                try (InputStream is = new FileInputStream(projFile);
                     ObjectInput ois = new ObjectInputStream(is)) {

                    Project project = (Project) ois.readObject();
                    repositories.put(project.getName(), project);

                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error al leer los archivos");
                }
            }
        }
        return fNames;
    }

    /**
     * Method used to save(write) all object's data into several .dat files
     *
     * @throws IOException if the save process ended forcefully
     */
    public void save() throws IOException {
        for (Map.Entry<String, Project> data : repositories.entrySet()){

            File projFile = new File(folderRoute, data.getKey() + ".dat");
            try (OutputStream os = new FileOutputStream(projFile);
                 ObjectOutput ous = new ObjectOutputStream(os)){

                ous.writeObject(data.getValue());

            } catch (IOException e) {
                throw new IOException("Error al guardar los archivos");
            }
        }
    }

    /**
     * Generate a serial number of the respective class's name
     *
     * @param tName <i>The name of the class</i>
     * @return a serialized version of the class name
     */
    public static long genSerial(String tName) {
        byte[] bytes = tName.getBytes();
        return new BigInteger(bytes).longValue();
    }

    public void setRepositories(Map<String, Project> repositories) {
        this.repositories = repositories;
    }
    public Map<String, Project> getRepositories() {
        return repositories;
    }
}
