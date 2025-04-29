import axios from "axios";


const FetchData = async ({
                endpoint
        }) => {
        try{
            const response = await axios.get(endpoint);
            return response.data;
        }catch(error){
            console.error(`Error fetching data from ${endpoint}:`, error);
            return null;
        }
   
};

export default FetchData;