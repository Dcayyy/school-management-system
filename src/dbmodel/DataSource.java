package dbmodel;

import account.Director;
import account.Student;
import account.Teacher;
import account.User;

import java.sql.*;
import java.util.Scanner;

public class DataSource {

    public static final String DB_NAME = "School_Management_SystemDB.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Zahari Mikov\\IdeaProjects\\School Management System with DB\\" + DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_USER_ID = "user_id";
    public static final String COLUMN_USERS_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_ACCOUNT_TYPE = "account_type";
    public static final int INDEX_USERS_USER_ID = 1;
    public static final int INDEX_USERS_EMAIL_ADDRESS = 2;
    public static final int INDEX_USERS_PASSWORD = 3;
    public static final int INDEX_USERS_ACCOUNT_TYPE = 4;

    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_STUDENTS_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENTS_FIRST_NAME = "first_name";
    public static final String COLUMN_STUDENTS_LAST_NAME = "last_name";
    public static final String COLUMN_STUDENTS_AGE = "age";
    public static final String COLUMN_STUDENTS_CLASSROOM = "classroom";
    public static final String COLUMN_STUDENTS_NUMBER_IN_CLASS = "number_in_class";
    public static final String COLUMN_STUDENTS_SPECIALITY = "speciality";
    public static final String COLUMN_STUDENTS_USER_ID = "user_id";
    public static final int INDEX_STUDENTS_STUDENT_ID = 1;
    public static final int INDEX_STUDENTS_FIRST_NAME = 2;
    public static final int INDEX_STUDENTS_LAST_NAME = 3;
    public static final int INDEX_STUDENTS_AGE = 4;
    public static final int INDEX_STUDENTS_CLASSROOM = 5;
    public static final int INDEX_STUDENTS_NUMBER_IN_CLASS = 6;
    public static final int INDEX_STUDENTS_SPECIALITY = 7;
    public static final int INDEX_STUDENTS_USER_ID = 8;

    public static final String TABLE_SQLITE_SEQUENCE = "sqlite_sequence";
    public static final String COLUMN_SQLITE_SEQUENCE_name = "name";
    public static final String COLUMN_SQLITE_SEQUENCE_SEQ = "seq";
    public static final int INDEX_SQLITE_SEQUENCE_NAME = 1;
    public static final int INDEX_SQLITE_SEQUENCE_SEQ = 2;

    public static final String TABLE_TEACHERS = "teachers";
    public static final String COLUMN_TEACHERS_TEACHER_ID = "teacher_id";
    public static final String COLUMN_TEACHERS_FIRST_NAME = "first_name";
    public static final String COLUMN_TEACHERS_LAST_NAME = "last_name";
    public static final String COLUMN_TEACHERS_TAUGHT_SUBJECT = "taught_subject";
    public static final String COLUMN_TEACHERS_HEAD_OF_CLASS = "head_of_class";
    public static final String COLUMN_TEACHERS_USER_ID = "user_id";
    public static final int INDEX_TEACHERS_TEACHER_ID = 1;
    public static final int INDEX_TEACHERS_FIRST_NAME = 2;
    public static final int INDEX_TEACHERS_LAST_NAME = 3;
    public static final int INDEX_TEACHERS_TAUGHT_SUBJECT = 4;
    public static final int INDEX_TEACHERS_HEAD_OF_CLASS = 5;
    public static final int INDEX_TEACHERS_USER_ID = 6;

    public static final String TABLE_DIRECTORS = "directors";
    public static final String COLUMN_DIRECTORS_DIRECTOR_ID = "director_id";
    public static final String COLUMN_DIRECTORS_FIRST_NAME = "first_name";
    public static final String COLUMN_DIRECTORS_LAST_NAME = "last_name";
    public static final String COLUMN_DIRECTORS_TYPE = "type";
    public static final String COLUMN_DIRECTORS_USER_ID = "user_id";
    public static final int INDEX_DIRECTORS_DIRECTOR_ID = 1;
    public static final int INDEX_DIRECTORS_FIRST_NAME = 2;
    public static final int INDEX_DIRECTORS_LAST_NAME = 3;
    public static final int INDEX_DIRECTORS_TYPE = 4;
    public static final int INDEX_DIRECTORS_USER_ID = 5;

    // SELECT user_id, email_address, password, account_type FROM users
    // WHERE email_address="?";
    private static final String QUERY_LOGIN_CREDENTIALS = "SELECT " + COLUMN_USERS_USER_ID + ", " + COLUMN_USERS_EMAIL_ADDRESS +
            ", " + COLUMN_USERS_PASSWORD + ", " + COLUMN_USERS_ACCOUNT_TYPE + " FROM " + TABLE_USERS +
            " WHERE " + COLUMN_USERS_EMAIL_ADDRESS + "=?";

