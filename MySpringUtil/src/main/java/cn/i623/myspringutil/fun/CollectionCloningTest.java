package cn.i623.myspringutil.fun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionCloningTest {

    public static void main(String[] args){
        ArrayList<Employee> old = new ArrayList<Employee>();
        old.add(new Employee("Joe", "Manager"));
        old.add(new Employee("Tim", "Developer"));
        old.add(new Employee("Frank", "Developer"));

       //Collection<Employee> newc = new HashSet<>(org);
       Collection<Employee> newc = new HashSet<Employee>(old.size());


        System.out.println("原来的集合： "+old);
        System.out.println("复制的集合： "+newc);

        Iterator<Employee> oldItr = old.iterator();
        while(oldItr.hasNext()){
            //oldItr.next().setDesignation("staff");
            newc.add(oldItr.next().clone());
        }


        Iterator<Employee> orgItr2 = old.iterator();
        while(orgItr2.hasNext()){
            orgItr2.next().setDesignation("staff");
        }
        System.out.println("修改后原来的集合： "+old);
        System.out.println("修改后复制的集合： "+newc);
    }

}


class Employee implements Cloneable{
    private String name;
    private String designation;

    public Employee(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, designation );
    }

    @Override
    protected Employee clone(){
        try {
            Employee result = (Employee) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
             throw new RuntimeException(e); // won't happen
        }

    }
}