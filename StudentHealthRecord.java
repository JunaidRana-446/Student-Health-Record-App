import java.io.Serializable;

public class StudentHealthRecord implements Serializable {
    private String studentId;
    private String name;
    private int age;
    private String gender;
    private String bloodGroup;
    private String conditions;

    public StudentHealthRecord(String studentId, String name, int age,
            String gender, String bloodGroup, String conditions) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.conditions = conditions;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return studentId + " - " + name + " (" + bloodGroup + ")";
    }

    public String detailedRecord() {
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nBlood Group: " + bloodGroup +
                "\nConditions: " + conditions;
    }
}
