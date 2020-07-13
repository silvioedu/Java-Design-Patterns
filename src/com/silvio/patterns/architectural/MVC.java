package com.silvio.patterns.architectural;

class Student {
    private Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

class StudentView {
    public void printStudentDetails(String studentName, Integer id){
        System.out.println("Student ");
        System.out.println("  Name: " + studentName);
        System.out.println("  Id  : " + id);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) { model.setName(name); }
    public String getStudentName(){ return model.getName(); }
    public void setStudentId(Integer id){ model.setId(id); }
    public Integer getStudentId(){ return model.getId(); }

    public void updateView(){ view.printStudentDetails(model.getName(), model.getId()); }
}

class MVCExample {

    private static Student initialStudent(){
        Student student = new Student();
        student.setName("Silvio");
        student.setId(7);
        return student;
    }

    public static void main(String[] args) {
        Student model  = initialStudent();

        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        System.out.println("-> First view");
        controller.updateView();

        System.out.println("-> Updating name");
        controller.setStudentName("Eduardo");

        System.out.println("-> Second view");
        controller.updateView();
    }

}