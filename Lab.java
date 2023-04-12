import java.util.ArrayList;

public class Lab {
    public static void main(String[] args) {
        Laboratory laboratory = Laboratory.getLaboratory("Shabnam");
        Patient patient = new Patient(1190338157, 19);
        BloodType bloodType = new BloodType(patient);
        AIDS aids = new AIDS(patient);

        bloodType.runOperation();
        aids.runOperation();

        laboratory.getAllTests().add(bloodType);
        laboratory.getPatients().add(patient);
        laboratory.getAllTests().add(aids);

        ShowResults showResults=new ShowResults();
        showResults.copytTest=bloodType;
        bloodType.sendResult(showResults);

        ShowResults showResults1=new ShowResults();
        showResults1.copytTest=aids;
        aids.sendResult(showResults1);

        for (Test test : patient.getListOfTest()) {
            if (test instanceof Anemia) {
                System.out.println(((Anemia) test).makingPrivate());
                System.out.println();
            }
            if (test instanceof AIDS) {
                System.out.println(((AIDS) test).makingPrivate());
                System.out.println();
            }
            if (test instanceof Thyroid) {
                System.out.println(((Thyroid) test).makingPrivate());
                System.out.println();
            }
            if(test instanceof BloodType){
                System.out.println(((BloodType) test).toString());
            }
        }
        System.out.println(patient.request(aids));
        System.out.println();
        for(Test test: Laboratory.getLaboratory().getStatisticalTests()){
            System.out.println(test.toString());
            System.out.println();
        }


    }
}

class Patient {
    private int id;
    private String preIllness = null;
    private int age;
    private ArrayList<Test> listOfTest = new ArrayList<>();
    private Request request;

    Patient(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return this.request;
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
        return "ID: " + this.getId() + ", Age: " + this.getAge() + ", PreIllness: " + this.getPreIllness();
    }

    public String request(Test test) {
        Request request = new Request(this);
        int check=0;
        for (Request req : Laboratory.getLaboratory().getRequests()) {
            check=request.compareTo(req);
        }
        if(check==0){
            if (test instanceof AIDS) {
                return ((AIDS) test).gettingTheRequests();
            }
            if (test instanceof Anemia) {
                return ((Anemia) test).gettingTheRequests();
            }
            if (test instanceof Thyroid) {
                return ((Thyroid) test).gettingTheRequests();
            }
        }
        return null;
    }
}


class Laboratory {
    private String name;
    private ArrayList<Test> allTests = new ArrayList<>();
    private ArrayList<Test> statisticalTests = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
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

    static Laboratory getLaboratory() {
        return laboratory;
    }

