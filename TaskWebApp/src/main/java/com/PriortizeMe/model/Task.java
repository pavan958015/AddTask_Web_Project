package com.PriortizeMe.model;

public class Task {
    private int id;
    private int userId;
    private String taskName;
    private String priority;

    // Getters and Setters
    public int getId() { 
    	return id; 
    }
    public void setId(int id){
    	this.id = id; 
    }
    public int getUserId() { 
    	return userId; 
   	}
    public void setUserId(int userId) {
    	this.userId = userId; 
   	}
    public String getTaskName() { 
    	return taskName; 
   	}
    public void setTaskName(String taskName) { 
    	this.taskName = taskName; 
    }

    public String getPriority() { 
    	return priority; 
    }
    public void setPriority(String priority) { 
    	this.priority = priority; 
    }
}
