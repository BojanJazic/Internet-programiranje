import { useState } from "react";
import FetchData from "./FetchData";
import axios from "axios";


const ShowClientsButtons = ({ handleBlockClient, handleUnblockClient, selectedClient }) => {

    return(
        <div className="option-buttons">
            <button type="button" 
                    onClick={handleBlockClient}
                    disabled={!selectedClient || selectedClient.isBlocked === 1}
                    >Block
            </button>
            <button type="button" 
                    onClick={handleUnblockClient}
                    disabled={!selectedClient || selectedClient.isBlocked === 0}
                    >Unblock
            </button>
        </div>
    );

}

export default ShowClientsButtons;