package mysmax.com.retrofitapplication.model;

import java.util.ArrayList;

public class TripsItem {
    private String routeId;
    private ArrayList<EmployeeDetails> employee = new ArrayList<>();

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public ArrayList<EmployeeDetails> getEmployee() {
        return employee;
    }

    public void setEmployee(ArrayList<EmployeeDetails> employee) {
        this.employee = employee;
    }

    public static class EmployeeDetails
    {
        private String empId;
        private String empName;
        private String empAddress;
        private String pickupTime;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getEmpAddress() {
            return empAddress;
        }

        public void setEmpAddress(String empAddress) {
            this.empAddress = empAddress;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }
    }
}
