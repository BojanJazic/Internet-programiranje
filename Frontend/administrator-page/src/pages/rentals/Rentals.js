
import { useLocation } from 'react-router-dom';
import backIcon from '../../back.png';
import './Rentals.css';
import { useState, useEffect } from 'react';
import axios from 'axios';
import RentalControls from '../../components/RentalControls';


function ShowRentals() {
    //preuzimam vrijednost id-a
    const location = useLocation();
    const vehicle = location.state;
    let idVehicle;
    //podaci iz baze
    const [ data, setData ] = useState([]);

    if (vehicle) {
        idVehicle = vehicle.idVehicle;
    } else {
        // Obradite slučaj kada state nije definisan
        console.error("State nije definisan");
    }

    //ovdje cu ucitati podatke za trazeno vozilo

    useEffect(() => {
        fetchData();
    }, [idVehicle]);

    const fetchData = async () => {
        try{
            const response = await axios.get(`/rentals/${idVehicle}`);
            //const result = await response.json();
            const sortedData = response.data.sort((a, b) => a.id - b.id);
        
            setData(sortedData);
        }catch(error){
            console.error(error);
        }
    }



    return(

        <>

            <RentalControls 
                data={data} 
                showTo={"administrator"}
            />
            
        </>

    );
}

export default ShowRentals;