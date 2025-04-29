

const EmployeesReview = ({ employees, selectedEmployee, setSelectedEmployee }) => {

    return(
        <div className="table-content">
        <table id="malfunctions" className="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Username</th>
                    <th>Role</th>
                    
                </tr>
            </thead>
            <tbody>
                {/**OVDJE CE BITI DODAVANE VRIJEDNOSTI */}

                {employees.map((e) => (
                    <tr
                        key={e.id}
                        onClick={() => {
                            //console.log("Selected Malfunction ID:", malfunction.idMalfunction);
                             setSelectedEmployee(e)
                        }}
                        className={
                            selectedEmployee && selectedEmployee.id === e.id
                                ? "selected-row" // Ako je red selektovan, dodajte obje klase
                                : "table-row" // Inače, koristite samo podrazumijevanu klasu
                        }
                    >
                        <td>{e.id}</td>
                        <td>{e.name}</td>
                        <td>{e.surname}</td>
                        <td>{e.username}</td>
                        <td>{e.role}</td>
                       
                    </tr>
                ))} 

            </tbody>
        </table>
        </div>

    );

}

export default EmployeesReview;