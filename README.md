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
                        ├── Illnes.java
                        ├── Visit.java
                        ├── User.java
                        └── MedicalFile.java

                    ├── dao
                        ├── implementation
                            ├── PatientDaoImp.java
                            ├── TestDaoImp.java
                            ├── IllnessDaoImp.java
                            ├── VisitImp.java
                            └── MedicalFileImp.java

                        ├── PatientDao.java
                        ├── TestDao.java
                        ├── IllnessDao.java
                        ├── VisitDao.java
                        └── MedicalFileDao.java

                    ├── controllers
                        ├── AdminController.java
                        ├── IllnessController.java
                        ├── loginController.java
                        ├── MedicalFileController.java
                        ├── PatientController.java
                        ├── RegisterController.java
                        ├── TestController.java
                        └── VisitController.java
                    
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

