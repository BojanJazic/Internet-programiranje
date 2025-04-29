import React, { useState, useEffect } from "react";
import './CreateVehicle.css';
import axios from "axios";
import { useNavigate } from "react-router-dom";
import backIcon from '../../back.png';

function CreateVehicle() {

    const navigate = useNavigate();

    const [
        vehicleType,
        setVehicleType
    ] = useState("");

    const [ manufacturers, setManufacturers ] = useState([]);

    const [formData, setFormData] = useState({
        idVehicle: "",          // Ostaje string ako nije autoinkrement ID
        idManufacturer: "",   // Broj, pa inicijalizujemo sa null
        manufacturer: "",       //String ime proizvodjaca
        model: "",               // String
        purchasePrice: null,    // Broj (int), inicijalizovan na null
        isRented: 0,        // Boolean (tinyint u bazi) - koristimo false
        isBroken: 0,        // Boolean (tinyint u bazi) - koristimo false
        image: "",               // String (URL ili putanja slike)
        purchaseDate: "",       // String u formatu "YYYY-MM-DD"
        description: "",         // String
        autonomy: null,          // Broj (int), inicijalizovan na null
        maxSpeed: null          // Broj (int), inicijalizovan na null
    });

    const handleRadioChange = (event) => {
        setVehicleType(event.target.value);
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;

        //ovdje je bitno da raspored bude isti kao i na formi i da se podudaraju imena kao u DTO klasama i potrebne su eksplicitne konverzije tipova

        if(name === "idVehicle") {
            setFormData((prevData) => ({
                ...prevData,
                [name]: value,
            }));
        }

        else if (name === "idManufacturer"){
            //pronadjemo odabranog proizvodjaca u listi manufacturers
            const selectedManufacturer = manufacturers.find(manufacturer => manufacturer.id === Number(value));
            
            setFormData((prevData) => ({
                ...prevData,
                idManufacturer: Number(value),
                manufacturer: selectedManufacturer ? selectedManufacturer.name : "",
            
            }));
        } else if(name === "model"){
            setFormData((prevData) => ({
            ...prevData,
            [name]: value,
            }));
        }else if(name === "purchasePrice"){
            setFormData((prevData) => ({
            ...prevData,
            purchasePrice: Number(value),
            }));
        } else if(name === "image"){
            setFormData((prevData) => ({
            ...prevData,
            [name]: value,
            }));
        }
        if( vehicleType === "car"){
        
            if(name === "purchaseDate"){
                setFormData((prevData) => ({
                ...prevData,
                [name]: value,
                }));
            }else if(name === "description"){
                setFormData((prevData) => ({
                ...prevData,
                [name]: value,
                }));
            }
        }

        if( vehicleType === "e-bike"){
            if(name === "autonomy"){
                setFormData((prevData) => ({
                ...prevData,
                [name]: Number(value),
                }));
            } 
        }

        if( vehicleType === "e-scooter" ){
            if(name === "maxSpeed"){
                setFormData((prevData) => ({
                ...prevData,
                [name]: Number(value),
                }));
            } 
        }

    }

        


    useEffect(() => {
        axios.get("/manufacturers")
           .then(response => {
                const manufacturersData = response.data;

                const filteredData = manufacturersData.map(manufacturer => ({
                    id: manufacturer.id,
                    name: manufacturer.name
                }));
                 setManufacturers(filteredData);
           }).catch(error => {
            console.error("Greska pri dohvatanju: ", error);
            alert("Došlo je do greške pri dohvatanju proizvođača.");
        });
           
       
    }, []);

    const handleSubmit = async (event) => {
         event.preventDefault();     //sprijecava reload stranice

        //provjera da li je radio button izabran

        if(!vehicleType){
            alert("Izaberite tip vozila!");
            return;
        }


         // Provera da li su sva polja popunjena
        if (
            !formData.idVehicle || 
            !formData.idManufacturer || 
            !formData.model || 
            !formData.purchasePrice || 
            !formData.image 
        ) {
            alert("Molimo popunite sva polja!");
            return;
        }

        if( vehicleType === "car" ){
            if( !formData.purchaseDate || !formData.description ){
                alert("Molimo popunite sva polja!");
                return;
            }
        }
        if( vehicleType === "e-bike" ){
            if( !formData.autonomy ){
                alert("Molimo popunite sva polja!");
                return;
            }
        }
        if( vehicleType === "e-scooter" ){
            if( !formData.maxSpeed ){
                alert("Molimo popunite sva polja!");
                return;
            }
        }

        // Priprema podataka za slanje
        const vehicleData = { ...formData, vehicleType };


        if( vehicleType === "car"){
            
            //ovdje trebam raditi dodavanje u bazu
            try{
                const response = await axios.post("/cars", vehicleData);
                
                navigate("/vehiclesManagement");

            }catch(error){
                alert("Error: " + error);
            }

        }

        if( vehicleType === "e-bike"){
            try{
                const response = await axios.post("/e-bikes", vehicleData,
                    { headers : { "Content-Type": "application/json" }}
                );
                navigate("/vehiclesManagement");
                
            }catch(error){
                alert("Error: " + error);
            }
        }

        if( vehicleType === "e-scooter"){
            try{
                const response = await axios.post("/e-scooters", vehicleData,
                    { headers : { "Content-Type": "application/json" }}
                );
                navigate("/vehiclesManagement");
                

            }catch(error){
                alert("Error: " + error);
            }

        }

    };



    return(
        <>
            <div className="vehicle-background">
                <form className="vehicle-form"  onSubmit={handleSubmit}>
                    <div className="title">
                        <h2>Create vehicle</h2>
                    </div>
                <div className="vehicles-corner-element">
                    <button type="button" className="details-button-text" onClick={() => navigate("/vehiclesManagement")}>
                        <img src={backIcon} alt="Back" width="20" height="20"/>
                    </button>
                </div>

                <div className="radio-buttons">
                <div className="radioGroup">
                    <label>
                        <div className="radioButton">
                        <input
                            type="radio"
                            value="car"
                            checked={vehicleType === "car"}
                            onChange={handleRadioChange}
                        />
                        </div>
                            Car
                        </label>
                        <label>
                            <div className="radioButton">
                            <input
                                type="radio"
                                value="e-bike"
                                checked={vehicleType === "e-bike"}
                                onChange={handleRadioChange}
                            />
                            </div>
                            E-bike
                        </label>
                        <label>
                            <div className="radioButton">
                            <input
                                type="radio"
                                value="e-scooter"
                                checked={vehicleType === "e-scooter"}
                                onChange={handleRadioChange}
                            />
                            </div>
                            E-scooter
                        </label>
                        </div>
                    </div>


                    <div className="vehicle-container">
                        <div className="row">
                            <div className="col-12 col-md-6">

                            <div className="mb-2">
                                <label className="mb-2">Id vehicle:</label>
                                    <input type="text" name="idVehicle" value={formData.idVehicle} onChange={handleInputChange} className="form-control" />
                            </div>
                            <div className="mb-2">
                                <label className="mb-2">Manufacturer:</label>
                                <select 
                                    name="idManufacturer" 
                                    className="form-control" 
                                    value={formData.idManufacturer !== null ? formData.idManufacturer : ""}
                                    onChange={handleInputChange}
                                    >
                                    <option value="">Choose manufacturer</option>
                                        {manufacturers.map((manufacturer) => (
                                    <option key={manufacturer.id} value={manufacturer.id}>
                                        {manufacturer.name}
                                    </option>
                                    ))}
                                </select>
                            </div>
                            <div className="mb-2">
                                <label className="mb-2">Model:</label>
                                    <input type="text" name="model" value={formData.model} onChange={handleInputChange} className="form-control" />
                            </div>
                            <div className="mb-2">
                                    <label className="mb-2">Purchase price:</label>
                                    <input type="number" name="purchasePrice" value={formData.purchasePrice} onChange={handleInputChange} className="form-control" min={0}/>
                                    </div>
                            
                                
                            </div>
                            <div className="col-12 col-md-6">

                            <div className="mb-2">
                                <label className="mb-2">Image:</label>
                                    <input type="text" name="image" value={formData.image} onChange={handleInputChange} className="form-control" />
                            </div>

                            
                            {/* Ovdje treba da idu uslovi */}

                            { vehicleType === "car" && (
                                <div>
                                <div className="mb-2">
                                    <label className="mb-2">Purchase date:</label>
                                    <input 
                                        type="date" 
                                        name="purchaseDate" 
                                        value={formData.purchaseDate || ""}
                                        onChange={handleInputChange} 
                                        className="form-control" 
                                    />
                                </div>
                                <div className="mb-2">
                                    <label className="mb-2">Description:</label>
                                    <input type="text" name="description" value={formData.description} onChange={handleInputChange} className="form-control" />
                                </div>
                                </div>
                            )}

                            { vehicleType === "e-bike" && (
                                <div>
                                    <div className="mb-2">
                                    <label className="mb-2">Autonomy:</label>
                                    <input type="number" name="autonomy" value={formData.autonomy} onChange={handleInputChange} className="form-control" min={0}/>
                                    </div>
                                </div>
                            )}

                            { vehicleType === "e-scooter" && (
                                <div>
                                    <div className="mb-2">
                                    <label className="mb-2">Max speed:</label>
                                    <input type="number" name="maxSpeed" value={formData.maxSpeed} onChange={handleInputChange} className="form-control" min={0}/>
                                </div>
                                </div>
                            )}
                            
                            
                            
                                
                            </div>
                        </div>
                        <div>
                            <button type="submit" className="vehicle-container button">Create</button>
                        </div>
                    </div>
                </form>
            </div>


        </>
    );
}

export default CreateVehicle;