    // SELECT * FROM users
    // WHERE user_id=1
    private static final String QUERY_LOGGED_USER_INFO = "SELECT * FROM " + TABLE_USERS +
            " WHERE " + COLUMN_USERS_USER_ID + "=?";

    // SELECT * FROM users
    // WHERE email_address=?
    private static final String QUERY_USER_IF_EXIST_BY_EMAIL = "SELECT * FROM " + TABLE_USERS +
            " WHERE " + COLUMN_USERS_EMAIL_ADDRESS + "=?";

    // SELECT student_id, first_name, last_name, age, classroom, number_in_class, speciality, users.user_id FROM students
    // INNER JOIN users ON students.user_id = users.user_id
    // WHERE students.user_id = ?
    private static final String QUERY_STUDENT_RECORDS = "SELECT " + COLUMN_STUDENTS_STUDENT_ID + ", " + COLUMN_STUDENTS_FIRST_NAME + ", " +
            COLUMN_STUDENTS_LAST_NAME + ", " + COLUMN_STUDENTS_AGE + ", " + COLUMN_STUDENTS_CLASSROOM + ", " + COLUMN_STUDENTS_NUMBER_IN_CLASS + ", " +
            COLUMN_STUDENTS_SPECIALITY + ", " + TABLE_USERS + "." + COLUMN_USERS_USER_ID + " FROM " + TABLE_STUDENTS +
            " INNER JOIN " + TABLE_USERS + " ON " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_USER_ID + " = " + TABLE_USERS + "." + COLUMN_USERS_USER_ID +
            " WHERE " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_USER_ID + "=?";

    // SELECT teacher_id, first_name, last_name, taught_subject, head_of_class, users.user_id FROM teachers
    // INNER JOIN users ON teachers.user_id = users.user_id
    // WHERE teachers.user_id = ?
    private static final String QUERY_TEACHER_RECORDS = "SELECT " + COLUMN_TEACHERS_TEACHER_ID + ", " + COLUMN_TEACHERS_FIRST_NAME + ", " +
            COLUMN_TEACHERS_LAST_NAME + ", " + COLUMN_TEACHERS_TAUGHT_SUBJECT + ", " + COLUMN_TEACHERS_HEAD_OF_CLASS +
            ", " + TABLE_USERS + "." + COLUMN_USERS_USER_ID + " FROM " + TABLE_TEACHERS +
            " INNER JOIN " + TABLE_USERS + " ON " + TABLE_TEACHERS + "." + COLUMN_TEACHERS_USER_ID + " = " + TABLE_USERS + "." + COLUMN_USERS_USER_ID +
            " WHERE " + TABLE_TEACHERS + "." + COLUMN_TEACHERS_USER_ID + "=?";

    // SELECT director_id, first_name, last_name, type, users.user_id FROM directors
    // INNER JOIN users ON directors.user_id = users.user_id
    // WHERE directors.user_id = ?
    private static final String QUERY_DIRECTOR_RECORDS = "SELECT " + COLUMN_DIRECTORS_DIRECTOR_ID + ", "  + COLUMN_DIRECTORS_FIRST_NAME + ", " +
            COLUMN_DIRECTORS_LAST_NAME + ", " + COLUMN_DIRECTORS_TYPE + ", " + TABLE_USERS + "." + COLUMN_USERS_USER_ID + " FROM " + TABLE_DIRECTORS +
            " INNER JOIN " + TABLE_USERS + " ON " + TABLE_DIRECTORS + "." + COLUMN_DIRECTORS_USER_ID + " = " + TABLE_USERS + "." + COLUMN_USERS_USER_ID +
            " WHERE " + TABLE_DIRECTORS + "." + COLUMN_DIRECTORS_USER_ID + "=?";


    // SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1;
    private static final String QUERY_USERS_TABLE_LAST_USER_ID = "SELECT " + COLUMN_USERS_USER_ID + " FROM " + TABLE_USERS +
            " ORDER BY " + COLUMN_USERS_USER_ID + " DESC LIMIT 1";

    // SELECT student_id FROM students ORDER BY student_id DESC LIMIT 1;
    private static final String QUERY_LAST_STUDENT_ID = "SELECT " + COLUMN_STUDENTS_STUDENT_ID + " FROM " + TABLE_STUDENTS +
            " ORDER BY " + COLUMN_STUDENTS_STUDENT_ID + " DESC LIMIT 1";
    
