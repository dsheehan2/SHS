Secure Healthcare System (SHS)

-- How to compile --

(Was tested on Windows OS using Eclipse IDE and JavaFX version 18.0.1) 

1. Download the latest version of Java Development Kit (JDK) and install it
2. Import the project into your choice of IDE. Make sure to extract it before doing so. If you see errors, you must install the JavaFX Windows SDK Libraries.
3. Download the latest version of JavaFX Windows SDK Libraries and install in IDE that works with Java of your choice.
(If you do not know how to download JavaFX, please visit here: https://openjfx.io/openjfx-docs/#install-javafx which is the official JavaFX website as this is the install instructions using Eclipse, brief instructions at the bottom of document)
4. Run the Main.java class!

-- How to Use the GUI and Test Cases --

1. For the GUI I used the text files as the entities. I already have test information in each of these. To see the best results when you interact with the program, first open all the text files as seperate tabs on your screen with the Main.java class.
2. Now, navigate to EmployeeAccount.txt and right click Main.java and select run, you will be presented with a login screen where it shows you need a Employee ID and a Password, in the EmployeeAccount.txt file you can see all their login information,
If you use a Staff, copy their ID and password into their login and you will enter in their respective interfaces. For best results, you should login with Staff first to either Create an Appointment, Clear No-Show or Check In an existing patient.
3. Remember the access you have on that Staff, Nurse, or Doctor you entered, if on the EmployeeAccount.txt file it says "Denied" you will not be able to access many of the options since these users don't have access. Pick one with "Granted"!
4. Notice, for Make Appointment (considering you want a new patient in the Appointment.txt file), you will create an appointment based on the doctors schedule, enter exactly how you see each doctor information otherwise you will get an error.
Once, you are done, you will get a complete message and your Patient name is added to the Appointment.txt file and the respective Doctor Schedule you chose is removed.
5. For Clearing No Show, simply type the patients full name to add them to the No_Show.txt and remove them from Appointment.txt.
6. For Check-In patient, you will first need to get authentication for the specific Staff user in the AuthenticationPIN.txt file, just copy and paste the respective staff you logged in with's pin. This is one of the security use cases.
7. Here, you will be able to Check-In the patient and if the patient is new, you will create an account for them in PatientInformation.txt, PatientMedicalChart.txt, and PaymentInformation.txt by typing their new info. If they are 
returning (i.e already in the file) you can update their info to rewrite it in these files or you can press "No" which it will not.
8. For Pay-Medical Fee, you can search for the Patient you created, or existing (David and Jacob), you will be able to update their PaymentInformation.txt file with what you chose to pay, notice that these will be encrypted! If you chose
Debit, the Bank interface will pop up where the banker (you in this case) clicks "yes" or "no" to approve. Clicking yes will update their info and keep track of their reference numbers on the last line.
9. Now go back to the Log In screen and pick a "Granted" nurse and log in with their credentials, search for your Patient and type their reason to visit and measurements, this will update their PatientMedicalChart.txt!
10. Lastly, log out and do the same for Doctor, log in and create a perscription and treatment for the patient. Notice if you picked an existing Patient in the system, it will ask you if you want to overwrite these perscriptions and
treatments. Since they are existing, they have conditions in these fields already, you can either press "yes" and rewrite them, or "no" and not rewrite them. You Log out when you are completed and exit the GUI.
11. That is everything including the security in the program!

-- Here are Brief Instructions to download JavaFX for Eclipse --

1. Make sure you download the JavaFX SDK at https://openjfx.io/openjfx-docs/#install-javafx and include the VM Arguments by going to the projects build properties and then navigating to the path of your SDK's "lib" folder.
2. Download the Eclipse Official Java Eclipse FX Plugin titled e(fx)clipse in Eclipse.
3. Now you need to paste this into the VM Arguments: --module-path "\path\to\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml
Make sure to replace \path\to\javafx-sdk-17.0.1\lib to your lib file path.
4. Now run the Main.java class!
