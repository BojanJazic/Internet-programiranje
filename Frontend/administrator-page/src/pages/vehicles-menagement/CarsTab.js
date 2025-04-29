import TableControls from "../../components/TableControls";

const CarsTab = ({
    searchRefCars,
    filterTable,
    itemsPerPage,
    handleItemsPerPageChange,
    pageCount,
    handlePageClick,
    currentPage,
    refreshData,
    currentPageData,
    handleRightClick,
    contextMenu,
    closeContextMenu,
    isModalOpen,
    handleDelete,
    showDetails,
    showConfirm,
    confirmDelete,
    cancelDelete,
    }) => {
        return(
    
                            <div className="tab-content other-tabs">

                                <TableControls
                                    tableName={"cars"}
                                    searchRef={searchRefCars}
                                    filterTable={filterTable}
                                    itemsPerPage={itemsPerPage}
                                    handleItemsPerPageChange={handleItemsPerPageChange}
                                    pageCount={pageCount}
                                    handlePageClick={handlePageClick}
                                    currentPage={currentPage}
                                    refreshData={refreshData}
                                />

                                <div onClick={closeContextMenu}>
                                <div className={`table-responsive table-container ${isModalOpen ? 'disabled' : ''}`}>
                                <table id="cars" className="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Manufacturer</th>
                                                <th>Model</th>
                                                <th>Purchase price</th>
                                                <th>Purchase date</th>
                                                <th>Rented</th>
                                                <th>Broken</th>
                                            </tr>
                                        </thead>
                                        <tbody onContextMenu={(e) => e.preventDefault()}>
                                           
                                            {currentPageData.map((car) => (
                                                <tr onContextMenu={(e) => handleRightClick(e, car.idVehicle)}
                                                    key={car.idVehicle}
                                                    
                                                >
                                                    <td>{car.idVehicle}</td>
                                                    <td>{car.manufacturerName}</td>
                                                    <td>{car.model}</td>
                                                    <td>{car.purchasePrice}</td>
                                                    <td>{car.purchaseDate}</td>
                                                    <td>{car.isRented ? "Yes" : "No"}</td>
                                                    <td>{car.isBroken ? "Yes" : "No"}</td>

                                                </tr>
                                            ))}


                                        </tbody>
                                    </table>
                                            {contextMenu.visible && (
                                                <div className="context-menu"
                                                     style={{
                                                        position: "absolute",
                                                        top: `${contextMenu.y}px`,
                                                        left: `${contextMenu.x}px`,
                                                        backgroundColor: "white",
                                                        border: "1px solid #ccc",
                                                        boxShadow: "2px 2px 5px rgba(0, 0, 0, 0.2)",
                                                        zIndex: 1000,
                                                        padding: "5px",
                                                    }}
                                                    
                                                    onClick={closeContextMenu}  //zatvori kad se klikne van menija
                                                >
                                                <button className="context-menu button" onClick={handleDelete}>
                                                    Obriši
                                                </button>
                                                <button className="context-menu button" onClick={showDetails}>
                                                    Detalji
                                                </button>
                                                
                                                </div>
                                            )}

                                            {showConfirm && (
                                                <div className="modal-overlay">
                                                    <div className="modal-content">
                                                        <p>Da li ste sigurni da želite obrisati ovaj unos?</p>
                                                        <button onClick={confirmDelete} className="confirm-btn">Potvrdi</button>
                                                        <button onClick={cancelDelete} className="cancel-btn">Odustani</button>

                                                    </div>
                                                </div>
                                            )}

                                </div>
                                
                                </div>     

                            </div>
    
        );
};

export default CarsTab;