    // SELECT seq FROM sqlite_sequence
    // WHERE name=?;
    private static final String QUERY_SQLITE_SEQUENCE = "SELECT seq FROM " + TABLE_SQLITE_SEQUENCE +
            " WHERE " + COLUMN_SQLITE_SEQUENCE_name + "=?";

    // SELECT * FROM users;
    private static final String QUERY_RECORDS_FROM_TABLE_USERS = "SELECT * FROM " + TABLE_USERS;

    // SELECT * FROM students;
    private static final String QUERY_RECORDS_FROM_TABLE_STUDENTS = "SELECT * FROM " + TABLE_STUDENTS;

    // SELECT * FROM teachers
    private static final String QUERY_RECORDS_FROM_TABLE_TEACHERS = "SELECT * FROM " + TABLE_TEACHERS;

    // SELECT * FROM directors;
    private static final String QUERY_RECORDS_FROM_TABLE_DIRECTORS = "SELECT * FROM " + TABLE_DIRECTORS;

    // SELECT ? FROM ? ORDER BY ? DESC LIMIT 1;
    private static final String QUERY_LAST_TABLE_ID = "SELECT " + "?" + " FROM " + "?" +
            " ORDER BY " + "?" + " DESC LIMIT 1";

    // SELECT teacher_id FROM teachers ORDER BY teacher_id DESC LIMIT 1;
    private static final String QUERY_LAST_TEACHER_ID = "SELECT " + COLUMN_TEACHERS_TEACHER_ID + " FROM " + TABLE_TEACHERS +
            " ORDER BY " + COLUMN_TEACHERS_TEACHER_ID + " DESC LIMIT 1";

    // SELECT director_id FROM directors ORDER BY director_id DESC LIMIT 1;
    private static final String QUERY_LAST_DIRECTOR_ID = " SELECT " + COLUMN_DIRECTORS_DIRECTOR_ID + " FROM " + TABLE_DIRECTORS +
            " ORDER BY " + COLUMN_DIRECTORS_DIRECTOR_ID + " DESC LIMIT 1";

    // SELECT user_id FROM teachers ORDER BY user_id DESC LIMIT 1;
    private static final String QUERY_TEACHERS_TABLE_LAST_USED_ID = "SELECT " + COLUMN_TEACHERS_USER_ID +
            " FROM " + TABLE_TEACHERS +
            " ORDER BY " + COLUMN_TEACHERS_USER_ID + " DESC LIMIT 1";

    // INSERT INTO users (email_address, password, account_type)
    // VALUES(?, ?, ?, ?, ?);
    private static final String INSERT_USER_INTO_USERS = "INSERT INTO " + TABLE_USERS +
            " (" + COLUMN_USERS_EMAIL_ADDRESS + ", "+ COLUMN_USERS_PASSWORD + ", " + COLUMN_USERS_ACCOUNT_TYPE +
            ") VALUES (?, ?, ?)";

    // INSERT INTO students (first_name, last_name, age, classroom, number_in_class, speciality, user_id)
    // VALUES ('Georgi', 'Ivanov', 18, '12B', 12, 'Application Developer', 4);
    private static final String INSERT_STUDENT_INTO_STUDENTS = "INSERT INTO " + TABLE_STUDENTS +
            " (" + COLUMN_STUDENTS_FIRST_NAME + ", " + COLUMN_STUDENTS_LAST_NAME + ", " +
            COLUMN_STUDENTS_AGE + ", " + COLUMN_STUDENTS_CLASSROOM + ", " + COLUMN_STUDENTS_NUMBER_IN_CLASS +
            ", " + COLUMN_STUDENTS_SPECIALITY + ", " + COLUMN_STUDENTS_USER_ID + ") " +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    // INSERT INTO teachers (first_name, last_name, taught_subject, head_of_class, user_id)
    // VALUES ('Georgi', 'Ivanov', 'Biology', '12A', 3);
    private static final String INSERT_TEACHER_INTO_TEACHERS = "INSERT INTO " + TABLE_TEACHERS +
            " (" + COLUMN_TEACHERS_FIRST_NAME + ", " + COLUMN_TEACHERS_LAST_NAME + ", " +
            COLUMN_TEACHERS_TAUGHT_SUBJECT + ", " + COLUMN_TEACHERS_HEAD_OF_CLASS + ", " +
            COLUMN_TEACHERS_USER_ID + ") " + " VALUES (?, ?, ?, ?, ?)";

    // INSERT INTO directors (first_name, last_name, type, user_id)
    // VALUES (?, ?, ?, ?);
    private static final String INSERT_DIRECTOR_INTO_DIRECTORS = "INSERT INTO " + TABLE_DIRECTORS +
            " (" + COLUMN_DIRECTORS_FIRST_NAME + ", " + COLUMN_DIRECTORS_LAST_NAME + ", " +
            COLUMN_DIRECTORS_TYPE + ", " + COLUMN_DIRECTORS_USER_ID + ") " + "VALUES (?, ?, ?, ?)";


