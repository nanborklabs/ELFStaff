package com.elfstaff.model;

/**
 * Created by nandhu on 4/8/16.
 */
public class Student {

public String studentname;
    public String standard;
    public String board;
    public int elf_id;

    public Student(String studentname, String standard, String board, int elf_id) {
        this.studentname = studentname;
        this.standard = standard;
        this.board = board;
        this.elf_id = elf_id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getElf_id() {
        return elf_id;
    }

    public void setElf_id(int elf_id) {
        this.elf_id = elf_id;
    }
}
