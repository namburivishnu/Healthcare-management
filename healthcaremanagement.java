import java.util.Scanner;

class Date {
    int day;
    int month;
    int year;
}

class OPPatient {
    String name;
    Date dob;
    char gender;
    String contact;
}

class Doctor {
    String name;
    String specialization;
    int maxAppointmentsPerDay;
}

class SurgeryPatient {
    String name;
    int age;
    char gender;
    Date joiningDate;
    int opBedNumber;
}

class Appointment {
    OPPatient patient;
    Doctor doctor;
}

class Billing {
    OPPatient patient;
    float amount;
}

public class HospitalManagementSystem {
    static final int MAX_PATIENTS = 100;
    static final int MAX_DOCTORS = 20;
    static final int MAX_APPOINTMENTS = 200;
    static final int MAX_SURGERY_PATIENTS = 100;

    static OPPatient[] patients = new OPPatient[MAX_PATIENTS];
    static int patientCount = 0;

    static Doctor[] doctors = new Doctor[MAX_DOCTORS];
    static int doctorCount = 0;

    static SurgeryPatient[] surgeryPatients = new SurgeryPatient[MAX_SURGERY_PATIENTS];
    static int surgeryPatientCount = 0;

    static Appointment[] appointments = new Appointment[MAX_APPOINTMENTS];
    static int appointmentCount = 0;

    static Billing[] bills = new Billing[MAX_PATIENTS];
    static int billCount = 0;

    static Scanner scanner = new Scanner(System.in);

    static void addOPPatient() {
        if (patientCount >= MAX_PATIENTS) {
            System.out.println("Maximum patient limit reached.");
            return;
        }

        OPPatient patient = new OPPatient();
        System.out.print("Enter patient name: ");
        patient.name = scanner.next();
        System.out.print("Enter date of birth (DD MM YYYY): ");
        patient.dob = new Date();
        patient.dob.day = scanner.nextInt();
        patient.dob.month = scanner.nextInt();
        patient.dob.year = scanner.nextInt();
        System.out.print("Enter gender (M/F): ");
        patient.gender = scanner.next().charAt(0);
        System.out.print("Enter contact number: ");
        patient.contact = scanner.next();

        patients[patientCount++] = patient;
        System.out.println("OPPatient added successfully.");
    }

    static void patientList() {
        System.out.println("OPPatients:");
        for (int i = 0; i < patientCount; ++i) {
            System.out.printf("Name: %s | DOB: %02d-%02d-%04d | Gender: %c | Contact: %s\n",
                    patients[i].name, patients[i].dob.day, patients[i].dob.month, patients[i].dob.year,
                    patients[i].gender, patients[i].contact);
        }
    }

    static void addDoctor() {
        if (doctorCount >= MAX_DOCTORS) {
            System.out.println("Maximum doctor limit reached.");
            return;
        }

        Doctor doctor = new Doctor();
        System.out.print("Enter doctor name: ");
        doctor.name = scanner.next();
        System.out.print("Enter specialization: ");
        doctor.specialization = scanner.next();
        System.out.print("Enter maximum appointments per day: ");
        doctor.maxAppointmentsPerDay = scanner.nextInt();

        doctors[doctorCount++] = doctor;
        System.out.println("Doctor added successfully.");
    }

    static void showDoctors() {
        System.out.println("Doctors:");
        for (int i = 0; i < doctorCount; ++i) {
            System.out.printf("Doctor: %s | Specialization: %s | Max Appointments: %d\n",
                    doctors[i].name, doctors[i].specialization, doctors[i].maxAppointmentsPerDay);
        }
    }

    static void addsurgeryPatient() {
        if (surgeryPatientCount >= MAX_SURGERY_PATIENTS) {
            System.out.println("Maximum surgery patient limit reached.");
            return;
        }

        SurgeryPatient patient = new SurgeryPatient();
        System.out.print("Enter surgery patient name: ");
        patient.name = scanner.next();
        System.out.print("Enter the age: ");
        patient.age = scanner.nextInt();
        System.out.print("Enter gender(M/F): ");
        patient.gender = scanner.next().charAt(0);
        System.out.print("Enter date of joining (DD MM YYYY): ");
        patient.joiningDate = new Date();
        patient.joiningDate.day = scanner.nextInt();
        patient.joiningDate.month = scanner.nextInt();
        patient.joiningDate.year = scanner.nextInt();
        System.out.print("Enter the OP bed number: ");
        patient.opBedNumber = scanner.nextInt();

        surgeryPatients[surgeryPatientCount++] = patient;
        System.out.println("Surgery patient added successfully.");
    }