    // DELETE FROM users
    // WHERE  user_id=?
    private static final String DELETE_USER_FROM_USERS_TABLE = "DELETE FROM " + TABLE_USERS +
            " WHERE " + COLUMN_USERS_USER_ID + "=?";

    // DELETE FROM students
    // WHERE user_id = ?
    private static final String DELETE_STUDENT_FROM_STUDENTS_TABLE = "DELETE FROM " + TABLE_STUDENTS +
            " WHERE " + COLUMN_STUDENTS_USER_ID + "=?";

    // DELETE FROM teachers
    // WHERE user_id = ?
    private static final String DELETE_TEACHER_FROM_TEACHERS_TABLE = "DELETE FROM " + TABLE_TEACHERS +
            " WHERE " + COLUMN_TEACHERS_USER_ID + "=?";

    // DELETE FROM directors
    // WHERE user_id = ?
    private static final String DELETE_DIRECTOR_FROM_DIRECTORS_TABLE = "DELETE FROM " + TABLE_DIRECTORS +
            " WHERE " + COLUMN_DIRECTORS_USER_ID + "=?";

    // Update users
    // SET password="123456ADS"
    // WHERE email_address="zaharimikov2001@gmail.com" AND password="12345ASD"
    private static final String UPDATE_CHANGE_PASSWORD = "UPDATE " + TABLE_USERS +
            " SET " + COLUMN_USERS_PASSWORD + "=?" +
            " WHERE " + COLUMN_USERS_EMAIL_ADDRESS + "=?" + " AND " + COLUMN_USERS_PASSWORD + "=?";

    // UPDATE sqlite_sequence SET seq = ? WHERE name = ?;
    public static final String UPDATE_SQLITE_SEQUENCE_SEQ = "UPDATE " + TABLE_SQLITE_SEQUENCE + " SET " + COLUMN_SQLITE_SEQUENCE_SEQ + " = ?" +
            " WHERE " + COLUMN_SQLITE_SEQUENCE_name + " = ?";

