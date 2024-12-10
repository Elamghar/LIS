

-- Drop tables if they already exist Ola Ms7o hadchi odik sa3a ayb9aw tables
DROP TABLE IF EXISTS Test;
DROP TABLE IF EXISTS Visit;
DROP TABLE IF EXISTS MedicalFile;
DROP TABLE IF EXISTS Patient;

-- Table: Patient

CREATE TABLE Patient (
                         patientId INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100),
                         date_ns DATE NOT NULL,
                         gender ENUM('Male', 'Female') NOT NULL,
                         login VARCHAR(50) NOT NULL UNIQUE ,
                         passwd VARCHAR(50) NOT NULL
);


-- Table: MedicalFile
CREATE TABLE MedicalFile (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             dateCreation DATE NOT NULL,
                             dateModif DATE,
                             patientId INT NOT NULL,
                             FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
);

-- Table: Appointment (Visit)
CREATE TABLE Visit (
                             visitId INT AUTO_INCREMENT PRIMARY KEY,
                             visitDate DATE NOT NULL,
                             diagnostic TEXT,
                             traitement TEXT,
                             patientId INT NOT NULL,
                             FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
);


-- Table: Test
CREATE TABLE Test (
                      testId INT AUTO_INCREMENT PRIMARY KEY,
                      testName VARCHAR(100) NOT NULL,
                      dateTest DATE NOT NULL,
                      testResult TEXT,
                      price DECIMAL(10, 2),
                      category VARCHAR(50),
                      appointmentId INT NOT NULL,
                      FOREIGN KEY (appointmentId) REFERENCES Visit(visitId) ON DELETE CASCADE
);
