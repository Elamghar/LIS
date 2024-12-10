<<<<<<< HEAD
# LIS
laboratory information system
├── main
|- java
   - ma
      - ensa
          - models
                 -Patient.java
                 -Test.java
                 -Sickness.java
                 -Visit.java
                 -MedicalFile.java
      - Dao
         - PatientDao.java
         - TestDao.java
         - SicknessDao.java
         - VisitDao.java
         - MedicalFileDAo.java
       -Implementation
    ─Controllers
-App.java
 ─ test
        ─ models
        ─ repositories
        ─ services
        ─ LISApplicationTests.java
- pom.xml
=======
# LIS : Laboratory Information System

├── main

    ├── java
        └── ma
            └── ensa
                └── lis
                    ├── models
                        ├── Patient.java
                        ├── TestLab.java
                        ├── TestStatus.java
                        └── MedicalFile.java

                

                    ├── dao
                        ├── implementation
                            ├── PatientDaoImp.java
                            ├── TestDaoImp.java
                            └── MedicalFileImp.java

                        ├── PatientDao.java
                        ├── TestDao.java
                        └── MedicalFileDao.java

                    ├── controllers
                        ├── AdminController.java
                        ├── loginController.java
                        ├── MedicalFileController.java
                        ├── PatientController.java
                        ├── TestController.java
                    
                    ├── utils
                        ├── DbConnection.java
                        ├── JsonUtils.java
                        ├── PDFGenerator.java
                        ├── QRCodeGenerator.java
                        └── useFullFunstion.java

                    └── App.java

├── test 
    
    ├── java
        └── ma
            └── ensa
                └── lis
                    ├── models
                        ├── VisistTest.java
                        ├── TestTestLab.java
                        └── TestStatusTestLab.java

                    ├── dao
                        └──implementation
                            └── TestDaoImpTest.java

                        └── TestDaoTest.java



                    ├── controllers
                        └── TestControllerTest.java
                    
                    └── utils
                        └── QRCodeGeneratorTest.java


└── pom.xml

>>>>>>> origin/main