    private Connection conn;
    private PreparedStatement query_login_credentials;
    private PreparedStatement query_logged_user_info;
    private PreparedStatement query_user_if_exist_by_email;
    private PreparedStatement query_student_records_by_user_id;
    private PreparedStatement query_users_table_last_user_id;
    private PreparedStatement query_records_from_table_users;
    private PreparedStatement query_records_from_table_students;
    private PreparedStatement query_records_from_table_teachers;
    private PreparedStatement query_last_student_id;
    private PreparedStatement query_teachers_table_last_user_id;
    private PreparedStatement query_last_teacher_id;
    private PreparedStatement query_teacher_records_by_user_id;
    private PreparedStatement query_records_from_table_directors;
    private PreparedStatement query_last_director_id;
    private PreparedStatement query_director_records_by_user_id;
    private PreparedStatement query_sqlite_seq;
    private PreparedStatement insert_user;
    private PreparedStatement insert_student;
    private PreparedStatement insert_teacher;
    private PreparedStatement insert_director;
    private PreparedStatement delete_user;
    private PreparedStatement delete_student;
    private PreparedStatement delete_teacher;
    private PreparedStatement delete_director;
    private PreparedStatement update_change_password;
    private PreparedStatement update_sqlite_sequence_seq;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            query_login_credentials = conn.prepareStatement(QUERY_LOGIN_CREDENTIALS);
            query_logged_user_info = conn.prepareStatement(QUERY_LOGGED_USER_INFO);
            query_user_if_exist_by_email = conn.prepareStatement(QUERY_USER_IF_EXIST_BY_EMAIL);
            query_student_records_by_user_id = conn.prepareStatement(QUERY_STUDENT_RECORDS);
            query_users_table_last_user_id = conn.prepareStatement(QUERY_USERS_TABLE_LAST_USER_ID);
            query_records_from_table_users = conn.prepareStatement(QUERY_RECORDS_FROM_TABLE_USERS);
            query_records_from_table_students = conn.prepareStatement(QUERY_RECORDS_FROM_TABLE_STUDENTS);
            query_last_student_id = conn.prepareStatement(QUERY_LAST_STUDENT_ID);
            query_teachers_table_last_user_id = conn.prepareStatement(QUERY_TEACHERS_TABLE_LAST_USED_ID);
            query_records_from_table_teachers = conn.prepareStatement(QUERY_RECORDS_FROM_TABLE_TEACHERS);
            query_last_teacher_id = conn.prepareStatement(QUERY_LAST_TEACHER_ID);
            query_teacher_records_by_user_id = conn.prepareStatement(QUERY_TEACHER_RECORDS);
            query_records_from_table_directors = conn.prepareStatement(QUERY_RECORDS_FROM_TABLE_DIRECTORS);
            query_last_director_id = conn.prepareStatement(QUERY_LAST_DIRECTOR_ID);
            query_director_records_by_user_id = conn.prepareStatement(QUERY_DIRECTOR_RECORDS);
            insert_user = conn.prepareStatement(INSERT_USER_INTO_USERS);
            insert_student = conn.prepareStatement(INSERT_STUDENT_INTO_STUDENTS);
            insert_teacher = conn.prepareStatement(INSERT_TEACHER_INTO_TEACHERS);
            insert_director = conn.prepareStatement(INSERT_DIRECTOR_INTO_DIRECTORS);
            delete_user = conn.prepareStatement(DELETE_USER_FROM_USERS_TABLE);
            delete_student = conn.prepareStatement(DELETE_STUDENT_FROM_STUDENTS_TABLE);
            delete_teacher = conn.prepareStatement(DELETE_TEACHER_FROM_TEACHERS_TABLE);
            delete_director = conn.prepareStatement(DELETE_DIRECTOR_FROM_DIRECTORS_TABLE);
            update_change_password = conn.prepareStatement(UPDATE_CHANGE_PASSWORD);
            update_sqlite_sequence_seq = conn.prepareStatement(UPDATE_SQLITE_SEQUENCE_SEQ);
            query_sqlite_seq = conn.prepareStatement(QUERY_SQLITE_SEQUENCE);
            return true;
        } catch (SQLException e) {
            System.out.println("There is a problem " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        closeQuery(query_login_credentials);
        closeQuery(query_logged_user_info);
        closeQuery(query_user_if_exist_by_email);
        closeQuery(query_student_records_by_user_id);
        closeQuery(query_users_table_last_user_id);
        closeQuery(query_records_from_table_users);
        closeQuery(query_records_from_table_students);
        closeQuery(query_last_student_id);
        closeQuery(query_teachers_table_last_user_id);
        closeQuery(query_last_teacher_id);
        closeQuery(query_teacher_records_by_user_id);
        closeQuery(query_records_from_table_directors);
        closeQuery(query_last_director_id);
        closeQuery(query_sqlite_seq);
        closeQuery(query_director_records_by_user_id);
        closeQuery(insert_user);
        closeQuery(insert_student);
        closeQuery(insert_teacher);
        closeQuery(insert_director);
        closeQuery(delete_user);
        closeQuery(delete_student);
        closeQuery(delete_teacher);
        closeQuery(delete_director);
        closeQuery(update_change_password);
        closeQuery(update_sqlite_sequence_seq);
        closeConnection(conn);
    }

    private void closeQuery(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean checkForRecordsInTableUsers() {
        try {
            ResultSet results = query_records_from_table_users.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Query 'query_records_from_table' failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkForRecordsInTableStudents() {
        try {
            ResultSet results = query_records_from_table_students.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Query 'query_records_from_table_students' failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkForRecordsInTableTeachers() {
        try {
            ResultSet results = query_records_from_table_teachers.executeQuery();

            if (results.next()) {
               return true;
           }
        } catch (SQLException e) {
            System.out.println("Query 'query_records_from_table_teachers' failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkForRecordsInTableDirectors() {
        try {
            ResultSet results = query_records_from_table_directors.executeQuery();

            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Query 'query_records_from_table_directors' failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertUserIntoUsersTable(User user) {
        String emailAddress = user.getEmailAddress();

        String accountType = user.getAccountType();
        String password = user.getPassword();

        String retrievedEmailAddress = "";

        try {
            query_user_if_exist_by_email.setString(1, emailAddress);
            ResultSet results = query_user_if_exist_by_email.executeQuery();
            if (results.next()) {
                retrievedEmailAddress = results.getString(INDEX_USERS_EMAIL_ADDRESS);
                if (retrievedEmailAddress != null) {
                    System.out.println("Email is already in use");
                    return false;
                }
            }

            if (!checkForRecordsInTableUsers()) {
                int initialValue = 0;
                user.resetUserId();
                update_sqlite_sequence_seq.setInt(1, initialValue);
                update_sqlite_sequence_seq.setString(1, TABLE_USERS);
                update_sqlite_sequence_seq.executeUpdate();
            }

            insert_user.setString(1, emailAddress);
            insert_user.setString(2, password);
            insert_user.setString(3, accountType);

            insert_user.executeUpdate();

            if (accountType.equalsIgnoreCase("Student")) {
                processStudent(user);
            } else if (accountType.equalsIgnoreCase("Teacher")) {
                processTeacher(user);
            } else if (accountType.equalsIgnoreCase("Director")) {
                processDirector(user);
            }
        } catch (SQLException e) {
            System.out.println("Failed insertion into users table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void processStudent(User user) {
        try {
            Student student = returnNewStudent();
            ResultSet result = query_users_table_last_user_id.executeQuery();
            int userId = result.getInt(INDEX_USERS_USER_ID);
            student.setUser_id(userId);

            if (!checkForRecordsInTableStudents()) {
                int initialValue = 0;
                student.resetStudentId();
                update_sqlite_sequence_seq.setInt(1, initialValue);
                update_sqlite_sequence_seq.setString(2, TABLE_STUDENTS);
                update_sqlite_sequence_seq.executeUpdate();
            }

            insertIntoStudents(student);
            System.out.println("User " + user.getEmailAddress() + " successfully registered!");
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processTeacher(User user) {
        try {
            Teacher teacher = returnNewTeacher();
            ResultSet teacherUserId = query_users_table_last_user_id.executeQuery();
            int userId = teacherUserId.getInt(INDEX_USERS_USER_ID);
            teacher.setUser_id(userId);

            if (!checkForRecordsInTableTeachers()) {
                int initialValue = 0;
                teacher.resetTeacherId();

                update_sqlite_sequence_seq.setInt(1, initialValue);
                update_sqlite_sequence_seq.setString(2, TABLE_TEACHERS);
                update_sqlite_sequence_seq.executeUpdate();
            }

            insertIntoTeachers(teacher);
            System.out.println("Teacher " + user.getEmailAddress() + " successfully registered!");

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processDirector(User user) {
        try {
            Director director = returnNewDirector();
            ResultSet directorUserId = query_users_table_last_user_id.executeQuery();
            int userId = directorUserId.getInt(INDEX_USERS_USER_ID);
            director.setUserId(userId);


            if (!checkForRecordsInTableDirectors()) {
                int initialValue = 0;
                director.resetDirectorId();
                update_sqlite_sequence_seq.setInt(1, initialValue);
                update_sqlite_sequence_seq.setString(2, TABLE_DIRECTORS);
                update_sqlite_sequence_seq.executeUpdate();
            }
            insertIntoDirectors(director);
            System.out.println("Director " + user.getEmailAddress() + " successfully registered!");

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Student returnNewStudent() {
        Student student = new Student();
        String firstName = student.validateFirstName();
        String lastname = student.validateLastName();
        int age = student.validateAge();
        int numberInClass = student.validateNumberOfClass();
        String speciality = student.validateSpeciality();
        String classroom = student.validateClassroom();

        return new Student(firstName, lastname, age, numberInClass, speciality, classroom);
    }

    private Teacher returnNewTeacher() {
        Teacher teacher = new Teacher();
        String firstName = teacher.validateFirstName();
        String lastName = teacher.validateLastName();
        String taughtSubject = teacher.validateTaughtSubject();
        String headOfClass = teacher.validateHeadOfClass();

        return new Teacher(firstName, lastName, taughtSubject, headOfClass);
    }

    private Director returnNewDirector() {
        Director director = new Director();
        String firstName = director.validateFirstName();
        String lastName = director.validateLastName();
        String type = director.validateType();

        return new Director(firstName, lastName, type);
    }

    public boolean checkLoginCredentials(String username, String password) {
        try {
            query_login_credentials.setString(1, username);
            ResultSet results = query_login_credentials.executeQuery();
            String dbUsernameRetrieved = "";
            String dbPasswordRetrieved = "";

            if (results.next()) {
                dbUsernameRetrieved = results.getString(INDEX_USERS_EMAIL_ADDRESS);
                dbPasswordRetrieved = results.getString(INDEX_USERS_PASSWORD);
            } else {
                return false;
            }

            if (dbUsernameRetrieved != null) {
                return dbUsernameRetrieved.equals(username) && dbPasswordRetrieved.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int retrieveLoginUserId(String username, String password) {
        int index = 0;

        if (checkLoginCredentials(username, password)) {
            try {
                query_login_credentials.setString(1, username);
                ResultSet results = query_login_credentials.executeQuery();
                index = Integer.parseInt(results.getString(INDEX_USERS_USER_ID));

                return index;
            } catch (SQLException e) {
                System.out.println("Query 'query_login_credentials' failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return 0;
    }

    public User getLoggedUser(String username, String pass) {
        int userId = 0;
        String emailAddress = "";
        String accountType = "";
        String password = "";

        try {
            int index = retrieveLoginUserId(username, pass);

            query_logged_user_info.setInt(1, index);

            ResultSet results = query_logged_user_info.executeQuery();

            userId = results.getInt(INDEX_USERS_USER_ID);
            emailAddress = results.getString(INDEX_USERS_EMAIL_ADDRESS);
            password = results.getString(INDEX_USERS_PASSWORD);
            accountType = results.getString(INDEX_USERS_ACCOUNT_TYPE);
        } catch (SQLException e) {
            System.out.println("Query 'query_logged_user_info' failed: " + e.getMessage());
            e.printStackTrace();
        }
        return new User(userId, emailAddress, password, accountType);
    }

    public void deleteUserFromUsersTable(User user) {
        if (user.getAccountType().equalsIgnoreCase("Student")) {
            deleteStudentFromStudentsTable(user);
        } else if (user.getAccountType().equalsIgnoreCase("Teacher")) {
            deleteTeacherFromTeachersTable(user);
        } else if (user.getAccountType().equalsIgnoreCase("Director")) {
            deleteDirectorFromDirectorsTable(user);
        }
        deleteUser(user);
    }

    private void deleteUser(User user) {
        try {
            delete_user.setInt(1, user.getUserId());
            int deletedUsers = delete_user.executeUpdate();
            if (deletedUsers != 1) {
                System.out.println("There is no such user!");
                return;
            }
            System.out.println("Successfully deleted user: " + user.getEmailAddress());
        } catch (SQLException e) {
            System.out.println("Delete query failed: " + e.getMessage()); {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudentFromStudentsTable(User user) {
        try {
            query_student_records_by_user_id.setInt(1, user.getUserId());
            ResultSet results = query_student_records_by_user_id.executeQuery();

            String firstName = results.getString(INDEX_STUDENTS_FIRST_NAME);
            String lastName = results.getString(INDEX_STUDENTS_LAST_NAME);

            String fullName = firstName + " " + lastName;

            if (results.next()) {
                delete_student.setInt(1, user.getUserId());
                int deletedStudentsRows = delete_student.executeUpdate();
                if (deletedStudentsRows != 1) {
                    System.out.println("There is no such student!");
                    return;
                }
                System.out.println("Successfully deleted student: " + fullName);
            } else {
                System.out.println("Query doesn't retrieve any information");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("Query 'query_student_records_by_user_id' produce null: " + npe.getMessage());
            npe.printStackTrace();
        }
    }

    public void deleteTeacherFromTeachersTable(User user) {
        try {
            query_teacher_records_by_user_id.setInt(1, user.getUserId());
            ResultSet results = query_teacher_records_by_user_id.executeQuery();

            String firstName = results.getString(INDEX_TEACHERS_FIRST_NAME);
            String lastName = results.getString(INDEX_TEACHERS_LAST_NAME);

            String fullName = firstName + " " + lastName;

            if (results.next()) {
                delete_teacher.setInt(1, user.getUserId());
                int deletedTeachersRows = delete_teacher.executeUpdate();
                if (deletedTeachersRows != 1) {
                    System.out.println("There is no such teacher!");
                    return;
                }
                System.out.println("Successfully deleted teacher: " + fullName);
            } else {
                System.out.println("Query doesn't retrieve any information");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("Query 'query_teacher_records_by_user_id' produce null: " + npe.getMessage());
            npe.printStackTrace();
        }
    }

    public void deleteDirectorFromDirectorsTable(User user) {
        try {
            query_director_records_by_user_id.setInt(1, user.getUserId());
            ResultSet results = query_director_records_by_user_id.executeQuery();

            String firstName = results.getString(INDEX_DIRECTORS_FIRST_NAME);
            String lastName = results.getString(INDEX_DIRECTORS_LAST_NAME);

            String fullName = firstName + " " + lastName;

            if (results.next()) {
                delete_director.setInt(1, user.getUserId());
                int deletedDirectorsRows = delete_director.executeUpdate();
                if (deletedDirectorsRows != 1) {
                    System.out.println("There is no such director!");
                    return;
                }
                System.out.println("Successfully deleted director: " + fullName);
            } else {
                System.out.println("Query doesn't retrieve any information!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("Query 'query_director_records_by_user_id' produce null: " + npe.getMessage());
            npe.printStackTrace();
        }
    }

    private void updateSqliteStudentsSequence() {
        try {
            query_sqlite_seq.setString(1, TABLE_STUDENTS);
            ResultSet results = query_sqlite_seq.executeQuery();
            int sqliteSequenceIndex = results.getInt(1);

            ResultSet lastStudentIdResult = query_last_student_id.executeQuery();
            int lastStudentIndex = lastStudentIdResult.getInt(INDEX_STUDENTS_STUDENT_ID);

            if (sqliteSequenceIndex > lastStudentIndex) {
                update_sqlite_sequence_seq.setInt(1, lastStudentIndex);
                update_sqlite_sequence_seq.setString(2, TABLE_STUDENTS);
                update_sqlite_sequence_seq.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateSqliteTeachersSequence() {
        try {
            query_sqlite_seq.setString(1, TABLE_TEACHERS);
            ResultSet results = query_sqlite_seq.executeQuery();
            int sqliteSequenceIndex = results.getInt(1);

            ResultSet lastTeacherIdResult = query_last_teacher_id.executeQuery();
            int lastTeacherIndex = lastTeacherIdResult.getInt(INDEX_TEACHERS_TEACHER_ID);

            if (sqliteSequenceIndex > lastTeacherIndex) {
                update_sqlite_sequence_seq.setInt(1, lastTeacherIndex);
                update_sqlite_sequence_seq.setString(2, TABLE_TEACHERS);
                update_sqlite_sequence_seq.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateSqliteDirectorsSequence() {
        try {
            query_sqlite_seq.setString(1, TABLE_DIRECTORS);
            ResultSet resulsts = query_sqlite_seq.executeQuery();
            int sqliteSequenceIndex = resulsts.getInt(1);

            ResultSet lastDirectorIdResult = query_last_director_id.executeQuery();
            int lastDirectorIndex = lastDirectorIdResult.getInt(INDEX_DIRECTORS_DIRECTOR_ID);

            if (sqliteSequenceIndex > lastDirectorIndex) {
                update_sqlite_sequence_seq.setInt(1, lastDirectorIndex);
                update_sqlite_sequence_seq.setString(2, TABLE_DIRECTORS);
                update_sqlite_sequence_seq.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void changePassword(User user) {
        Scanner scanner = new Scanner(System.in);

        String emailAddress = user.getEmailAddress();
        String password = user.getPassword();

        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();

        String retrievedPassword = "";

        if (currentPassword.equals(password)) {
            try {
                update_change_password.setString(1, newPassword);
                update_change_password.setString(2, emailAddress);
                update_change_password.setString(3, password);
                update_change_password.executeUpdate();
                user.setPassword(newPassword);
                System.out.println("Your password have been successfully changed!");
            } catch (SQLException e) {
                System.out.println("Failed query in change password(): " + e.getMessage());
            }
        } else {
            try {
                query_user_if_exist_by_email.setString(1, emailAddress);
                ResultSet results = query_user_if_exist_by_email.executeQuery();
                if (results.next()) {
                    retrievedPassword = results.getString(INDEX_USERS_PASSWORD);
                    System.out.println("retrievedPassword = " + retrievedPassword);
                }
                if (!currentPassword.equals(retrievedPassword)) {
                    System.out.println("Incorrect current password!");
                }
            } catch (SQLException e) {
                System.out.println("Failed query_user_if_exist_by_email in change password(): " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void insertIntoStudents(Student student) {
        try {
            insert_student.setString(1, student.getFirstName());
            insert_student.setString(2, student.getLastName());
            insert_student.setInt(3, student.getAge());
            insert_student.setString(4, student.getClassroom());
            insert_student.setInt(5, student.getNumberInClass());
            insert_student.setString(6, student.getSpeciality());
            insert_student.setInt(7, student.getUser_id());

            insert_student.executeUpdate();
            updateSqliteStudentsSequence();
        } catch (SQLException e) {
            System.out.println("Failed insertion into students table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertIntoTeachers(Teacher teacher) {
        try {
            insert_teacher.setString(1, teacher.getFirstName());
            insert_teacher.setString(2, teacher.getLastName());
            insert_teacher.setString(3, teacher.getTaughtSubject());
            insert_teacher.setString(4, teacher.getHeadOfClass());
            insert_teacher.setInt(5, teacher.getUser_id());

            insert_teacher.executeUpdate();
            updateSqliteTeachersSequence();
        } catch (SQLException e) {
            System.out.println("Failed insertion into teachers table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertIntoDirectors(Director director) {
        try {
            insert_director.setString(1, director.getFirstName());
            insert_director.setString(2, director.getLastName());
            insert_director.setString(3, director.getType());
            insert_director.setInt(4, director.getUserId());

            insert_director.executeUpdate();
            updateSqliteDirectorsSequence();
        } catch (SQLException e) {
            System.out.println("Failed insertion into directors table: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
