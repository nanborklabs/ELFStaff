package com.elfstaff.model;

/**
 * Created by nandhu on 5/8/16.
 *
 */
public class ReportModel {

// this is one of five category 10-20, 20-30,30-40 etc..
    public int Category;
//        no of students in that category

//    icon to display if 0 display red icon
//    else display green icon;

    public  boolean icon;
    public int nosStudetns;
    public int growthPercent;

    public ReportModel(int category, boolean icon, int nosStudetns, int growthPercent) {
        Category = category;
        this.icon = icon;
        this.nosStudetns = nosStudetns;
        this.growthPercent = growthPercent;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }

    public int getNosStudetns() {
        return nosStudetns;
    }

    public void setNosStudetns(int nosStudetns) {
        this.nosStudetns = nosStudetns;
    }

    public int getGrowthPercent() {
        return growthPercent;
    }

    public void setGrowthPercent(int growthPercent) {
        this.growthPercent = growthPercent;
    }
}