    static void surgeryPatientList() {
        System.out.println("Surgery Patients:");
        for (int i = 0; i < surgeryPatientCount; ++i) {
            System.out.printf("Name: %s | Age: %d | Gender: %c | Date of Joining: %02d-%02d-%04d | OP Bed Number: %d\n",
                    surgeryPatients[i].name, surgeryPatients[i].age, surgeryPatients[i].gender,
                    surgeryPatients[i].joiningDate.day, surgeryPatients[i].joiningDate.month,
                    surgeryPatients[i].joiningDate.year, surgeryPatients[i].opBedNumber);
        }
    }

    static void scheduleAppointment() {
        if (appointmentCount >= MAX_APPOINTMENTS) {
            System.out.println("Maximum appointment limit reached.");
            return;
        }

        Appointment appointment = new Appointment();
        System.out.print("Enter patient name: ");
        String patientName = scanner.next();

        for (OPPatient patient : patients) {
            if (patient != null && patient.name.equals(patientName)) {
                appointment.patient = patient;
                break;
            }
        }

        System.out.print("Enter doctor name: ");
        String doctorName = scanner.next();

        for (Doctor doctor : doctors) {
            if (doctor != null && doctor.name.equals(doctorName)) {
                appointment.doctor = doctor;
                break;
            }
        }

        appointments[appointmentCount++] = appointment;
        System.out.println("Appointment scheduled successfully.");
    }

    static void showAppointments() {
        System.out.println("Appointments:");
        for (Appointment appointment : appointments) {
            if (appointment != null && appointment.patient != null) {
                System.out.println("Patient: " + appointment.patient.name + " | Doctor: " + appointment.doctor.name);
            }
        }
    }

    static void generateBill() {
        System.out.print("Enter patient name: ");
        String patientName = scanner.next();

        for (OPPatient patient : patients) {
            if (patient != null && patient.name.equals(patientName)) {
                System.out.print("Enter amount: ");
                float amount = scanner.nextFloat();
                bills[billCount] = new Billing();
                bills[billCount].patient = patient;
                bills[billCount].amount = amount;
                System.out.println("Bill generated successfully.");
                billCount++;
                return;
            }
        }

        System.out.println("Patient not found.");
    }

    static void showBills() {
        System.out.println("Bills:");
        for (Billing bill : bills) {
            if (bill != null && bill.patient != null) {
                System.out.println("Patient: " + bill.patient.name + " | Amount: " + bill.amount);
            }
        }
    }

    static void statistics() {
        int maleCount = 0;
        int femaleCount = 0;

        for (OPPatient patient : patients) {
            if (patient != null) {
                if (patient.gender == 'M' || patient.gender == 'm') {
                    maleCount++;
                } else {
                    femaleCount++;
                }
            }
        }

        System.out.println("Statistics:");
        System.out.println("Total Patients: " + patientCount);
        System.out.println("Male Patients: " + maleCount);
        System.out.println("Female Patients: " + femaleCount);
    }

    static void showMenu() {
        System.out.println("\nHospital Management System");
        System.out.println("1. Add OPPatient");
        System.out.println("2. Add Doctor");
        System.out.println("3. Schedule Appointment for OPpatient");
        System.out.println("4. OPpatient list");
        System.out.println("5. Show Doctors");
        System.out.println("6. Show OPpatient Appointments");
        System.out.println("7. Generate Bill");
        System.out.println("8. Show Bills");
        System.out.println("9. OPpatient Statistics");
        System.out.println("10. Add Surgery Patient");
        System.out.println("11. Surgery Patient List");
        System.out.println("12. Exit\n");

        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        int choice;

        while (true) {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addOPPatient();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    scheduleAppointment();
                    break;
                case 4:
                    patientList();
                    break;
                case 5:
                    showDoctors();
                    break;
                case 6:
                    showAppointments();
                    break;
                case 7:
                    generateBill();
                    break;
                case 8:
                    showBills();
                    break;
                case 9:
                    statistics();
                    break;
                case 10:
                    addsurgeryPatient();
                    break;
                case 11:
                    surgeryPatientList();
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
