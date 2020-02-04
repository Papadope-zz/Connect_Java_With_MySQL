package db_utils;

import static db_utils.db_connection.createConnection;
import java.sql.*;
import java.util.*;
import validations.db_validations;

public class database_menu {

    static Scanner sc = new Scanner(System.in);

    //This method is the main menu
    public static void mainMenu() throws SQLException {
        int choice;
        System.out.println(" Welcome ^_^ ");
        System.out.println("Press 1 if you want to read dummy data from DB");
        System.out.println("Press 2 if you want to import data to DB");
        System.out.println("Press 0 to exit.");
        choice = db_validations.returnValidNumber(i -> i < 0 || i > 2);
        createConnection();
        switch (choice) {
            case 1:
                System.out.println("DUMMY DATA");
                System.out.println("**************************************");
                menuForDummyData();
            case 2:
                System.out.println("IMPORT DATA");
                System.out.println("**************************************");
                menuForImportData();
            case 0:
                System.out.println("Thank you very much. GoodBye!");
                db_connection.con.close();
                System.exit(0);
        }
    }

    //This method is the menu to see data into the database
    public static void menuForDummyData() throws SQLException {
        System.out.println(" - Choose what do you want to get inform about(Database Menu) : ");
        System.out.println("#1. A List Of All Students : ");
        System.out.println("#2. A List Of All Trainers : ");
        System.out.println("#3. A List Of All Assignments : ");
        System.out.println("#4. A List Of All Courses : ");
        System.out.println("#5. A List Of All Students Per Course : ");
        System.out.println("#6. A List Of Trainers Per Course : ");
        System.out.println("#7. A List Of Assignments Per Course : ");
        System.out.println("#8. A List Of Assignments Per Course Per Student : ");
        System.out.println("#9. A List Of Students With More Than One Assignments : ");
        System.out.println("#10. Choose it to go to the previous menu");
        System.out.println("#0. Press 0 to terminate the program ");
        System.out.println("**************************************");

        int choice = db_validations.returnValidNumber(i -> i < 0 || i > 10);

        switch (choice) {

            case 1:
                db_connection.getStudents();
                menuForDummyData();
            case 2:
                db_connection.getTrainers();
                menuForDummyData();
            case 3:
                db_connection.getAssignments();
                menuForDummyData();
            case 4:
                db_connection.getCourses();
                menuForDummyData();
            case 5:
                db_connection.getStudentsPerCourse();
                menuForDummyData();
            case 6:
                db_connection.getTrainersPerCourse();
                menuForDummyData();
            case 7:
                db_connection.getAssignmentsPerCourse();
                menuForDummyData();
            case 8:
                db_connection.getAssignmentsPerCoursePerStudent();
                menuForDummyData();
            case 9:
                db_connection.getStudentsForMoreThanOneCourse();
                menuForDummyData();
            case 10:
                System.out.println(" Back to start! ^_^ ");
                database_menu.mainMenu();
            case 0:
                System.out.println(" GoodBye.\nRemember if it works, don't touch it.");
                db_connection.con.close();
                System.exit(0);
        }
    }

    //This method is to import data into the database
    public static void menuForImportData() throws SQLException {
        System.out.println(" Import data into database : ");
        System.out.println(" #1. Import a student : ");
        System.out.println(" #2. Import a trainer : ");
        System.out.println(" #3. Import an assignment : ");
        System.out.println(" #4. Import a course : ");
        System.out.println(" #5. Import a student per course : ");
        System.out.println(" #6. Import a trainer per course : ");
        System.out.println(" #7. Import an assignment per student per course : ");
        System.out.println(" #8. Back to main menu : ");
        System.out.println(" #9. Exit program : ");

        int choice = db_validations.returnValidNumber(i -> i < 1 || i > 9);

        switch (choice) {

            case 1:
                db_connection.insertDataIntoStudentsTable();
                System.out.println("**************************************");
                menuForImportData();
            case 2:
                db_connection.insertDataIntoTrainersTable();
                System.out.println("**************************************");
                menuForImportData();
            case 3:
                db_connection.insertDataIntoAssignmentsTable();
                System.out.println("**************************************");
                menuForImportData();
            case 4:
                db_connection.insertDataIntoCoursesTable();
                System.out.println("**************************************");
                menuForImportData();
            case 5:
                db_connection.insertDataIntoStudentsPerCourseTable();
                System.out.println("**************************************");
                menuForImportData();
            case 6:
                db_connection.insertDataIntoTrainersPerCourseTable();
                System.out.println("**************************************");
                menuForImportData();
            case 7:
                db_connection.insertDataIntoAssignmentPerStudentPerCourse();
                System.out.println("**************************************");
                menuForImportData();
            case 8:
                System.out.println(" Back to start! ^_^ ");
                database_menu.mainMenu();
                System.out.println("**************************************");
            case 9:
                System.out.println(" Goodbye! ");
                System.out.println(" And remember if it works don't touch it ");
                db_connection.con.close();
                System.exit(0);
        }
    }
}
