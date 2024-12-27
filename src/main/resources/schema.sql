DROP TABLE IF EXISTS TestLab;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS MedicalFile;
DROP TABLE IF EXISTS Patient_Test;

CREATE TABLE Patient (
                         patientId VARCHAR(50) PRIMARY KEY,
                         firstName VARCHAR(100) NOT NULL,
                         lastName VARCHAR(100) NOT NULL,
                         age INT NOT NULL,
                         gender ENUM('Male', 'Female') NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         address VARCHAR(255) NOT NULL,
                         phoneNumber VARCHAR(20) NOT NULL
);

CREATE TABLE MedicalFile (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             dateCreation DATE NOT NULL,
                             dateModif DATE,
                             patientId VARCHAR(50) NOT NULL,
                             FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
);

CREATE TABLE Test (
                      testId INT  PRIMARY KEY,
                      testName VARCHAR(100) NOT NULL,
                      category VARCHAR(50),
                      description TEXT
);

CREATE TABLE Patient_Test (
                              patientId VARCHAR(50) NOT NULL,
                              testId INT NOT NULL,
                              selected BOOLEAN DEFAULT FALSE,
                              PRIMARY KEY (patientId, testId),
                              FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE,
                              FOREIGN KEY (testId) REFERENCES Test(testId) ON DELETE CASCADE
);
