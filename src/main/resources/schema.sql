
# -- Drop tables if they already exist Ola Ms7o hadchi odik sa3a ayb9aw tables
DROP TABLE IF EXISTS TestLab;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS MedicalFile;
DROP TABLE IF EXISTS Patient_Test;

# -- Table: Patient
CREATE TABLE Patient (
                         patientId VARCHAR(50)  PRIMARY KEY,   -- Auto-incrementing primary key for patientId
                         firstName VARCHAR(100) NOT NULL,             -- First name of the patient
                         lastName VARCHAR(100) NOT NULL,              -- Last name of the patient
                         age INT NOT NULL,                            -- Age of the patient
                         gender ENUM('Male', 'Female') NOT NULL,      -- Gender of the patient (Male or Female)
                         email VARCHAR(255) NOT NULL UNIQUE,          -- Email of the patient
                         address VARCHAR(255) NOT NULL,               -- Address of the patient
                         phoneNumber VARCHAR(20) NOT NULL             -- Phone number of the patient
);

# -- Table: MedicalFile
CREATE TABLE MedicalFile (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             dateCreation DATE NOT NULL,
                             dateModif DATE,
                             patientId VARCHAR(50) NOT NULL,
                             FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE
);

# -- Table: Test
CREATE TABLE TestLab (
                      testId INT AUTO_INCREMENT PRIMARY KEY,    -- Identifiant unique pour le test
                      testName VARCHAR(100) NOT NULL,           -- Nom du test
                      category VARCHAR(50),                     -- Catégorie du test
                      description TEXT                          -- Description du test
);

# -- Table: Patient_Test (association entre Patient et TestLab)
CREATE TABLE Patient_Test (
                              patientId VARCHAR(50) NOT NULL,          -- Référence au patient
                              testId INT NOT NULL,                     -- Référence au test
                              selected BOOLEAN DEFAULT FALSE,          -- Indicateur si le test est sélectionné
                              PRIMARY KEY (patientId, testId),         -- Clé primaire composite
                              FOREIGN KEY (patientId) REFERENCES Patient(patientId) ON DELETE CASCADE,
                              FOREIGN KEY (testId) REFERENCES TestLab(testId) ON DELETE CASCADE
);
