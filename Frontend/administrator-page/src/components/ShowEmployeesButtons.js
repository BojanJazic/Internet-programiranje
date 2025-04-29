

const ShowEmployeesButtons = ({ handleOpenModal, selectedEmployee, employees, handleDeleteEmployee }) => {

    return(
    <div className="option-buttons">
        <button type="button" onClick={() => handleOpenModal("add")}>Add</button>
        <button type="button" onClick={() => {
            if(selectedEmployee){
            const employee = employees.find(e => e.id === selectedEmployee.id);

                if(employee){
                    handleOpenModal("update", employee);
                }
            }else{
                alert("First select the row!")
            }
            }}>Update
        </button>
        <button type="button" onClick={() => handleDeleteEmployee(selectedEmployee.id)}>Delete</button>
    </div>
    );
}

export default ShowEmployeesButtons;