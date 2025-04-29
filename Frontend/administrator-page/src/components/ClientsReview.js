

const ClientsReview = ({ clients, setSelectedClient, selectedClient }) => {

    return(

            <div className="table-content">
            <table id="malfunctions" className="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Identity document</th>
                        <th>Passport</th>
                        <th>Driver license</th>
                        <th>Is blocked</th>
                    </tr>
                </thead>
                <tbody>

                    {clients.map((c) => (
                        <tr
                            key={c.idClient}
                            onClick={() => {
                                 setSelectedClient(c);
                            }}
                            className={
                                selectedClient && selectedClient.idClient === c.idClient
                                    ? "selected-row" // Ako je red selektovan, dodajte obje klase
                                    : "table-row" // Inače, koristite samo podrazumijevanu klasu
                            }
                        >
                            <td>{c.idClient}</td>
                            <td>{c.name}</td>
                            <td>{c.surname}</td>
                            <td>{c.username}</td>
                            <td>{c.email}</td>
                            <td>{c.phone}</td>
                            <td>{c.idCard}</td>
                            <td>{c.passport}</td>
                            <td>{c.driverLicenseNumber}</td>
                            <td>{c.isBlocked}</td>
                           

                           
                        </tr>
                    ))} 

                </tbody>
            </table>
            </div>

    );

};


export default ClientsReview;