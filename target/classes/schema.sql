# # SET FOREIGN_KEY_CHECKS = 0;
# #
# # DROP TABLE IF EXISTS Patient_Test;
# # DROP TABLE IF EXISTS MedicalFile;
# # DROP TABLE IF EXISTS Test;
# # DROP TABLE IF EXISTS Patient;
# #
# # SET FOREIGN_KEY_CHECKS = 1;
# CREATE TABLE  Patient (
#                           CIN VARCHAR(50) PRIMARY KEY,
#                           firstName VARCHAR(100) NOT NULL,
#                           lastName VARCHAR(100) NOT NULL,
#                           age INT NOT NULL,
#                           gender ENUM('Male', 'Female') NOT NULL,
#                           email VARCHAR(255) NOT NULL UNIQUE,
#                           address VARCHAR(255) NOT NULL,
#                           phoneNumber VARCHAR(20) NOT NULL
# );
#
# CREATE TABLE IF NOT EXISTS MedicalFile (
#                                            id INT AUTO_INCREMENT PRIMARY KEY,
#                                            dateCreation DATE NOT NULL,
#                                            dateModif DATE,
#                                            patientId VARCHAR(50) NOT NULL,
#                                            FOREIGN KEY (patientId) REFERENCES Patient(CIN) ON DELETE CASCADE
# );
#
# CREATE TABLE IF NOT EXISTS Test (
#                                     testId INT PRIMARY KEY AUTO_INCREMENT,
#                                     testName VARCHAR(100) NOT NULL,
#                                     category VARCHAR(50),
#                                     description TEXT
# );
#
# CREATE TABLE IF NOT EXISTS Patient_Test (
#                                             CIN VARCHAR(50) NOT NULL,
#                                             testId INT NOT NULL,
#                                             selected BOOLEAN DEFAULT FALSE,
#                                             PRIMARY KEY (CIN, testId),
#                                             FOREIGN KEY (CIN) REFERENCES Patient(CIN) ON DELETE CASCADE,
#                                             FOREIGN KEY (testId) REFERENCES Test(testId) ON DELETE CASCADE
# );