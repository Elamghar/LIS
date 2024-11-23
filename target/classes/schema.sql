#
# #
# # -- Drop tables if they already exist Ola Ms7o hadchi odik sa3a ayb9aw tables
# DROP TABLE IF EXISTS Test;
# DROP TABLE IF EXISTS Visit;
# DROP TABLE IF EXISTS MedicalFile;
# DROP TABLE IF EXISTS Patient;
# DROP TABLE IF EXISTS Admin;
# #
# # -- Table: Patient
#
# CREATE TABLE Admin (
#                          patientId INT AUTO_INCREMENT PRIMARY KEY,
#                          name VARCHAR(100) NOT NULL,
#                          prenom VARCHAR(100),
#                          date_ns DATE NOT NULL,
#                          gender ENUM('Male', 'Female') NOT NULL,
#                          login VARCHAR(50) NOT NULL UNIQUE ,
#                          passwd VARCHAR(50) NOT NULL
# );
#
# CREATE TABLE Patient (
#                          patientId VARCHAR(50)  PRIMARY KEY,   -- Auto-incrementing primary key for patientId
#                          firstName VARCHAR(100) NOT NULL,             -- First name of the patient
#                          lastName VARCHAR(100) NOT NULL,              -- Last name of the patient
#                          age INT NOT NULL,                            -- Age of the patient
#                          gender ENUM('Male', 'Female') NOT NULL,      -- Gender of the patient (Male or Female)
#                          email VARCHAR(255) NOT NULL,                 -- Email of the patient
#                          address VARCHAR(255) NOT NULL,               -- Address of the patient
#                          role VARCHAR(50) NOT NULL,                   -- Role of the patient (e.g., Doctor, Nurse, etc.)
#                          phoneNumber VARCHAR(20) NOT NULL             -- Phone number of the patient
# );
# # -- Table: MedicalFile
# CREATE TABLE MedicalFile (
#                              id INT AUTO_INCREMENT PRIMARY KEY,
#                              dateCreation DATE NOT NULL,
#                              dateModif DATE,
#                              patientId VARCHAR(50) NOT NULL,
#                              FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
# );
#
# # -- Table: Appointment (Visit)
# CREATE TABLE Visit (
#                              visitId INT AUTO_INCREMENT PRIMARY KEY,
#                              visitDate DATE NOT NULL,
#                              diagnostic TEXT,
#                              traitement TEXT,
#                              patientId VARCHAR(50) NOT NULL,
#                              FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
# );
#
#
# # -- Table: Test
# CREATE TABLE Test (
#                       testId INT AUTO_INCREMENT PRIMARY KEY,
#                       testName VARCHAR(100) NOT NULL,
#                       dateTest DATE NOT NULL,
#                       testResult TEXT,
#                       price DECIMAL(10, 2),
#                       category VARCHAR(50),
#                       appointmentId INT NOT NULL,
#                       FOREIGN KEY (appointmentId) REFERENCES Visit(visitId) ON DELETE CASCADE
# );
