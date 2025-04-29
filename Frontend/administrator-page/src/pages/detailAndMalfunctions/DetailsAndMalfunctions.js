
import { useLoaderData, useLocation } from "react-router-dom";
import './DetailsAndMalfunctions.css';
import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import MalfunctionModal from '../modals/MalfunctionModal';
import axios from "axios";
import backIcon from '../../back.png'
import defaultImg from '../../images/compressed.jpg';
import MalfunctionsTable from "../../components/MalfunctionsTable";


function DetailsAndMalfunctions() {
    console.log("Component rerendered"); 
    const navigate = useNavigate();
    
    const location = useLocation();
    const vehicle = location.state; //preuzimanje podataka o vozilu

    //dio vezan za otvaranje modalnog prozora i slanje podataka


    const [ isModalOpen, setIsModalOpen ] = useState(false);
    const [ formData, setFormData ] = useState({
        idVehicle: vehicle.idVehicle || "",
        description: "",
        dateTime: "",
    });

    const [ data, setData ] = useState([]); //podaci iz baze
    //pracenje izabranog id-ja
    const [selectedMalfunctionId, setSelectedMalfunctionId] = useState(null);


    // Funkcija za učitavanje podataka
    const fetchData = async () => {
        try {
            const response = await axios.get(`/malfunctions/${vehicle.idVehicle}`);
            setData(response.data);
        }catch(error){
            console.error("Error fetching data from", error);
        }
    };

    useEffect(() => {
        fetchData();
    }, [vehicle.idVehicle]); 



    let showCarDetails = false;
    let showBikeDetails = false;
    let showScooterDetails = false;

    if (!vehicle) {
        return <p>Nema podataka o vozilu.</p>;
    }

    if( vehicle.hasOwnProperty("description")){
        showCarDetails = true;
        showBikeDetails = false;
        showScooterDetails = false;
    } else if( vehicle.hasOwnProperty("autonomy") ){
        showCarDetails = false;
        showBikeDetails = true;
        showScooterDetails = false;
    } else if( vehicle.hasOwnProperty("maxSpeed") ){
        showCarDetails = false;
        showBikeDetails = false;
        showScooterDetails = true;
    }


    
    const handleOpenModal = () => {
        setIsModalOpen(true);
    };

    const resetData = () => {
        setFormData({ idVehicle: vehicle.idVehicle, description: "", dateTime: "" }); // Resetovanje polja
        setIsModalOpen(false);
    };

    const handleCloseModal = () => {
       resetData();
    };

    const handleSave = async () => {
        const dataToSend = {...formData};
        console.log("Podaci za slanje:", dataToSend);

        try{
            
            await axios.post("/malfunctions", dataToSend);

           await fetchData(); 

           await handleUpdateVehicle();
           

        }catch(error){
            alert(error);
        }

        resetData();
    };

    const handleUpdateVehicle = async () => {
        try{
            await axios.put(`/vehicles/${vehicle.idVehicle}`);
        }catch(error){
            alert(error);
        }
    }


    const handleDelete = async () => {
        if(!selectedMalfunctionId){
            alert("First select the row");
            return;
        }

        try{
            const response = await axios.delete(`/malfunctions/${selectedMalfunctionId}`);
            setData(prevData => {
                const newData = prevData.filter(malfunction => malfunction.idMalfunction !== selectedMalfunctionId);
    
                // Proverite da li je data.length === 0 nakon ažuriranja
                if (newData.length === 0) {
                    console.log("DATA IS EMPTY");
                    handleRepairVehicle();
                }
    
                return newData;
            });
            setSelectedMalfunctionId(null); // resetuje izabrani id
        }catch(error){
            alert(error);
        }
    };

    const handleRepairVehicle = async () => {
        try{
            await axios.put(`/vehicles/repair/${vehicle.idVehicle}`);
        }catch(error){
            alert(error);
        }
    };

    const ShowRentals = () => {
        console.log("Sending state:", vehicle.idVehicle); // Provjerite šta šaljete
        console.log("Putanja do slike: ", vehicle.img);
        navigate("/rentals", {state: vehicle});
    }


    return(
        <>
          <div className="details-background">
            <div className="details-side">
                <div className="header">
                <div className="details-corner-element">
                    <button type="button" className="details-button-text" onClick={() => window.history.back()}>
                        <img src={backIcon} alt="Back" width="20" height="20"/>
                    </button>
                </div>

                    <h2>Details</h2>
                </div>

                <div className="informations">
                    <label>Id vehicle: <input type="text" value={vehicle.idVehicle} readOnly /></label>
                    <label>Manufacturer: <input type="text" value={vehicle.manufacturerName} readOnly /></label>
                    <label>Model: <input type="text" value={vehicle.model} readOnly /></label>
                    <label>Purchase price: <input type="text" value={vehicle.purchasePrice} readOnly /></label>
                    <label>Is rented: <input type="text" value={vehicle.isRented} readOnly /></label>
                    {/**   SAD U ZAVISNOSTI OD TIPA VOZILA TREBA DA SE PRIKAZU I OSTALA POLJA   */}

                   { showCarDetails && (
                        <div>
                        <label>Purchase date: <input type="date" value={vehicle.purchaseDate} readOnly /></label>
                        <label>Description: </label>
                        <textarea
                            value={vehicle.description}
                            readOnly
                            rows={4} // Broj redova koji se prikazuje
                            cols={20} // Širina polja
                            style={{ resize: "none" }} // Onemogućava korisniku da menja veličinu
                        />
                      
                       </div>
                    )
                    }
                    {showBikeDetails && (
                        <label>Autonomy: <input type="text" value={vehicle.autonomy} readOnly /></label>
                    )
                    }
                    {showScooterDetails && (
                        <label>Max speed: <input type="text" value={vehicle.maxSpeed} readOnly /></label>
                    )}
                   
                </div>
                {/**
                 * 
                 * iz nekog razloga ne radi ovo ucitavanje slike, to moram istraziti
                 * 
                 */}
                 {/*ovdje ne moze direktno da se ucita pomocu src, nego mora biti ucitana prije*/}
                 
                 
                <div className="image-space" style={{ 
                            backgroundImage: `url(${vehicle.image ? `/images/${vehicle.image}` : defaultImg})` 
                        }}>

                </div>
                
                <div className="footer">
            
                <button className="details-side-button" type="button" onClick={ShowRentals}>Rentals</button>
            </div>

            </div>

            

            <div className="malfunctions-side">
                <div className="header">
                    <h2>Malfunctions</h2>
                </div>

                <div>

                    <MalfunctionsTable 
                        data={data}
                        setSelectedMalfunctionId={setSelectedMalfunctionId}
                        selectedMalfunctionId={selectedMalfunctionId}
                    />

                {/* <div className="table-content">
                <table id="malfunctions" className="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                       

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
                </div> */}
                </div>



                <div className="footer">
                    <button className="malfunction-side-button" type="button" onClick={handleOpenModal}>Add</button>

                    <MalfunctionModal
                        isOpen={isModalOpen}
                        onClose={handleCloseModal}
                        onSave={handleSave}
                        formData={formData}
                        setFormData={setFormData}
                    />

                    <button className="malfunction-side-button" type="button" 
                            onClick={handleDelete} 
                            disabled={!selectedMalfunctionId}   //onemoguci dugme ako nijedan red nije selektovan
                            >Delete</button>
                </div>
            </div>
          </div>
        </>
    );
}


export default DetailsAndMalfunctions;
