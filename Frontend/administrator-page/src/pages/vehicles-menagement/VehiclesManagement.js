import React, { useState, useEffect, useRef } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Tab, Tabs } from 'react-bootstrap';
import "./VehiclesManagement.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import HomeTab from "./HomeTab";
import CarsTab from "./CarsTab";
import BikesTab from "./BikesTab";
import ScootersTab from "./ScootersTab";


function VehiclesManagement() {


    //ovo ostaje
    const fileInputRef = useRef(null);
    const [ selectedFile, setSelectedFile] = useState(null);
    const [ selectedVehicleId, setSelectedVehicleId ] = useState(null);



    const [activeTab, setActiveTab] = useState("home"); // Početni tab

    const [key, setKey] = useState('cars');
    const [ cars, setCars ] = useState([]);
    const [ eBikes, setEBikes ] = useState([]);
    const [ eScooters, setEScooters] = useState([]);
    const navigate = useNavigate();

    //dio za brisanje
    const [ contextMenu, setContextMenu ] = useState({ visible: false, x: 0, y: 0, selectedId: null});
    const [ showConfirm, setShowConfirm ] = useState(false);
    const [ isModalOpen, setIsModalOpen ] = useState(false);
   

    const [ data, setData ] = useState([]); //podaci iz baze

    const [ currentPage, setCurrentPage ] = useState(0); //trenutna stranica
    const [ itemsPerPage, setItemsPerPage ]= useState(5);  // Broj elemenata po stranici

    
    const [ parsedData, setParsedData ] = useState(null);


    // Generička funkcija za učitavanje podataka
    const fetchData = async (endpoint, setData) => {
        try {
            const response = await fetch(`${endpoint}`);
            const result = await response.json();
            setData(result);
        }catch(error){
            console.error(`Error fetching data from ${endpoint}: `, error);
        }
    };

    useEffect(() => {
        console.log(`Tab promenjen na: ${key}`);
        if (key === 'cars') {
            console.log("Učitavam automobile...");
            fetchData("cars", setCars);
        }
        if (key === 'eBikes') {
            console.log("Učitavam eBikes...");
            fetchData("e-bikes", setEBikes);
        }
        if (key === 'eScooters') {
            console.log("Učitavam eScooters...");
            fetchData("e-scooters", setEScooters);
        }
    }, [key]);

    const refreshData = () => {
        if (key === 'cars') fetchData("cars", setCars);
        if (key === 'eBikes') fetchData("e-bikes", setEBikes);
        if (key === 'eScooters') fetchData("e-scooters", setEScooters);
    };

    const getCurrentData = () => {
        switch (key) {
            case 'cars': return cars;
            case 'eBikes': return eBikes;
            case 'eScooters': return eScooters;
            default: return [];
        }
    };
    

    //racunanje podataka za trenutnu stranicu
    const currentData = getCurrentData();
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentPageData = currentData.slice(startIndex, endIndex);
    const pageCount = Math.ceil(currentData.length / itemsPerPage);


    //funkcija za promjenu stranice
    const handlePageClick = ({ selected }) => {
        setCurrentPage(selected);
    };

    //funkcija za promjenu broja elemenata po stranici
    const handleItemsPerPageChange = (e) => {
        setItemsPerPage(Number(e.target.value));
        setCurrentPage(0); //resetujemo paginaciju
    }

    const showPagination = getCurrentData.length > itemsPerPage;
    
    const searchRefCars = useRef(null);
    const searchRefBikes = useRef(null);
    const searchRefScooters = useRef(null);

    const filterTable = (tableName, ref) => {
        console.log("Tabela za filtriranje:", tableName);
        console.log("Ref:", ref);
        console.log("Ref.current:", ref?.current);

        const input = ref?.current;
        if (!input) {
            console.error("❌ Input reference je null za tabelu:", tableName);
            return;
        }

        const filter = input.value.trim().toUpperCase();
        const table = document.getElementById(tableName);

        console.log("Tabela DOM element:", table);

        if (!table) {
            console.error(`❌ Nema tabele sa id="${tableName}"`);
            return;
        }

        const rows = table.getElementsByTagName("tr");
        
        if(filter === ''){
            for (let i = 1; i < rows.length; i++) {
                rows[i].style.display = "";
              }
              return;
        }

        //iterira kroz svaki red
        for(let i = 1; i < rows.length; i++){
            const cells = rows[i].getElementsByTagName("td");

            //filtriramo prema modelu i proizvodjacu
            let match = false;

            for(let j = 0; j < cells.length; j++){
                if(cells[j].textContent.toUpperCase().includes(filter)){
                    match = true;
                    break;
                }
            }

             rows[i].style.display = match ? "" : "none";

        }
    }


    const createVehicleClicked = () => {
        navigate("/createVehicle");
    }

   
    

    const showDetails = async () => {

        try {
            let response = null;
            
            //u backend dijelu promjeniti malo ove endpointe

            if (key === 'cars'){
                response = await axios.get(`/cars/${selectedVehicleId}`);
            }
            else if (key === 'eBikes'){
                response = await axios.get(`/e-bikes/${selectedVehicleId}`);
            }
            else if (key === 'eScooters'){
                response = await axios.get(`/e-scooters/${selectedVehicleId}`);
            }
           // const vehicleDetails = await response.json();
            navigate("/showDetails", { state: response.data });
    
        }catch(error){
            alert("GRESKA PRI DOHVATANJU JEDNOG VOZILA: " + error);
        }

    }    

    const uploadCSVFile = () => {
        fileInputRef.current.click();   //otvara file explorer
    }

    const handleFileChange = (event) => {
        //ovdje treba logika koja ce otvoriti sistemski prozor

        const selectedFile = event.target.files[0];
        setSelectedFile(selectedFile);

        if(selectedFile) {
            const reader = new FileReader();
            reader.onload = (e) => {
               const parsedData = parseCSV(e.target.result);

               sendDataToBackend(parsedData[0]);
            };
            reader.readAsText(selectedFile);
        }
    }

    const parseCSV = (csvText) => {
        const rows = csvText.split("\n").map(row => row.trim()).filter(row => row.length > 0); // Uklanja prazne redove
       
        const header = rows[0].split(",");
        const dataWithoutHeader = rows.slice(1);

        const data = dataWithoutHeader.map((row) => {
            const columns = row.split(", ").map(col => col.trim());

            if( columns.length > 10 || columns.length < 9 ){
                alert("Neispravan csv fajl");
                return;
            }

            if(header.length === 10 && header[9].trim() === "description"){
                return {
                    idVehicle: columns[0] || null,
                    idManufacturer: parseInt(columns[1]) || null,
                    manufacturer: columns[2] || null,
                    model: columns[3] || null,
                    purchasePrice: parseInt(columns[4]) || null,
                    isRented: columns[5] === "1" ? 1 : 0,
                    isBroken: columns[6] === "1" ? 1 : 0,
                    image: columns[7] || null,
                    purchaseDate: new Date(columns[8]).toISOString().split("T")[0] || null,
                    description: columns[9] || null,
                };
            }

           else if(header.length === 9 &&  header[8].trim() === "autonomy"){
                return {
                    idVehicle: columns[0] || null,
                    idManufacturer: parseInt(columns[1]) || null,
                    manufacturer: columns[2] || null,
                    model: columns[3] || null,
                    purchasePrice: parseInt(columns[4]) || null,
                    isRented: columns[5] === "1" ? 1 : 0,
                    isBroken: columns[6] === "1" ? 1 : 0,
                    image: columns[7] || null,
                    autonomy: parseInt(columns[8]) || null,
                };
            }

           else if(header.length === 9 && header[8].trim() === "maxSpeed"){
                return {
                    idVehicle: columns[0] || null,
                    idManufacturer: parseInt(columns[1]) || null,
                    manufacturer: columns[2] || null,
                    model: columns[3] || null,
                    purchasePrice: parseInt(columns[4]) || null,
                    isRented: columns[5] === "1" ? 1 : 0,
                    isBroken: columns[6] === "1" ? 1 : 0,
                    image: columns[7] || null,
                    maxSpeed: parseInt(columns[8]) || null,
                };
            }
           
           
          }).filter(d => d !== null); //uklanja null vrijednosti

          alert(JSON.stringify(data, null, 2));

          return data;
              
    }

    const sendDataToBackend = async (data) => {

        try{
            let response = null;
            if(Object.keys(data).length === 10){

                response = await axios.post("/cars", data);
            } else if (Object.keys(data).length === 9) {
                if (data.hasOwnProperty("autonomy")) {
                    response = await axios.post("/e-bikes", data);
                } else if (data.hasOwnProperty("maxSpeed")) {
                    response = await axios.post("/e-scooters", data);
                }
            }
            fetchData();


        }catch(error){
            alert("Problem adding vehicle!");
        }

    }

    //trenutno aktivni tab
    const handleSelect = (eventKey) => {
        setActiveTab(eventKey);
    };



    //brisanje na desni klik
    //detektuj desni klik i prikazi konteksni meni
    const handleRightClick = (event, idVehicle) => {
        event.preventDefault(); //sprijecava prikazivanje podrazumijevanog konteksnog menija
        setSelectedVehicleId(idVehicle);
        setContextMenu({
            visible: true,
            x: event.pageX,
            y: event.pageY,
            selectedId: idVehicle,
        });
    };

    //zatvori konteksni meni
    const closeContextMenu = () => {
        console.log("Closing context menu");
        setContextMenu({ visible: false, x: 0, y: 0, selectedId: null });
    };

    //obrisi red iz tabele i baze
    //ovdje samo staviti koji tip vozila je odabran da bi se znalo koji se endpoint poziva
    //pogledati kako da dobijem trenutno aktivni tab
    const handleDelete = () => {
       // setSelectedVehicleId(vehicleId);
        setShowConfirm(true);
        setIsModalOpen(true);
    }

    const confirmDelete = async () => {
        //alert(selectedVehicleId);
        if( !selectedVehicleId )
            return;
        
        try{
            if( key === 'cars' ){
                await axios.delete(`/cars?idVehicle=${selectedVehicleId}`);
            } else if( key === 'eBikes' ){
                await axios.delete(`/e-bikes?idVehicle=${selectedVehicleId}`);
            } else if( key === 'eScooters' ){
                await axios.delete(`/e-scooters?idVehicle=${selectedVehicleId}`);
            }

            

        }catch(error){
            alert("Greska pri brisanju: ", error);
        }
        fetchData();
        setShowConfirm(false);
        setIsModalOpen(false);
        setSelectedVehicleId(null);
        closeContextMenu();
    };

    const cancelDelete = () => {
        setShowConfirm(false);
        setIsModalOpen(false);
        setSelectedVehicleId(null);
        closeContextMenu();
    };

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);




    const commonProps = {
        filterTable,
        itemsPerPage,
        handleItemsPerPageChange,
        pageCount,
        handlePageClick,
        currentPage,
        refreshData,
        currentPageData,
        handleRightClick,
        contextMenu,
        closeContextMenu,
        isModalOpen,
        handleDelete,
        showDetails,
        showConfirm,
        confirmDelete,
        cancelDelete
    };
    
  
    
    return (
        <>
        
            <div className="container-fluid p-0 custom-tabs-container">
                <div className="bg-light shadow-sm fixed-top">
                    {/* <HomeTab /> */}
                <Tabs
                        defaultActiveKey="home"
                        onSelect={(k) => setKey(k)}
                        id="fill-tab-example"
                        className="nav-justified"
                    >
                        {/* <Tab eventKey="home2" title="testic" >
                            <HomeTab userId={userId} setUserId={setu}/>
                        </Tab> */}
                        <Tab eventKey="home" title="Home" > 
                        <HomeTab
                            createVehicleClicked={createVehicleClicked}
                            uploadCSVFile={uploadCSVFile}
                            fileInputRef={fileInputRef}
                            handleFileChange={handleFileChange}
                        />
                           
                        </Tab>
                        <Tab eventKey="cars" title="Cars">

                            <CarsTab 
                                searchRefCars={searchRefCars}
                                {...commonProps}
                               
                            />

                        </Tab>
                        
                        <Tab eventKey="eBikes" title="E-bikes">

                            <BikesTab 
                                searchRefBikes={searchRefBikes}
                                {...commonProps}
                                
                            />

                            
                </Tab>
                <Tab eventKey="eScooters" title="E-scooters">
                            <ScootersTab 
                                searchRefScooters={searchRefScooters}
                                {...commonProps}
                            />
                </Tab>
            </Tabs>
                    
                </div>
                
            </div>

            
        </>
    );
}



export default VehiclesManagement;