package com.uniandes.lithub.model;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

import com.uniandes.lithub.controller.SavedProjectInfo;

public abstract class PackageNode extends DefaultMutableTreeNode implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(PackageNode.class.getName());
    protected static int id = 0;
    protected final String name;
    protected final String description;
    protected boolean leaf;

    /**
     * Constructor of Activity (only use in-app)
     *
     * @param name        <i></i>
     * @param description <i></i>
     */
    public PackageNode(String name, String description) {
        this.name = name;
        this.description = description;
        id++;
    }

    /**
     * Constructor of Activity (only use in the load)
     *
     * @param name        <i></i>
     * @param description <i></i>
     * @param pid         <i></i>
     */
    public PackageNode(String name, String description, int pid) {
        this.name = name;
        this.description = description;
        id = pid;
    }
}
