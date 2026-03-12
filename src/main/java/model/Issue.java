package model;

public class Issue {

    private int id;
    private String title;
    private String description;
    private String location;
    private String department;
    private String status;
    private int studentId;

    public Issue(){}

    public Issue(String title,String description,String location,String department,int studentId){

        this.title=title;
        this.description=description;
        this.location=location;
        this.department=department;
        this.studentId=studentId;
        this.status="Pending";
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public int getStudentId(){
        return studentId;
    }

    public void setStudentId(int studentId){
        this.studentId=studentId;
    }
}