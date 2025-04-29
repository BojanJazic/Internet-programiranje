import axios from 'axios';
import '../rentals/Rentals.css';
import { useState, useEffect } from 'react';
import RentalControls from '../../components/RentalControls';


function OperatorRentals () {


    const [ rentals, setRentals ] = useState([]);
    
    useEffect(() => {
        fetchData();
    }, []);
    
    const fetchData = async () => {
        try{
            const response = await axios.get("/rentals");
            setRentals(response.data);
        }catch(error){
            console.error(error);
        }
    }


    return(
        <>
            <RentalControls 
                data={rentals}
                showTo={"operator"}
            />
        </>
    );

}


export default OperatorRentals;

