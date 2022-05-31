package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;
import com.uniandes.lithub.exceptions.WBSException;

import java.io.Serializable;

public class WBStruct implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(WBStruct.class.getName());
    private final WorkNode root;

    public WBStruct(String rootName, String rootDesc) {
        this.root = new WorkNode(rootName, rootDesc);
    }

    public void addNode(boolean isTask, int id, String[] nodeData) throws WBSException {
        WorkNode parentChild = root.searchParentChild(id);
        PackageNode pn = isTask
                ? new TaskNode(nodeData[0], nodeData[1], nodeData[2], Long.parseLong(nodeData[3]),
                SavedProjectInfo.DTF.parseDateTime(nodeData[4]))
                : new WorkNode(nodeData[0], nodeData[1]);

        if (parentChild == null)
            throw new WBSException("No se puede aniadir el nodo a la WBS porque se esta aniadiendo a un nodo de "
                    + "tarea en lugar de uno de trabajo");

        parentChild.addNode(pn);
    }
}
