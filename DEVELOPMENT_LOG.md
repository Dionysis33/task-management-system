Ημερολόγιο Ανάπτυξης (Development Log)

Project: Enterprise Task Management System

Διάρκεια: Νοέμβριος 2025 – Ιανουάριος 2026

Φάση 1: Ανάλυση Απαιτήσεων & Σχεδιασμός (Εβδομάδες 1-3)

Καθορισμός Πεδίου: Επιλογή του συστήματος διαχείρισης εργασιών με έμφαση σε ακαδημαϊκά περιβάλλοντα.

Σχεδιασμός UML: Δημιουργία των αρχείων ERD.drawio, Class_Diagram_Diagram.drawio και Use_Case_Diagram.drawio που βλέπουμε στο ριζικό κατάλογο του project.

Επιλογή Τεχνολογιών: Απόφαση για χρήση Java 21 και Spring Boot 3.3.0 για αξιοποίηση σύγχρονων δυνατοτήτων απόδοσης.

Φάση 2: Υλοποίηση Υποδομής & Βάσης Δεδομένων (Εβδομάδες 4-6)

Ρύθμιση Persistence: Διαμόρφωση του application.properties για σύνδεση με MySQL και αυτόματη ενημέρωση του σχήματος (hibernate.ddl-auto=update).

Δημιουργία Entities: Ανάπτυξη των κλάσεων User, Task και Role στο πακέτο com.uogm.taskmanagement.entity.

Repository Layer: Υλοποίηση των interfaces UserRepository, TaskRepository και RoleRepository για την επικοινωνία με τη βάση.

Φάση 3: Επιχειρησιακή Λογική & Ασφάλεια (Εβδομάδες 7-9)

Service Layer: Ανάπτυξη της λογικής στα TaskServiceImpl και UserServiceImpl, διασφαλίζοντας το διαχωρισμό ευθυνών (N-Tier Architecture).

Ασφάλεια (Spring Security 6): Ρύθμιση του SecurityConfig για έλεγχο πρόσβασης βάσει ρόλων (RBAC).

Προστασία Δεδομένων: Χρήση του UserRegistrationDTO για την ασφαλή μεταφορά δεδομένων και την αποφυγή επιθέσεων Mass Assignment.

Φάση 4: Βελτιστοποίηση & Αξιολόγηση (Εβδομάδες 10-11)

Performance Tuning: Ενεργοποίηση των Virtual Threads στο Spring Boot για τη διαχείριση μεγάλου όγκου ταυτόχρονων αιτημάτων.

Μαθηματική Τεκμηρίωση: Εφαρμογή του Νόμου του Little ($L = \lambda \times W$). 
Η χρήση Virtual Threads επιτρέπει στο σύστημα να διατηρεί υψηλό αριθμό ταυτόχρονων εργασιών ($L$) αυξάνοντας το throughput ($\lambda$)
χωρίς να επιβαρύνεται ο χρόνος απόκρισης ($W$).

Τελικές Δοκιμές: Υλοποίηση unit tests (π.χ. TaskServiceImplTest) για τη διασφάλιση της ορθότητας των λειτουργιών.
