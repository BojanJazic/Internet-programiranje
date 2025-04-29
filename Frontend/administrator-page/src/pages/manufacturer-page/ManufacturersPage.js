
import React, { useState, useEffect } from 'react';
import './ManufacturersPage.css';
import backIcon from '../../back.png';
import axios from 'axios';
import ManufacturerModal from '../modals/ManufacturerModal';
import ReactPaginate from "react-paginate";
import ShowManufacturerTable from '../../components/ShowManufacturerTable';
import BackButton from '../../components/BackButton';

function ShowManufacturers() {

    const [ selectedManufacturerId, setSelectedManufacturerId ] = useState(null);
  
    const [ manufacturers, setManufacturers ] = useState([]);   //stanje za podatke o proizvodjacima
    const [ selectedManufacturer, setSelectedManufacturer ] = useState(null);   //stanje za selektovanog proizvodjaca
    const [ view, setView ] = useState("table");    //stanje za upravljanje prikazom

    const [ isModalOpen, setIsModalOpen ] = useState(false);
    const [ modalMode, setModalMode ] = useState("add");    //add ili update
    const [ formData, setFormData ] = useState({
        name: "",
        state: "",
        address: "",
        phone: "",
        fax: "",
        email: "",
    });


    const [ currentPage, setCurrentPage ] = useState(0); //trenutna stranica
    const [ itemsPerPage, setItemsPerPage ]= useState(5);  // Broj elemenata po stranici
    
    



    //ucitavanje podataka
    useEffect(() => {
        fetchData();
    }, [selectedManufacturerId]);

    const fetchData = async () => {
        try{
            const response = await fetch("/manufacturers");
            const data = await response.json();
            setManufacturers(data);
        }catch(error){
            console.error("Error fetching manufacturers:", error);
        }
    };
        
    const handleAdd = () => setView("add");
    const handleUpdate = (manufacturer) => {
        setSelectedManufacturer(manufacturer);
        setView("update");
    };

    const handleDelete = async () => {
        
        console.log("Manufacturer id: " + selectedManufacturerId);
        if(!selectedManufacturerId){
            alert("First select the row");
            return;
        }
        try {
            const response = await axios.delete(`/manufacturers/${selectedManufacturerId}`);
           
            if( response.status === 200 ){
                setManufacturers(manufacturers.filter(m => m.id !== selectedManufacturerId));
                setSelectedManufacturerId(null);
            } 

        }catch(error){
            console.log("Error deleting manufacturer: ", error);
        }


    };

    const handleOpenModal = (mode, manufacturer) => {
        if(mode === "update" && manufacturer){
            setFormData(manufacturer);
        }else{
            setFormData({ // Resetuj formu za dodavanje
                name: "",
                state: "",
                address: "",
                phone: "",
                fax: "",
                email: "",
            });
        }
        setIsModalOpen(true);
        setModalMode(mode);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setFormData({  name: "",
                       state: "",
                       address: "",
                       phone: "",
                       fax: "",
                       email: ""
        });
    };


    //OVDJE CE ICI METODA ZA CUVANJE PODATAKA
    const handleSave = async () => {
        try {
            let response;
            if(modalMode === "add"){
                response = await axios.post("/manufacturers", formData);    
            }else if(modalMode === "update"){
                response = await axios.put("/manufacturers", formData);
            }
            
            fetchData(); // Ponovo učitaj podatke
            handleCloseModal();
            
        } catch (error) {
            console.log(error);
        }
    };



    


    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentPageData = manufacturers.slice(startIndex, endIndex);
    const pageCount = Math.ceil(manufacturers.length / itemsPerPage);

     //funkcija za promjenu stranice
     const handlePageClick = ({ selected }) => {
        setCurrentPage(selected);
    };

    //funkcija za promjenu broja elemenata po stranici
    const handleItemsPerPageChange = (e) => {
        setItemsPerPage(Number(e.target.value));
        setCurrentPage(0); //resetujemo paginaciju
    }



    return (
        <>
            <div className="manufacturers-background">
                <div className="header">
                    <div className="manufacturers-corner-element">
                    <BackButton />
                    </div>
                    <h2>Manufacturers</h2>
                </div>

                <div className="options">
                    <div className="options-side">
                        <label>Options</label>
                        <div className="buttons-group">
                            <button type="button" onClick={() => handleOpenModal("add")}>Add</button>
                            <button type="button" onClick={() => {
                                if(selectedManufacturerId){
                                    const manufacturer = manufacturers.find(m => m.id === selectedManufacturerId);

                                    if(manufacturer){
                                        handleOpenModal("update", manufacturer);
                                    }
                                }else {
                                    alert("First select the row!");
                                }
                            }}>Update</button>
                            <button type="button" onClick={handleDelete}>Delete</button>                        
                            
                            <div className="manufacturer-pagination-wrapper">
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
                                containerClassName={'manufacturer-pagination'}
                                activeClassName={'active'}
                                forcePage={currentPage} //sinhronizacija sa trenutnom stranicom
                            />
                        </div>

                        </div>


                    </div>
                    <div className="options-display">

                        {/* {view === "table" && ( */}
                            <ShowManufacturerTable
                                manufacturers={manufacturers}
                                currentPageData={currentPageData}
                                setSelectedManufacturerId={setSelectedManufacturerId}
                                />
                        {/* )} */}

                            <ManufacturerModal
                                isOpen={isModalOpen}
                                onClose={handleCloseModal}
                                onSave={handleSave}
                                formData={formData}
                                setFormData={setFormData}
                                modalMode={modalMode}
                            />

                    </div>
                </div>

                

            </div>
        
        </>
    );

}


function ManufacturersPage(){
    return <ShowManufacturers />;
}

export default ManufacturersPage;