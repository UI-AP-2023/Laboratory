import java.util.ArrayList;

public class Lab {
    public static void main(String[] args) {

    }
}

class Patient {
    private int id;
    private String preIllness;
    private int age;
    private ArrayList<Test> listOfTest = new ArrayList<>();

    Patient(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public void setPreIllness(String preIllness) {
        this.preIllness = preIllness;
    }

    public int getId() {
        return this.id;
    }

    public String getPreIllness() {
        return this.preIllness;
    }

    public int getAge() {
        return this.age;
    }

    public ArrayList<Test> getListOfTest() {
        return this.listOfTest;
    }
    public String toString() {
        return "ID: " + this.getId() + ", Age: " +this.getAge() + ", PreIllness: " + this.getPreIllness();
    }
}

class Laboratory {
    private String name;
    private ArrayList<Test> allTests = new ArrayList<>();
    private ArrayList<Test> statisticalTests = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private static Laboratory laboratory;

    private Laboratory(String name) {
        this.name = name;
    }

    static Laboratory getLaboratory(String name) {
        if (laboratory == null) {
            laboratory = new Laboratory(name);
        }
        return laboratory;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public ArrayList<Test> getAllTests() {
        return this.allTests;
    }

    public ArrayList<Test> getStatisticalTests() {
        return this.statisticalTests;
    }
    public String toString() {
        return "Name: " + this.getName();
    }
}

abstract class Test {
    private Patient patient;
    private boolean confirmByPatient;
    private static int code = 0;
    private int CBC;
    private int BMP;
    private int bloodPressure;

    Test(Patient patient,int CBC,int BMP,int bloodPressure) {
        this.bloodPressure=bloodPressure;
        this.BMP=BMP;
        this.CBC=CBC;
        this.patient=patient;
        code++;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setConfirmByPatient(boolean confirmByPatient) {
        this.confirmByPatient = confirmByPatient;
    }

    public boolean isConfirmByPatient() {
        return this.confirmByPatient;
    }

    public void setCBC(int CBC) {
        this.CBC = CBC;
    }

    public int getCBC() {
        return this.CBC;
    }

    public void setBMP(int BMP) {
        this.BMP = BMP;
    }

    public int getBMP() {
        return this.BMP;
    }

    public static int getCode() {
        return code;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getBloodPressure() {
        return this.bloodPressure;
    }
    public String toString() {
        return "Code: "+getCode()+" ,"+patient.toString()+ ", Confirm by patient: " +this.confirmByPatient+ ", CBC: "+this.getCBC()+", BMP: "+this.getBMP()+", Blood Pressure: "+this.getBloodPressure();
    }
}
abstract class BloodType extends Test{
    private boolean confirmByLab;
    BloodType(Patient patient, int CBC, int BMP, int bloodPressure) {
        super(patient, CBC, BMP, bloodPressure);
    }

    public void setConfirmByLab(boolean confirmByLab) {
        this.confirmByLab = confirmByLab;
    }
    public boolean getConfirmByLab() {
        return this.confirmByLab;
    }

    @Override
    public String toString() {
        return super.toString()+" , Confirm by laboratory"+this.getConfirmByLab();
    }
}
class AIDS extends  Test{
    private boolean confirmByLab;
    private String heartIll;
    AIDS(Patient patient, int CBC, int BMP, int bloodPressure) {
        super(patient, CBC, BMP, bloodPressure);
    }
    public void setConfirmByLab(boolean confirmByLab) {
        this.confirmByLab = confirmByLab;
    }

    public void setHeartIll(String heartIll) {
        this.heartIll = heartIll;
    }

    public boolean isConfirmByLab() {
        return this.confirmByLab;
    }

    public String getHeartIll() {
        return this.heartIll;
    }
    @Override
    public String toString() {
        return super.toString()+" , Confirm by laboratory : "+this.isConfirmByLab()+" , Heart illness: "+this.getHeartIll();
    }
}

class Thyroid extends Test{
    private int prePressure;
    public Thyroid(Patient patient, int CBC, int BMP, int bloodPressure) {
        super(patient, CBC, BMP, bloodPressure);
    }

    public void setPrePressure(int prePressure) {
        this.prePressure = prePressure;
    }

    public int getPrePressure() {
        return this.prePressure;
    }
    public String toString() {
        return super.toString()+" , pre pressure: "+this.getPrePressure();
    }
}
class Anemia extends Test{
    public Anemia(Patient patient, int CBC, int BMP, int bloodPressure) {
        super(patient, CBC, BMP, bloodPressure);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
