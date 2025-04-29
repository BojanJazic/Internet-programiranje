

const ShowManufacturerTable = ({ manufacturers, currentPageData, setSelectedManufacturerId }) => {
    return (
        <div className="manufacturers-table-content">
        <table className="table table-striped table-bordered table-view">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>State</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Fax</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                {currentPageData.map((manufacturer) => (
                    <tr key={manufacturer.id}
                        onClick={() => {
                            setSelectedManufacturerId(manufacturer.id);
                           // console.log("Selected Manufacturer ID:", manufacturer.id); // Provera
                        }}
                        
                    >
                        <td>{manufacturer.id}</td>
                        <td>{manufacturer.name}</td>
                        <td>{manufacturer.state}</td>
                        <td>{manufacturer.address}</td>
                        <td>{manufacturer.phone === null || manufacturer.phone == "" ? "null" : manufacturer.phone}</td>
                        <td>{manufacturer.fax === null  || manufacturer.fax == "" ? "null" : manufacturer.fax}</td>
                        <td>{manufacturer.email === null  || manufacturer.email == "" ? "null" : manufacturer.email}</td>

                       
                    </tr>
                ))}
            </tbody>
        </table>
        </div>
    );
};

export default ShowManufacturerTable;