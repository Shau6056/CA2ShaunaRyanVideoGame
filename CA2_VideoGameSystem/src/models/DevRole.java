package models;

public class DevRole {

    private String devRole;

    public DevRole(String devRole) {
        setDevRole(devRole);
    }

    public String getDevRole() {
        return devRole;
    }

    public void setDevRole(String devRole) {
        String[] devRoleList = {"Designer", "Programmer", "Artist"}; // Making these the only valid roles
        for (String validRole : devRoleList) {
            if (devRole.equals(validRole)) {
                this.devRole = devRole;
                return; // Exit the method once a valid role is found
            }
        }
        this.devRole = ""; // Set to empty string if the role is invalid
    }
}
