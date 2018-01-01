/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.utils;

/**
 *
 * @author hesham
 */
public enum AlumniServEnum {
    // Column Names: Students
    STUDENT_ID("STUDENT_ID"),
    STUDENT_FIRST_NAME("STUDENT_FIRST_NAME"),
    STUDENT_LAST_NAME("STUDENT_LAST_NAME"),
    DATE_OF_BIRTH("DATE_OF_BIRTH"),
    NATIONALITY("NATIONALITY"),
    SCHOOL_ID("SCHOOL_ID"),
    SCHOOL_NAME("SCHOOL_NAME"),
    DEPARTMENT_ID("DEPARTMENT_ID"),
    DEPARTMENT_NAME("DEPARTMENT_NAME"),
    DEGREE_ID("DEGREE_ID"),
    DEGREE_NAME("DEGREE_NAME"),
    GPA("GPA"),
    GENDER_ID("GENDER_ID"),
    GENDER_NAME("GENDER_NAME"),
    STATUS_ID("STATUS_ID"),
    STATUS_NAME("STATUS_NAME"),
    YEARS_OF_EXPERIENCE("YEARS_OF_EXPERIENCE"),
    FACEBOOK("FACEBOOK"),
    LINKEDIN("LINKEDIN"),
    GRADUATION_YEAR_ID("GRADUATION_YEAR_ID"),
    YEAR_NAME("YEAR_NAME"),
    GRADUATION_SEMESTER_ID("GRADUATION_SEMESTER_ID"),
    SEMESTER_NAME("SEMESTER_NAME"),
    //Column Names: EMPLOYEE:
    EMPLOYEE_ID("EMPLOYEE_ID"),
    EMPLOYEE_FIRST_NAME("EMPLOYEE_FIRST_NAME"),
    EMPLOYEE_LAST_NAME("EMPLOYEE_LAST_NAME"),
    USER_NAME("USER_NAME"),
    PASSWORD("USER_PASSWORD"),
    EMPLOYEE_DEPARTMENT("GROUP_NAME"),
    //Column Names: Student_Job:
    STUDENT_POSITION("STUDENT_POSITION"),
    COMPANY_NAME("COMPANY_NAME"),
    COUNTRY("COUNTRY"),
    CITY("CITY"),
    START_DATE("START_DATE"),
    //Column Names: user_group:
    USER_ID("USER_ID"),
    GROUP_ID("GROUP_ID"),
    //COLUMNS: email
    STUDENT_EMAIL("STUDENT_EMAIL"),
    //Queries: STUDENT
    GET_ALL_STUDENTS("select STUDENTS.STUDENT_ID, STUDENTS.STUDENT_FIRST_NAME,STUDENTS.STUDENT_LAST_NAME, STUDENTS.DATE_OF_BIRTH, "
            + "STUDENTS.NATIONALITY, STUDENTS.GPA, STUDENTS.YEARS_OF_EXPERIENCE, STUDENTS.FACEBOOK, STUDENTS.LINKEDIN, EMAIL.STUDENT_EMAIL,"
            + " STUDENT_STATUS.STATUS_ID, STUDENT_STATUS.STATUS_NAME , DEEGREE.DEGREE_ID, DEEGREE.DEGREE_NAME, "
            + "DEPARTMENTS.DEPARTMENT_ID, DEPARTMENTS.DEPARTMENT_NAME, "
            + "GENDER.GENDER_ID,GENDER.GENDER_NAME, GRADUATION_SEMESTER.GRADUATION_SEMESTER_ID, "
            + "GRADUATION_SEMESTER.SEMESTER_NAME, GRADUATION_YEAR.GRADUATION_YEAR_ID, GRADUATION_YEAR.YEAR_NAME, SCHOOLS.SCHOOL_ID, SCHOOLS.SCHOOL_NAME "
            + "from students, STUDENT_STATUS , DEEGREE, DEPARTMENTS, EMAIL, "
            + "GENDER,GRADUATION_SEMESTER,GRADUATION_YEAR,SCHOOLS "
            + "where students.status_id = student_status.status_id "
            + "and students.DEGREE_ID = DEEGREE.DEGREE_ID and students.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID "
            + "and students.GENDER_ID = GENDER.GENDER_ID and students.GRADUATION_SEMESTER_ID = GRADUATION_SEMESTER.GRADUATION_SEMESTER_ID "
            + "and students.GRADUATION_YEAR_ID = GRADUATION_YEAR.GRADUATION_YEAR_ID "
            + "and students.SCHOOL_ID = SCHOOLS.SCHOOL_ID "
            + "AND STUDENTS.STUDENT_ID= EMAIL.STUDENT_ID"),
    GET_STUDENT_BY_ID("SELECT * FROM STUDENTS WHERE STUDENT_ID=?"),
    EDIT_STUDENT("UPDATE STUDENTS SET STUDENT_FIRST_NAME=?, "
            + "STUDENT_LAST_NAME=?, "
            + "DATE_OF_BIRTH=?, "
            + "NATIONALITY=?, "
            + "SCHOOL_ID=?, "
            + "DEPARTMENT_ID=?, "
            + "DEGREE_ID=?, "
            + "GPA=?, "
            + "GENDER_ID=?, "
            + "STATUS_ID=?, "
            + "YEARS_OF_EXPERIENCE=?, "
            + "FACEBOOK=?, "
            + "LINKEDIN=?, "
            + "GRADUATION_YEAR_ID=?, "
            + "GRADUATION_SEMESTER_ID=?, "
            + "WHERE STUDENT_ID=?"),
    DELETE_STUDENT("DELETE FROM STUDENTS WHERE STUDENT_ID=?"),
    INSERT_STUDENT("INSERT INTO STUDENTS (STUDENT_ID, "
            + "STUDENT_FIRST_NAME, "
            + "STUDENT_LAST_NAME, "
            + "DATE_OF_BIRTH, "
            + "NATIONALITY, "
            + "SCHOOL_ID, "
            + "DEPARTMENT_ID, "
            + "DEGREE_ID, "
            + "GPA, "
            + "GENDER_ID, "
            + "STATUS_ID, "
            + "YEARS_OF_EXPERIENCE, "
            + "FACEBOOK, "
            + "LINKEDIN, "
            + "GRADUATION_YEAR_ID, "
            + "GRADUATION_SEMESTER_ID) VALUES ((SELECT MAX(STUDENT_ID) FROM STUDENTS)+1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    //Queries: Employee:
    GET_ALL_EMPLOYEES("SELECT EMPLOYEE.EMPLOYEE_ID, EMPLOYEE.EMPLOYEE_FIRST_NAME , EMPLOYEE.EMPLOYEE_LAST_NAME, "
            + "USERS.USER_ID, USERS.USER_NAME, USERS.USER_PASSWORD, GROUPS.GROUP_ID, GROUPS.GROUP_NAME "
            + "FROM EMPLOYEE , USER_GROUP , GROUPS, USERS WHERE ("
            + "USER_GROUP.USER_ID = EMPLOYEE.EMPLOYEE_ID AND USERS.USER_ID=employee.EMPLOYEE_ID) "
            + "AND GROUPS.GROUP_ID= USER_GROUP.GROUP_ID"),
    GET_EMPLOYEE_BY_ID("SELECT EMPLOYEE.EMPLOYEE_FIRST_NAME, EMPLOYEE.EMPLOYEE_LAST_NAME, "
            + "USERS.USER_NAME, USERS.USER_PASSWORD FROM EMPLOYEE, USERS "
            + "WHERE EMPLOYEE.EMPLOYEE_ID = USERS.USER_ID AND EMPLOYEE.EMPLOYEE_ID=?"),
    INSERT_EMPLOYEE("INSERT INTO EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_FIRST_NAME, EMPLOYEE_LAST_NAME) "
            + "VALUES ((SELECT USER_ID FROM USER_GROUP WHERE USER_ID=?), ?, ?)"),
    //    INSERT_EMPLOYEE("INSERT INTO TEST_TABLE (ID, FNAME, "
    //            + "LNAME, DEPT) VALUES (?, ?, ?, ?)"),
    INSERT_EMPLOYEE_USER("INSERT INTO USERS (USER_ID,USER_NAME,USER_PASSWORD) "
            + "VALUES ((SELECT EMPLOYEE_ID from EMPLOYEE WHERE EMPLOYEE_ID =?), ?, ?)"),
    INSERT_USER_GROUPS("INSERT INTO USER_GROUP (USER_ID, GROUP_ID) "
            + "VALUES (?, (SELECT GROUP_ID from GROUPS where GROUP_NAME=?))"),
    UPDATE_EMP_USER_GROUP("UPDATE USER_GROUP SET GROUP_ID=(SELECT GROUP_ID from GROUPS where GROUP_NAME=?)"
            + " WHERE USER_ID=?"),
    UPDATE_EMPLOYEE("UPDATE EMPLOYEE SET EMPLOYEE_FIRST_NAME=?, EMPLOYEE_LAST_NAME=? WHERE EMPLOYEE_ID=?"),
    UPDATE_EMP_USERS("UPDATE USERS SET USER_NAME=? ,USER_PASSWORD=? "
            + "WHERE USER_ID=(SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID=?)"),
    DELETE_EMP_USER_GROUP("DELETE FROM USER_GROUP WHERE USER_ID=?"),
    DELETE_EMP_EMPLOYEE("DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID=?"),
    DELETE_EMP_USERS("DELETE FROM USERS WHERE USER_ID=?"),
    //Queries: Degree
    GET_ALL_DEGREES("SELECT DEEGREE.DEGREE_ID, DEEGREE.DEGREE_NAME FROM DEEGREE ORDER BY DEEGREE.DEGREE_ID"),
    //Queries: Departments
    GET_ALL_DEPARTMENTS("SELECT DEPARTMENTS.DEPARTMENT_ID, DEPARTMENTS.DEPARTMENT_NAME, SCHOOLS.SCHOOL_NAME, "
            + "DEPARTMENTS.SCHOOL_ID FROM DEPARTMENTS, SCHOOLS "
            + "WHERE SCHOOLS.SCHOOL_ID=? and SCHOOLS.SCHOOL_ID= DEPARTMENTS.SCHOOL_ID "
            + "ORDER BY DEPARTMENTS.DEPARTMENT_NAME"),
    GET_ALL_SCHOOLS("SELECT SCHOOLS.SCHOOL_ID, SCHOOLS.SCHOOL_NAME "
            + "FROM SCHOOLS ORDER BY SCHOOL_NAME"),
    // Quieries: groups and users:
    SELECT_GROUPS("SELECT GROUP_ID, GROUP_NAME FROM GROUPS"),
    GENERATE_EMPLOYEE_ID("SELECT MAX(USER_ID)+1 AS EMPLOYEE_ID FROM USERS"),;
    private String columnName;

    private AlumniServEnum(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return columnName;
    }

}
