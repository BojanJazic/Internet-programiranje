import { useEffect, useState } from 'react';
import '../../components/ClientsReview';
import ClientsReview from '../../components/ClientsReview';
import FetchData from '../../components/FetchData';
import '../../pages/users-management/UsersManagement.css';
import axios from 'axios';
import ShowClientsButtons from '../../components/ShowClientsButtons';
import backIcon from '../../back.png';

function OperatorClientsReview() {
    
    const [ clients, setClients ] = useState([]);
    const [ selectedClient, setSelectedClient ] = useState(null);


    useEffect(() => {
        fetchData();
    });


    const fetchData = async () => {
        const clientsData = await FetchData({ endpoint: "/clients" });
        if (clientsData) {
            setClients(clientsData);
        }
    }

    //gadjaj endpoint za dati id i azuriraj tamo stanje
    const handleBlockClient = async () => {
        // Logika za blokiranje klijenta
        console.log("Block client:" + selectedClient.idClient);
        try{
            if( selectedClient.isBlocked === 0){

                const response = await axios.put(`/clients/${selectedClient.idClient}`);

                //ovdje je sad problem kako da osvjezim podatke nakon unosa
                fetchData("clients"); // Call the async function
            }
        }catch(error){
            console.error(error);
        }


    };
    
    const handleUnblockClient = async () => {
        // Logika za odblokiranje klijenta
       console.log("Unblock client:" + selectedClient.idClient);

        try{
            if( selectedClient.isBlocked === 1){

                const response = await axios.put(`/clients/${selectedClient.idClient}`);

                fetchData("clients"); // Call the async function
            }
        }catch(error){
            console.error(error);
        }
    };

    return (
        <>
            <div className="users-management">
                <div className="left-panel">
                    <div className="corner-element">
                        <button type="button" className="button-text" onClick={() => window.history.back()}>
                            <img src={backIcon} alt="Back" width="20" height="20"/>
                        </button>
                    </div>

                    <div className="buttons-management">
                           
                        <ShowClientsButtons 
                            handleBlockClient={handleBlockClient}
                            handleUnblockClient={handleUnblockClient}
                            selectedClient={selectedClient}
                            
                        />



                    </div>
                </div>
                <div className="right-panel">

                    
                     <ClientsReview 
                        clients={clients}
                        setSelectedClient={setSelectedClient}
                        selectedClient={selectedClient}
                     />

                        
                    
                            
                </div>
            </div>
        </>

    );

}


export default OperatorClientsReview;