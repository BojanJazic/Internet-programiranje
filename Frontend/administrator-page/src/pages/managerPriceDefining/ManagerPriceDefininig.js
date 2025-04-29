
import { useEffect, useState } from 'react';
import backIcon from '../../back.png';
import '../managerPriceDefining/ManagerPriceDefining.css';
import FetchData from '../../components/FetchData';
import PriceDefiningModal from '../modals/PriceDefiningModal';
import axios from 'axios';


function ManagerPriceDefining(){

    const [ data, setData ] = useState([]);
    const [ selectedVehicleId, setSelectedVehicleId ] = useState(null);

    const [  isModalOpen, setIsModalOpen ] = useState(false);
    const [ formData, setFormData ] = useState({
       rentalPrice: "", 
    });

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await FetchData({endpoint: "/vehicles"});

        if(response){
            console.log(response);
            setData(response);
        }

    }

    const handleOpenModal = () => {
        setIsModalOpen(true);
        setFormData({
            rentalPrice: "",
        });
    }

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setFormData({
            rentalPrice: "",
        });
    }

    const handleSave = async () => {
        console.log(formData.rentalPrice);

        try{
            axios.put(`/vehicles/${selectedVehicleId}/${formData.rentalPrice}`);
            fetchData();
        }catch(error){
            console.error(error);
        }



        handleCloseModal();
    }

    return(
        <>
        
            <div className="price-defining">

                <div className="left-panel">
                    <div className="corner-element">
                        <button type="button" className="button-text" onClick={() => window.history.back()}>
                            <img src={backIcon} alt="Back" width="20" height="20"/>
                        </button>
                    </div>

                    <div className="button-place">
                        <button type="button" 
                            disabled={selectedVehicleId === null}
                            onClick={() => {
                            
                                handleOpenModal();
                                console.log(selectedVehicleId);
                            }}>Add rental price</button>
                    </div>


                </div>


                <div className="right-panel">
                
                    <div className="table-content">
                        <table id="vehicles" className="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Manufacturer</th>
                                <th>Model</th>
                                <th>Rental price</th>
                                    
                            </tr>
                            </thead>
                            <tbody>
                                         
                                {data.map((d) => (
                                    <tr
                                        key={d.idVehicle}
                                        onClick={() => {
                                            setSelectedVehicleId(d.idVehicle)
                                            //console.log("SELECTED VEHICLE: ", selectedVehicleId);
                                        }}  
                                        
                                        className={
                                            selectedVehicleId === d.idVehicle
                                            ? "selected-row"
                                            : "table-row"
                                        }
                                    >
                                        <td>{d.idVehicle}</td>
                                        <td>{d.manufacturer}</td>
                                        <td>{d.model}</td>
                                        <td>{d.price !== null ? d.price : "N/A"}</td>
                                    </tr>
                                ))}


                            </tbody>
                        </table>
                    </div>

                        <PriceDefiningModal 
                            isOpen={isModalOpen}
                            onClose={handleCloseModal}
                            onSave={handleSave}
                            formData={formData}
                            setFormData={setFormData}
                        />

                </div>

            </div>
        
        </>

    );

}

export default ManagerPriceDefining;