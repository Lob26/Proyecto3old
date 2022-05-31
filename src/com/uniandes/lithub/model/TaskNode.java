package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskNode extends PackageNode implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(TaskNode.class.getName());
    private final String type;
    private long spentTime = 0L;
    private final long estimatedTime;
    private final DateTime estimatedEnd;
    private List<Participant> responsible = new ArrayList<Participant>();
    private List<Activity> associate = new ArrayList<Activity>();

    /**
     * Constructor of a Task Node (Only use in-app)
     *
     * @param name          <i>The task's name from the WBS</i>
     * @param description   <i>The task's description from the WBS</i>
     * @param type          <i>The type under the task control</i>
     * @param estimatedTime <i>The possible time to fulfill the task</i>
     * @param estimatedEnd  <i>The possible end date to fulfill the task</i>
     */
    public TaskNode(String name, String description, String type, long estimatedTime, DateTime estimatedEnd) {
        super(name, description);
        this.type = type;
        this.estimatedTime = estimatedTime;
        this.estimatedEnd = estimatedEnd;
    }

    /**
     * Constructor of a Task Node (Only use in the load)
     *
     * @param name          <i>The task's name from the WBS</i>
     * @param description   <i>The task's description from the WBS</i>
     * @param type          <i>The type under the task control</i>
     * @param spentTime     <i>The time spent under the task on-going</i>
     * @param estimatedTime <i>The possible time to fulfill the task</i>
     * @param estimatedEnd  <i>The possible end date to fulfill the task</i>
     * @param responsible   <i>The list of members responsible of the task</i>
     * @param associate     <i>The list of activities under the task</i>
     */
    public TaskNode(String name, String description, String type, long spentTime, long estimatedTime,
                    DateTime estimatedEnd, List<Participant> responsible, List<Activity> associate) {
        this(name, description, type, estimatedTime, estimatedEnd);
        this.spentTime = spentTime;
        this.responsible = responsible;
        this.associate = associate;
    }

    public boolean isDeletable() {
        return associate.isEmpty();
    }

}