    public ArrayList<Request> getRequests() {
        return requests;
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


abstract class Test extends ShowResults {
    private Patient patient;
    private boolean confirmByPatient;
    private static int code = 0;
    private int CBC;
    private int BMP;
    private int bloodPressure;

    Test(Patient patient) {
        this.patient = patient;
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

    public String privateToString() {
        return patient.toString() + ", Confirm by patient: " + this.confirmByPatient + ", Blood Pressure: " + this.getBloodPressure();
    }

    public String toString() {
        return "Code: " + getCode() + " ," + patient.toString() + ", Confirm by patient: " + this.confirmByPatient + ", CBC: " + this.getCBC() + ", BMP: " + this.getBMP() + ", Blood Pressure: " + this.getBloodPressure();
    }

    public Test sendResult(ShowResults showResults1) {
        ShowResults showResult2 = new ShowResults();
        showResult2 = (ShowResults) showResults1.clone();
        this.getPatient().getListOfTest().add(showResult2.copytTest);
        return showResult2.copytTest;
    }

    public abstract void runOperation();
}


class BloodType extends Test implements iCheckTest {
    private boolean confirmByLab;
    int counter = 0;

    BloodType(Patient patient) {
        super(patient);
    }

    public void setConfirmByLab(boolean confirmByLab) {
        this.confirmByLab = confirmByLab;
    }

    public boolean getConfirmByLab() {
        return this.confirmByLab;
    }

    @Override
    public String toString() {
        return super.toString() + " , Confirm by laboratory: " + this.getConfirmByLab();
    }

    @Override
    public void runOperation() {
        int randomCBC = (int) ((Math.random() * 4) + 1);
        if (randomCBC == 1) {
            this.setCBC((int) ((Math.random() * 18) + 1));
        } else {
            this.setCBC((int) ((Math.random() * 140) + 20));
        }

        int randomBMP = (int) ((Math.random() * 4) + 1);
        if (randomBMP == 1) {
            this.setBMP((int) ((Math.random() * 33) + 1));
        } else {
            this.setBMP((int) ((Math.random() * 175) + 35));
        }

        int randomBloodPresure = (int) ((Math.random() * 4) + 1);
        if (randomBloodPresure == 1) {
            this.setBloodPressure((int) ((Math.random() * 2) + 1));
        } else {
            this.setBloodPressure((int) ((Math.random() * 96) + 4));
        }
        counter++;
        this.checking();
    }

    @Override
    public void checking() {
        BloodTypeRange cbc = BloodTypeRange.CBC;
        BloodTypeRange bmp = BloodTypeRange.BMP;
        BloodTypeRange bloodPresure = BloodTypeRange.BLOOD_PRESSURE;
        if (cbc.getRange_one() <= this.getCBC() && cbc.getRange_two() >= this.getCBC() && bmp.getRange_one() <= this.getBMP() && bmp.getRange_two() >= this.getBMP() &&
                bloodPresure.getRange_one() <= this.getBloodPressure() && bloodPresure.getRange_two() >= this.getBloodPressure()) {
            this.confirmByLab = true;
            Laboratory.getLaboratory().getStatisticalTests().add(this);
        } else {
            this.wrongAnswer();
        }
    }

    @Override
    public void wrongAnswer() {
        if (counter < 2) {
            this.runOperation();
        } else {
            confirmByLab = false;
        }
    }
}

class AIDS extends Test implements iCheckTest, iPrivate {
    private boolean confirmByLab;
    int counter = 0;

    private String heartIll;

    AIDS(Patient patient) {
        super(patient);
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
        return super.toString() + " , Confirm by laboratory : " + this.isConfirmByLab() + " , Heart illness: " + this.getHeartIll();
    }

    @Override
    public void runOperation() {
        int randomCBC = (int) ((Math.random() * 4) + 1);
        if (randomCBC == 1) {
            this.setCBC((int) ((Math.random() * 18) + 1));
        } else {
            this.setCBC((int) ((Math.random() * 160) + 20));
        }

        int randomBMP = (int) ((Math.random() * 4) + 1);
        if (randomBMP == 1) {
            this.setBMP((int) ((Math.random() * 33) + 1));
        } else {
            this.setBMP((int) ((Math.random() * 305) + 35));
        }

        int randomBloodPresure = (int) ((Math.random() * 4) + 1);
        if (randomBloodPresure == 1) {
            this.setBloodPressure((int) ((Math.random() * 2) + 1));
        } else {
            this.setBloodPressure((int) ((Math.random() * 146) + 4));
        }

        String[] heartIll = {"HEALTHY", "CORONARY", "STROKE", "PERIPHERALARTERIAL", "AORTIC", "NOT", "ERROR", "FINDFAILD"};

        int randomHeartIll = (int) ((Math.random() * 7) + 0);
        if (randomHeartIll < 5) {
            this.setHeartIll(heartIll[randomHeartIll]);
        }
        counter++;
        this.checking();
    }

    @Override
    public void checking() {
        AIDSRange cbc = AIDSRange.CBC;
        AIDSRange bmp = AIDSRange.BMP;
        AIDSRange bloodPresure = AIDSRange.BLOOD_PRESSURE;
        if (cbc.getRange_one() <= this.getCBC() && cbc.getRange_two() >= this.getCBC() && bmp.getRange_one() <= this.getBMP() && bmp.getRange_two() >= this.getBMP() &&
                bloodPresure.getRange_one() <= this.getBloodPressure() && bloodPresure.getRange_two() >= this.getBloodPressure()) {
            this.confirmByLab = true;
            Laboratory.getLaboratory().getStatisticalTests().add(this);
        } else {
            this.wrongAnswer();
        }
    }

    @Override
    public void wrongAnswer() {
        if (counter < 3) {
            this.runOperation();
        } else {
            confirmByLab = false;
        }
    }

    @Override
    public String makingPrivate() {

        return this.privateToString();
    }

    @Override
    public String gettingTheRequests() {
        if (this.getPatient().getPreIllness() == null && this.getPatient().getAge() > 18) {
            return this.toString();
        }
        return null;
    }
}


class Thyroid extends Test implements iPrivate {
    private int heartPressure;

    public Thyroid(Patient patient) {
        super(patient);
    }

    public void setHeartPressure(int heartPressure) {
        this.heartPressure = heartPressure;
    }

    public int getHeartPressure() {
        return this.heartPressure;
    }

    public String toString() {
        return super.toString() + " , pre pressure: " + this.getHeartPressure();
    }

    @Override
    public void runOperation() {
        int random = (int) ((Math.random() * 4) + 3);
        int factorial = (int) factorial(random);
        this.setCBC(factorial * 10);
        this.setBMP(this.getCBC() - random);
        this.setBloodPressure(getCBC() / getBMP());
        this.setHeartPressure(random);
        Laboratory.getLaboratory().getStatisticalTests().add(this);
    }

    private double factorial(double random) {
        double fact = 1;
        for (int i = 1; i < random; i++) {
            fact *= i;
        }
        return fact;
    }

    @Override
    public String makingPrivate() {

        return this.privateToString();
    }

    @Override
    public String gettingTheRequests() {
        if (this.getPatient().getPreIllness() == null) {
            return this.toString();
        }
        return null;
    }
}


class Anemia extends Test implements iPrivate {
    public Anemia(Patient patient) {
        super(patient);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void runOperation() {
        int random = (int) ((Math.random() * 49) + 1);
        this.setCBC((int) Math.sqrt(random));
        this.setBMP(this.getCBC() + 2);
        this.setBloodPressure(getCBC() / getBMP());
        Laboratory.getLaboratory().getStatisticalTests().add(this);

    }

    @Override
    public String makingPrivate() {

        return this.privateToString();
    }

    @Override
    public String gettingTheRequests() {
        if (this.getPatient().getAge() > 18) {
            return this.toString();
        }
        return null;
    }
}

class ShowResults implements Cloneable {
    Test copytTest;

    public Object clone() {
        ShowResults showResults = new ShowResults();
        showResults.copytTest = this.copytTest;
        return showResults;
    }
}

interface iCheckTest {
    public void checking();

    public void wrongAnswer();
}

enum AIDSRange {
    CBC(20, 180),
    BLOOD_PRESSURE(4, 150),
    BMP(35, 340);

    private enum HeartDisease {
        HEALTHY,
        CORONARY,
        STROKE,
        PERIPHERAL_ARTERIAL,
        AORTIC
    }

    private int range_one;
    private int range_two;

    AIDSRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }

    public int getRange_one() {
        return range_one;
    }

    public int getRange_two() {
        return range_two;
    }
}

enum BloodTypeRange {
    CBC(20, 160),
    BLOOD_PRESSURE(4, 100),
    BMP(35, 210);

    private int range_one;
    private int range_two;

    BloodTypeRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }

    public int getRange_one() {
        return range_one;
    }

    public int getRange_two() {
        return range_two;
    }

}

interface iPrivate {
    public String makingPrivate();

    public String gettingTheRequests();
}

class Request implements Comparable<Request> {
    private Patient patient;

    Request(Patient patient) {
        this.patient = patient;
    }

    public String toString() {
        return "ID: " + this.patient.getId();
    }

    public Patient getPatient() {
        return this.patient;
    }

    @Override
    public int compareTo(Request request) {
        if (!Laboratory.getLaboratory().getRequests().contains(request)) {
            Laboratory.getLaboratory().getRequests().add(request);
            return 0;
        }
        return -1;
    }
}