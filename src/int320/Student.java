/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.Objects;

/**
 *
 * @author jiraw
 */
public class Student {
    private int id;
    private String name;
    private double gpax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpax() {
        return gpax;
    }

    public void setGpax(double gpax) {
        this.gpax = gpax;
    }

    public Student(int id, String name, double gpax) {
        this.id = id;
        this.name = name;
        this.gpax = gpax;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.gpax) ^ (Double.doubleToLongBits(this.gpax) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.gpax) != Double.doubleToLongBits(other.gpax)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
