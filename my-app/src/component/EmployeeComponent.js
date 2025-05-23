import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {

    constructor(props) {
        super(props);
        
        this.state = {
            employee:{},
            department:{},
            organization:{}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((response) => {
            this.setState({employee : response.data.employee})
            this.setState({department : response.data.department})
            this.setState({organization : response.data.organization})

            console.log(this.state.employee)
            console.log(this.state.department)
            console.log(this.state.organization)

        })
    }
    
    render() {
        return (
            <div>
                <div className='details'>
                    <h3> View Employee Details </h3>
                    <div className='card-body'>
                            <p><strong>Employee First Name : </strong>{this.state.employee.firstName}</p>
                            <p><strong>Employee Last Name : </strong>{this.state.employee.lastName}</p>
                            <p><strong>Employee Email : </strong>{this.state.employee.email}</p>
                            <p><strong>Employee Location : </strong>{this.state.employee.location}</p>
                    </div>
                    <h3> View Department Details </h3>
                    <div className='card-body'>
                            <p><strong>Department Name : </strong>{this.state.department.departmentName}</p>
                            <p><strong>Department Description : </strong>{this.state.department.departmentDescription}</p>
                            <p><strong>Department Code : </strong>{this.state.department.departmentCode}</p>
                    </div>
                    <h3> View Organization Details </h3>
                    <div className='card-body'>
                            <p><strong>Organization Name : </strong>{this.state.organization.organizationName}</p>
                            <p><strong>Organization Code : </strong>{this.state.organization.organizationCode}</p>
                    </div>
                </div>
            </div>
        );
    }
}

export default EmployeeComponent;