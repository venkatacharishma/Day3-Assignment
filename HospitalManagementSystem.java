// Hospital Management System Application

import java.util.*;

// Patient class
class Patient {
    private String id;
    private String name;
    private int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

// Doctor class
class Doctor {
    private String id;
    private String name;
    private String specialty;

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialty: " + specialty;
    }
}

// Appointment class
class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patient.getName() + ", Doctor: " + doctor.getName() + ", Date: " + date;
    }
}

// Hospital Management System
public class HospitalManagementSystem {
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added: " + patient);
    }

    public static void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added: " + doctor);
    }

    public static void scheduleAppointment(String appointmentId, String patientId, String doctorId, String date) {
        try {
            Patient patient = patients.stream().filter(p -> p.getId().equals(patientId)).findFirst().orElseThrow(() -> new Exception("Patient not found"));
            Doctor doctor = doctors.stream().filter(d -> d.getId().equals(doctorId)).findFirst().orElseThrow(() -> new Exception("Doctor not found"));
            Appointment appointment = new Appointment(appointmentId, patient, doctor, date);
            appointments.add(appointment);
            System.out.println("Appointment scheduled: " + appointment);
        } catch (Exception e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Sample data
        Patient patient1 = new Patient("P001", "John Doe", 30);
        Patient patient2 = new Patient("P002", "Jane Smith", 40);

        Doctor doctor1 = new Doctor("D001", "Dr. Alice", "Cardiology");
        Doctor doctor2 = new Doctor("D002", "Dr. Bob", "Neurology");

        // Add patients and doctors
        addPatient(patient1);
        addPatient(patient2);

        addDoctor(doctor1);
        addDoctor(doctor2);

        // Schedule appointments
        scheduleAppointment("A001", "P001", "D001", "2025-01-27");
        scheduleAppointment("A002", "P002", "D002", "2025-01-28");

        // Display all appointments
        System.out.println("\nAll Appointments:");
        appointments.forEach(System.out::println);
    }
}