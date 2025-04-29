

const MalfunctionsTable = ({ data = [], setSelectedMalfunctionId, selectedMalfunctionId }) => {

    return(

        <div className="table-content">
                <table id="malfunctions" className="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {/**OVDJE CE BITI DODAVANE VRIJEDNOSTI */}

                        {data.map((malfunction) => (
                            <tr
                                key={malfunction.idMalfunction}
                                onClick={() => {
                                    console.log("Selected Malfunction ID:", malfunction.idMalfunction);
                                     setSelectedMalfunctionId(malfunction.idMalfunction)}
                                }
                                className={
                                    selectedMalfunctionId === malfunction.idMalfunction
                                        ? "selected-row" // Ako je red selektovan, dodajte obje klase
                                        : "table-row" // Inače, koristite samo podrazumijevanu klasu
                                }
                            >
                                <td>{malfunction.idMalfunction}</td>
                                <td>{malfunction.description}</td>
                                <td>{malfunction.dateTime}</td>
                               
                            </tr>
                        ))} 

                    </tbody>
                </table>
                </div>

    );

}

export default MalfunctionsTable;