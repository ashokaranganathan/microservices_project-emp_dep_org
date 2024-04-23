import axios from 'axios'

const Employee_Service_Base_Url = "http://localhost:9191/api/employee/get/";

const Employee_ID = 1;

class EmployeeService{
    getEmployee(){
        return axios.get(Employee_Service_Base_Url + Employee_ID);
    }
}

export default new EmployeeService