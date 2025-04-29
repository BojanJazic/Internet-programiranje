import React, { useState, useEffect } from 'react';
import backIcon from '../../back.png';
import './UsersManagement.css';
import axios from 'axios';
import FetchData from '../../components/FetchData';
import EmployeeModal from '../modals/EmployeeModal';
import ClientsReview from '../../components/ClientsReview';
import EmployeesReview from '../../components/EmployeesReview';
import ShowClientsButtons from '../../components/ShowClientsButtons';
import ShowEmployeesButtons from '../../components/ShowEmployeesButtons';

function UsersManagement() {

    const [ view, setView ] = useState("");  //clients ili employees

    const [ clients, setClients ] = useState([]);    //podaci iz baze
    const [ employees, setEmployees ] = useState([]);
    const [ selectedClient, setSelectedClient ] = useState(null);
    const [ selectedEmployee, setSelectedEmployee ] = useState(null);
    
    const [ isModalOpen, setIsModalOpen ] = useState(false);
    const [ modalMode, setModalMode ] = useState("add");    //add ili update
    const [ formData, setFormData ] = useState({
        name: "",
        surname: "",
        username: "",
        role: ""
    });

    const handleAdd = () => {
        setView("add");
    }

    const handleUpdate = (employee) => {
        setSelectedEmployee(employee);
        setView("update");
    };

    const handleOpenModal = (mode, employee) => {
        if(mode === "update" && employee){
            setFormData(employee);
        }else{
            setFormData({ // Resetuj formu za dodavanje
                name: "",
                surname: "",
                username: "",
                role: ""
            });
        }
        setIsModalOpen(true);
        setModalMode(mode);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setFormData({  
            name: "",
                surname: "",
                username: "",
                role: "",
        });
    };


    const fetchData = async (userType) => {
        // Fetch clients
        if(userType === "clients"){
            const clientsData = await FetchData({ endpoint: "/clients" });
            if (clientsData) {
                setClients(clientsData);
            }
        }
        else {
            // Fetch employees
            const employeesData = await FetchData({ endpoint: "/persons/employees" });
            if (employeesData) {
                setEmployees(employeesData);
            }
        }
    };

    


    
    //gadjaj endpoint za dati id i azuriraj tamo stanje
    const handleBlockClient = async () => {
        // Logika za blokiranje klijenta
        console.log("Block client:" + selectedClient.idClient);
        try{
            if( selectedClient.isBlocked === 0){

                const response = await axios.put(`/clients/${selectedClient.idClient}`);

                fetchData("clients"); // Call the async function
            }
        }catch(error){
            console.error(error);
        }


    };
    
    const handleUnblockClient = async () => {
        // Logika za odblokiranje klijenta
       console.log("Unblock client:" + selectedClient.idClient);

        try{
            if( selectedClient.isBlocked === 1){

                const response = await axios.put(`/clients/${selectedClient.idClient}`);

                fetchData("clients"); // Call the async function
            }
        }catch(error){
            console.error(error);
        }
    };
    
    const handleAddEmployee = () => {
        // Logika za dodavanje zaposlenog
        console.log("Add employee");
    };
    
    const handleUpdateEmployee = (employeeId) => {
        // Logika za ažuriranje zaposlenog
        console.log("Update employee:", employeeId);
    };
    
    const handleDeleteEmployee = (employeeId) => {
        // Logika za brisanje zaposlenog
        console.log("Delete employee:", employeeId);
        axios.delete(`/persons/${employeeId}`);
        fetchData("employees");
    };


    const handleSave = async () => {
        try {
            let response;
            if(modalMode === "add"){
                await axios.post("/persons", formData);    
            }else if(modalMode === "update"){
                await axios.put(`/persons/${selectedEmployee.id}`, formData);
            }
            
            fetchData("employees"); // Ponovo učitaj podatke
            handleCloseModal();
            
        } catch (error) {
            console.log(error);
        }
    };


    return(
        <>
            <div className="users-management">
                <div className="left-panel">
                    <div className="corner-element">
                        <button type="button" className="button-text" onClick={() => window.history.back()}>
                            <img src={backIcon} alt="Back" width="20" height="20"/>
                        </button>
                    </div>

                    <div className="buttons-management">
                        <button type="button" onClick={() => {
                            setView("clients");
                            fetchData("clients"); // Call the async function
                        }}>Clients</button>
                        <button type="button" onClick={() => {
                            setView("employees");
                            fetchData("employees"); // Call the async function
                        }}>Employees</button>
                       
                        {/**dodatna dugmad u zavisnosti od prikaza */}
                        { view === "clients" && (
                           
                            <ShowClientsButtons 
                                handleBlockClient={handleBlockClient}
                                handleUnblockClient={handleUnblockClient}
                                selectedClient={selectedClient}
                            
                            />
                        )}

                        { view === "employees" && (

                            <ShowEmployeesButtons 
                                handleOpenModal={handleOpenModal}
                                selectedEmployee={selectedEmployee}
                                employees={employees}
                                handleDeleteEmployee={handleDeleteEmployee}

                            />
                          
                        )}

                    </div>
                </div>
                <div className="right-panel">

                    {view === "clients" && (
                     <ClientsReview 
                        clients={clients}
                        setSelectedClient={setSelectedClient}
                        selectedClient={selectedClient}
                     />

                        )}
                    { view === "employees" && (

                        <EmployeesReview 
                            employees={employees}
                            selectedEmployee={selectedEmployee}
                            setSelectedEmployee={setSelectedEmployee}
                        />
                     )}

                    <EmployeeModal
                        isOpen={isModalOpen}
                        onClose={handleCloseModal}
                        onSave={handleSave}
                        formData={formData}
                        setFormData={setFormData}
                        modalMode={modalMode}
                    />
                            
                </div>
            </div>
        </>
    );




}

export default UsersManagement;