import ReactPaginate from "react-paginate";

const TableControl = ({ tableName,
    searchRef,
    filterTable,
    itemsPerPage,
    handleItemsPerPageChange,
    pageCount,
    handlePageClick,
    currentPage,
    refreshData,
}) => {

    console.log("searchRef:", searchRef);
    console.log("searchRef.current:", searchRef?.current);
    
    return(
    <div className="search-menu">
 
                            <input
                                type="text"
                                placeholder="Search..."
                                ref={searchRef}
                                className="me-2"
                                onChange={() => filterTable(tableName, searchRef)}
                            />
                            
                            <div className="pagination-wrapper">
                            <select className="items-per-page" value={itemsPerPage} onChange={handleItemsPerPageChange}>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                            </select>
                            <ReactPaginate
                                previousLabel={'Previous'}
                                nextLabel={'Next'}
                                breakLabel={'...'}
                                pageCount={pageCount}
                                marginPagesDisplayed={2}
                                pageRangeDisplayed={5}
                                onPageChange={handlePageClick}
                                containerClassName={'pagination'}
                                activeClassName={'active'}
                                forcePage={currentPage} //sinhronizacija sa trenutnom stranicom
                            />
                        </div>
                            
                            <button type="button" onClick={refreshData}>Refresh</button>
                           
                            </div>
    );
};

export default TableControl;