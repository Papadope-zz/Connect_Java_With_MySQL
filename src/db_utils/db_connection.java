package db_utils;

import java.sql.*;
import java.util.*;
import validations.db_validations;

public class db_connection {

    public static Scanner sc = new Scanner(System.in);
    public static Connection con = null;
    public static ResultSet rs;

    //This method creates the connection with database
    public static void createConnection() {
        String url = "jdbc:mysql://localhost:3306"
                + "/cb9_part2?zeroDateTimeBehavior="
                + "CONVERT_TO_NULL&useUnicode="
                + "true&useJDBCCompliantTimezoneShift="
                + "true&useLegacyDatetimeCode=false&serverTimezone="
                + "UTC&allowPublicKeyRetrieval=true&useSSL=false";
        String username = "root";
        String password = "Your_DB_Password";
        try {
            con = (Connection) DriverManager.getConnection(url, "root",
                    "Papadog21!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //This method gets students from the database
    public static void getStudents() throws SQLException {

        Statement st = db_connection.con.createStatement();
        String leftAlignFormat = "| %3d | %-10s | %-12s | %s |%-7d| %n";
        System.out.format("+-----+------------+--------------+------------+-------+%n");
        System.out.format("|                  S T U D E N T S                     |%n");
        System.out.format("+-----+------------+--------------+------------+-------+%n");
        System.out.format("| ID  | FIRST NAME |  LAST NAME   | BIRTHDATE  |  FEES |%n");
        System.out.format("+-----+------------+--------------+------------+-------+%n");

        ResultSet result = st.executeQuery("select * from students");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDate(4).toLocalDate(),
                    result.getInt(5));
        }
        System.out.format("+-----+------------+--------------+------------+-------+%n");
    }

    //This method gets trainers from the database
    public static void getTrainers() throws SQLException {
        Statement st = con.createStatement();
        String leftAlignFormat = "| %10d | %-11s | %-11s | %-10s | %n";
        System.out.format("+------------+-------------+-------------+------------+%n");
        System.out.format("|                  T R A I N E R S                    |%n");
        System.out.format("+------------+-------------+-------------+------------+%n");
        System.out.format("| TRAINER ID |  FIRST NAME | LAST NAME   |  SUBJECT   |%n");
        System.out.format("+------------+-------------+-------------+------------+%n");

        ResultSet result = st.executeQuery("select * from trainers");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4));
        }
        System.out.format("+------------+-------------+-------------+------------+%n");
    }

    //This method gets courses from the database
    public static void getCourses() throws SQLException {
        Statement st = con.createStatement();
        String leftAlignFormat = "| %-10d | %-40s | %-16s | %-9s | %-12s | %-6s | %n";
        System.out.format("+------------+------------------------------------------+------------------+-----------+--------------+------------+%n");
        System.out.format("|                                                 C O U R S E S                                                    |%n");
        System.out.format("+------------+------------------------------------------+------------------+-----------+--------------+------------+%n");
        System.out.format("| COURSE ID  |                   TITLE                  |     STREAM       |   TYPE    |  START DATE  |   END DATE |%n");
        System.out.format("+------------+------------------------------------------+------------------+-----------+--------------+------------+%n");

        ResultSet result = st.executeQuery("select * from courses");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDate(5).toLocalDate(),
                    result.getDate(6).toLocalDate());
        }
        System.out.format("+------------+------------------------------------------+------------------+-----------+--------------+------------+%n");
    }

    //This method gets assignments from the database
    public static void getAssignments() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-9d | %-13d | %-36s | %-40s | %-6s | %-9d | %-10d |%n";
        System.out.format("+-----------+---------------+--------------------------------------+------------------------------------------+------------+-----------+------------+%n");
        System.out.format("|                                                       A S S I G N M E N T S                                                                       |%n");
        System.out.format("+-----------+---------------+--------------------------------------+------------------------------------------+------------+-----------+------------+%n");
        System.out.format("| COURSE ID | ASSIGNMENT ID |                TITLE                 |                DESCRIPTION               |  SUB DATE  | ORAL MARK | TOTAL MARK |%n");
        System.out.format("+-----------+---------------+--------------------------------------+------------------------------------------+------------+-----------+------------+%n");

        ResultSet result = st.executeQuery("select * from assignments");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDate(5).toLocalDate(),
                    result.getInt(6),
                    result.getInt(7));
        }
        System.out.format("+-----------+---------------+--------------------------------------+------------------------------------------+------------+-----------+------------+%n");
    }

    //This method gets students per course from the database
    public static void getStudentsPerCourse() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-10d | %-10s | %-11s | %-9d | %-10s | %-18S |%n";
        System.out.format("+------------+------------+-------------+-----------+------------+--------------------+%n");
        System.out.format("|                             S T U D E N T S  P E R  C O U R S E                     |%n");
        System.out.format("+------------+------------+-------------+-----------+------------+--------------------+%n");
        System.out.format("| STUDENT ID | FIRST NAME |   LAST NAME | COURSE ID |   TYPE     |        STREAM      |%n");
        System.out.format("+------------+------------+-------------+-----------+------------+--------------------+%n");

        ResultSet result = st.executeQuery("select s.student_id, s.first_name, s.last_name, \n"
                + "c.course_id, c.type, c.stream from students s \n"
                + "inner join students_per_course spc \n"
                + "on s.student_id = spc.student_id \n"
                + "inner join courses c on spc.course_id = c.course_id");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5),
                    result.getString(6));
        }
        System.out.format("+------------+------------+-------------+-----------+------------+--------------------+%n");
    }

    //This method gets the trainers per course from the database
    public static void getTrainersPerCourse() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-10d | %-12s | %-12s | %-10s | %-9d | %-10s | %-17s |%n";
        System.out.format("+------------+--------------+--------------+------------+-----------+------------+-------------------+%n");
        System.out.format("|                               T R A I N E R S  P E R  C O U R S E                                  |%n");
        System.out.format("+------------+--------------+--------------+------------+-----------+------------+-------------------+%n");
        System.out.format("| TRAINER ID |   FIRST NAME | LAST NAME    |  SUBJECT   | COURSE ID |    TYPE    |       STREAM      |%n");
        System.out.format("+------------+--------------+--------------+------------+-----------+------------+-------------------+%n");

        ResultSet result = st.executeQuery("select t.trainer_id, t.first_name, t.last_name, t.subject, c.course_id, \n"
                + "c.type, c.stream from trainers t \n"
                + "inner join trainers_per_course tpc \n"
                + "on t.trainer_id = tpc.trainer_id \n"
                + "inner join courses c on tpc.course_id = c.course_id");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getString(6),
                    result.getString(7));
        }
        System.out.format("+------------+--------------+--------------+------------+-----------+------------+-------------------+%n");
    }

    //This method gets the assignments per course from the database
    public static void getAssignmentsPerCourse() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-4d | %-4d | %-36s | %-40s | %-40s | %-9s |%n";
        System.out.format("+------+------+--------------------------------------+------------------------------------------+------------------------------------------+-----------+%n");
        System.out.format("|                                                      A S S I G N M E N T  P E R  C O U R S E                                                         |%n");
        System.out.format("+------+------+--------------------------------------+------------------------------------------+------------------------------------------+-----------+%n");
        System.out.format("| C ID | A ID |            ASSIGNMENT TITLE          |                DESCRIPTION               |                COURSE TITLE              |    TYPE   |%n");
        System.out.format("+------+------+--------------------------------------+------------------------------------------+------------------------------------------+-----------+%n");

        ResultSet result = st.executeQuery("select * from assignments a \n"
                + "inner join courses c \n"
                + "on a.course_id = c.course_id");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getInt(2),
                    result.getString(3), result.getString(4),
                    result.getString(9), result.getString(11));
        }
        System.out.format("+------+------+--------------------------------------+------------------------------------------+------------------------------------------+-----------+%n");
    }

    //This method gets the assignments per course per student from database
    public static void getAssignmentsPerCoursePerStudent() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-4d | %-10s | %-12s | %-4d | %-36s | %-9d | %-9s |%n";
        System.out.format("+------+------------+--------------+------+--------------------------------------+-----------+-----------+%n");
        System.out.format("|                     A S S I G N M E N T  PER  C O U R S E  PER  S T U D E N T                          |%n");
        System.out.format("+------+------------+--------------+------+--------------------------------------+-----------+-----------+%n");
        System.out.format("| S ID | FIRST NAME |  LAST NAME   | A ID |                 TITLE                |    C ID   |    TYPE   |%n");
        System.out.format("+------+------------+--------------+------+--------------------------------------+-----------+-----------+%n");

        ResultSet result = st.executeQuery("select s.student_id, s.first_name, s.last_name,a.assignment_id, a.title, a.description, apsc.total_mark, apsc.sub_date,c.course_id, c.stream, c.type \n"
                + "from students s \n"
                + "inner join assignment_per_student_per_course apsc on s.student_id = apsc.student_id \n"
                + "inner join assignments a on apsc.assignment_id = a.assignment_id \n"
                + "inner join courses c on a.course_id = c.course_id");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5),
                    result.getInt(9),
                    result.getString(11));
        }
        System.out.format("+------+------------+--------------+------+--------------------------------------+-----------+-----------+%n");
    }

    //This method gets students that belong to more than one courses
    public static void getStudentsForMoreThanOneCourse() throws SQLException {
        Statement st = db_connection.con.createStatement();

        String leftAlignFormat = "| %-18s | %-18s |%n";
        System.out.format("+--------------------+--------------------+%n");
        System.out.format("| S T U D E N T S  WITH MORE C O U R S E  |%n");
        System.out.format("+--------------------+--------------------+%n");
        System.out.format("|    FIRST NAME      |      LAST NAME     |%n");
        System.out.format("+--------------------+--------------------+%n");

        ResultSet result = st.executeQuery("select first_name, last_name \n"
                + "from students \n"
                + "where student_id \n"
                + "in(select student_id from students_per_course group by student_id having count(course_id) > 1);");
        while (result.next()) {
            System.out.format(leftAlignFormat, result.getString(1),
                    result.getString(2));
        }
        System.out.format("+--------------------+--------------------+%n");
    }

    //This method inserts data into students table
    public static boolean insertDataIntoStudentsTable() throws SQLException {
        Statement st = db_connection.con.createStatement();
        System.out.println("Give me the first name of the student : ");
        String first_name = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the last name of the student : ");
        String last_name = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Enter a date of birth (YYYY-MM-DD) : ");
        String date_of_birth = db_validations.returnValidDate();
        System.out.println("Enter tuition fees (can't be more than 2500$ or a negative number)");
        int tuition_fees = db_validations.returnValidNumber(i -> i < 0 || i > 2500);
        int result = st.executeUpdate("insert into students(first_name, last_name, date_of_birth, tuition_fees) \n"
                + "values('" + first_name + "','" + last_name + "','" + date_of_birth + "','" + tuition_fees + "')");
        if (result == 1) {
            System.out.println(" Your input is done! ");
            return false;
        }
        System.out.println(" Sorry something came up ");
        return true;
    }

    //This method inserts data into trainers table
    public static boolean insertDataIntoTrainersTable() throws SQLException {
        Statement st = db_connection.con.createStatement();
        System.out.println("Give me the first name of the trainer : ");
        String first_name = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the last name of the trainer : ");
        String last_name = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the subject of the course type : ");
        String subject = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        int result = st.executeUpdate("insert into trainers(first_name, last_name, subject) \n"
                + "values('" + first_name + "','" + last_name + "','" + subject + "')");
        if (result == 1) {
            System.out.println(" Your input is done! ");
            return false;
        }
        System.out.println(" Sorry something came up ");
        return true;
    }

    //This method inserts data into assignments table
    public static boolean insertDataIntoAssignmentsTable() throws SQLException {

        try {
            Statement st = con.createStatement();
            System.out.println("Give me title for the assignment : ");
            String title = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
            System.out.println("Give me the description of the assignment : ");
            String description = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
            System.out.println("Give me the start date of the subject (YYYY-MM-DD) : ");
            String sub_date_time = db_validations.returnValidDate();
            System.out.println("Give me the oral mark (max input 100) : ");
            int oral_mark = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            System.out.println("Give me the total mark (max input 100) : ");
            int total_mark = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            getCourses();
            System.out.println("Give me the course ID you want to add the assignment : ");
            int course_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            int result = st.executeUpdate("insert into assignments(course_id, title, description, sub_date_time, oral_mark, total_mark) \n"
                    + "values('" + course_id + "','" + title + "','" + description + "','" + sub_date_time + "','" + oral_mark + "','" + total_mark + "')");
            if (result == 1) {
                System.out.println(" Your input is ok ");
                return false;
            }
            System.out.println(" Sorry something came up ");
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("There is not a course in that ID");
            System.out.println("Create a course first ?");
            System.out.println(" #1 Yes or 2# No ");
            int choice = db_validations.returnValidNumber(i -> i < 1 || i > 2);
            switch (choice) {
                case 1:
                    insertDataIntoCoursesTable();
                case 2:
                    database_menu.mainMenu();
            }
        }
        return false;
    }

    //This method inserts data into courses table
    public static boolean insertDataIntoCoursesTable() throws SQLException {
        Statement st = con.createStatement();
        System.out.println("Give me title for the course : ");
        String title = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the stream of the course : ");
        String stream = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the type of the course : ");
        String type = db_validations.loopAndGetValidString(s -> s.trim().matches("[a-z A-Z]{1,15}"));
        System.out.println("Give me the start date of the course (YYYY-MM-DD) : ");
        String start_date = db_validations.returnValidDate();
        System.out.println("Give me the end_date of the course (YYYY-MM-DD) : ");
        String end_date = db_validations.returnValidDate();
        int result = st.executeUpdate("insert into courses(title, stream, type, start_date, end_date) \n"
                + "values('" + title + "','" + stream + "','" + type + "','" + start_date + "','" + end_date + "')");
        if (result == 1) {
            System.out.println(" Your input is ok ");
            getCourses();
            return false;
        }
        System.out.println(" Sorry something came up ");
        return true;
    }

    //This method inserts data into students per course table
    public static boolean insertDataIntoStudentsPerCourseTable() throws SQLException {

        try {
            Statement st = con.createStatement();
            getCourses();
            System.out.println(" Choose ID of the course you want to insert the student in : ");
            int course_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            getStudentsPerCourse();
            System.out.println(" Choose ID of the student you want to insert : ");
            int student_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            int result = st.executeUpdate("insert into students_per_course(course_id, student_id) \n"
                    + "values('" + course_id + "','" + student_id + "')");
            if (result == 1) {
                System.out.println(" Your input is ok ");
                getStudentsPerCourse();
                return false;
            }
            System.out.println(" Sorry something came up ");
            return true;

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println(" Invalid Input. ");
            System.out.println("Create a course first ?");
            System.out.println(" #1 Yes or 2# No ");
            int choice = db_validations.returnValidNumber(i -> i < 1 || i > 2);
            switch (choice) {
                case 1:
                    insertDataIntoCoursesTable();
                case 2:
                    database_menu.mainMenu();
            }
        }
        return false;
    }

    //This method inserts data into trainers per course table doesn't work
    public static boolean insertDataIntoTrainersPerCourseTable() throws SQLException {

        try {
            Statement st = con.createStatement();
            getCourses();
            System.out.println(" Choose ID of the course you want to insert the trainer in : ");
            int course_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            getTrainersPerCourse();
            System.out.println(" Choose ID of the trainer you want to insert : ");
            int trainer_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            int result = st.executeUpdate("insert into trainers_per_course(course_id, trainer_id) \n"
                    + "values('" + course_id + "','" + trainer_id + "')");
            if (result == 1) {
                System.out.println(" Your input is ok ");
                getTrainersPerCourse();
                return false;
            }
            System.out.println(" Sorry something came up ");
            return true;

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println(" Invalid Input. ");
            System.out.println("Create a course first ?");
            System.out.println(" Check first if trainer is already in that course.");
            System.out.println(" #1 Yes or 2# No ");
            int choice = db_validations.returnValidNumber(i -> i < 1 || i > 2);
            switch (choice) {
                case 1:
                    insertDataIntoCoursesTable();
                case 2:
                    database_menu.mainMenu();
            }
        }
        return false;
    }

    //This method inserts data into assignments per student per course table
    public static boolean insertDataIntoAssignmentPerStudentPerCourse() throws SQLException {

        try {
            Statement st = con.createStatement();
            getAssignments();
            System.out.println(" Choose ID of the assignment you want to insert : ");
            int assignment_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            getStudentsPerCourse();
            System.out.println(" Choose ID of the student you want to insert : ");
            int student_id = db_validations.returnValidNumber(i -> i < 0 || i > 100);
            int result = st.executeUpdate("insert into assignment_per_student_per_course(assignment_id, student_id) \n"
                    + "values('" + assignment_id + "','" + student_id +"')");
            if (result == 1) {
                getAssignmentsPerCoursePerStudent();
                System.out.println(" Your input is ok");
                return false;
            }
            System.out.println(" Sorry something came up ");
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println(" Invalid Input. ");
            System.out.println("Create a course first ?");
            System.out.println(" Check first if student is already in that assignment.");
            System.out.println(" #1 Yes or 2# No ");
            int choice = db_validations.returnValidNumber(i -> i < 1 || i > 2);
            switch (choice) {
                case 1:
                    insertDataIntoCoursesTable();
                case 2:
                    database_menu.mainMenu();
            }
        }
        return false;
    }

}
