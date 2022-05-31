package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkNode extends PackageNode implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(WorkNode.class.getName());
    private List<PackageNode> children = new ArrayList<PackageNode>();

    /**
     * Constructor of a WorkNode (only use in-app)
     *
     * @param name        <i>The work's name from the WBS</i>
     * @param description <i>The work's description from the WBS</i>
     */
    public WorkNode(String name, String description) {
        super(name, description);
    }

    /**
     * @param name        <i>The work's name from the WBS</i>
     * @param description <i>The work's description from the WBS</i>
     * @param children    <i>The work package's children (whatever their type of
     *                    node might be)</i>
     */
    public WorkNode(String name, String description, List<PackageNode> children) {
        this(name, description);
        this.children = children;
    }

    public WorkNode searchParentChild(int id) {
        return null;
    }

    public void addNode(PackageNode node) {
        children.add(node);
    }
}
