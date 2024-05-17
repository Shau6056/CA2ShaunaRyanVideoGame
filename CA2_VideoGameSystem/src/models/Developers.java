package models;

import controllers.MyHashTable;
import controllers.MyLinkedList;
import controllers.Node;

import java.util.Arrays;

public class Developers {

    private String name;
    private String dateOfInc;
    private String country;
    private String[] famousTitles;

    private MyHashTable<String, DevRole> devRoles; // Hashtable to store DevRole objects

    public Developers(String name, String dateOfInc, String country, String[] famousTitles) {
        this.name = name;
        this.dateOfInc = dateOfInc;
        this.country = country;
        this.famousTitles = famousTitles;
        this.devRoles = new MyHashTable<>(50);
    }

    public void addRole(String roleName, String devRole) {
        DevRole role = new DevRole(devRole);
        devRoles.put(roleName, role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfInc() {
        return dateOfInc;
    }

    public void setDateOfInc(String dateOfInc) {
        this.dateOfInc = dateOfInc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getFamousTitles() {
        return famousTitles;
    }

    public void setFamousTitles(String[] famousTitles) {
        this.famousTitles = famousTitles;
    }

    @Override
    public String toString() {
        StringBuilder rolesString = new StringBuilder();
        for (int i = 0; i < devRoles.getCapacity(); i++) {
            MyLinkedList<MyHashTable.Entry<String, DevRole>> bucket = devRoles.getBucket(i);
            if (bucket != null) {
                Node<MyHashTable.Entry<String, DevRole>> current = bucket.getHead();
                while (current != null) {
                    rolesString.append(current.getData().getKey()).append(": ").append(current.getData().getValue().getDevRole()).append(", ");
                    current = current.getNext();
                }
            }
        }
        return "Developers {" +
                "Name='" + name + '\'' +
                ", Date of Incorporation='" + dateOfInc + '\'' +
                ", Country of Origin='" + country + '\'' +
                ", Famous Titles Developed='" + Arrays.toString(famousTitles) + '\'' +
                ", Roles=[" + rolesString.toString() + "]" +
                '}';
    }
}
