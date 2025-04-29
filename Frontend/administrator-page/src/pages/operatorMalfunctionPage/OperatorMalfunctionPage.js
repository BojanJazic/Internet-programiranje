import './OperatorMalfunctionPage.css';
import backIcon from '../../back.png';
import { useEffect, useState } from 'react';
import axios from 'axios';
import FetchData from '../../components/FetchData';
import MalfunctionsTable from '../../components/MalfunctionsTable';
import MalfunctionModal from '../modals/MalfunctionModal';


function OperatorMalfunctionPage () {

    const [ vehicles, setVehicles ] = useState([]);
    const [selectedVehicle, setSelectedVehicle] = useState("");
    const [ malfunctions, setMalfunctions ] = useState([]);
    const [selectedMalfunctionId, setSelectedMalfunctionId] = useState(null);
    
    const [ isModalOpen, setIsModalOpen ] = useState(false);
    const [ formData, setFormData ] = useState({
            idVehicle: selectedVehicle || "",
            description: "",
            dateTime: "",
        });


        useEffect(() => {
            fetchData();

        }, []);


        useEffect(() => {
            setFormData((prevFormData) => ({
                ...prevFormData,
                idVehicle: selectedVehicle,
            }));
        }, [selectedVehicle]);

        const fetchData = async () => {

            const result = await FetchData({endpoint: "/vehicles"});
            if(result){
                setVehicles(result);
            }
        };

        const fetchMalfunctions = async () => {
            if (selectedVehicle) {
                const response = await FetchData({ endpoint: `/malfunctions/${selectedVehicle}` });
                if (response) {
                    // Provjerite da li je odgovor niz, ako nije, pretvorite ga u niz
                    setMalfunctions(Array.isArray(response) ? response : [response]);
                }
            }
        };

        const handleOpenModal = () => {
            setIsModalOpen(true);
        }

        const resetData = () => {
            setFormData({ idVehicle: selectedVehicle, description: "", dateTime: "" }); // Resetovanje polja
            setIsModalOpen(false);
        };

        const handleCloseModal = () => {
            resetData();
         };

         const handleSave = async () => {
            const dataToSend = { ...formData };
            console.log("Podaci za slanje:", dataToSend);
        
            try {
                await axios.post("/malfunctions", dataToSend);
                await fetchMalfunctions(); // Ponovo učitaj kvarove nakon dodavanja
            } catch (error) {
                alert(error);
            }
        
            resetData();
        };

    return(

        <>
        
            <div className="operator-background">
                <div className="left-pane">
                    <div className="corner-element">
                        <button type="button" className="button-text" onClick={() => window.history.back()}>
                            <img src={backIcon} alt="Back" width="20" height="20"/>
                        </button>
                    </div>

                    <div className="option">
                        <select
                            name="choosenVehicle"
                            className="form-control"
                            onChange={(e) => setSelectedVehicle(e.target.value)}
                        
                        >
                            <option value="">Choose vehicle</option>
                            {vehicles.map((v) => (
                                <option key={v.idVehicle} value={v.idVehicle}>
                                    {v.idVehicle}
                                    {' '}
                                    {v.manufacturer}
                                    {' '}
                                    {v.model}
                                </option>
                            ))}
                        </select>

                        <button type="button"
                            onClick={() => {
                               //ovdje ce se pozvati ova metoda za ucitavanje kvarova
                               //i otvorice se modalni prozor
                               console.log("SELECTED VEHICLE: ", selectedVehicle);

                               if(selectedVehicle){
                                    fetchMalfunctions();
                               }
                            }}
                        >Show malfunction</button>

                        <button
                            type="button"
                            disabled={!selectedVehicle} // Onemogućeno ako nije izabrano vozilo
                            onClick={() => {
                                // Logika za dodavanje novog kvara
                                console.log("Dodavanje novog kvara za vozilo:", selectedVehicle);
                                handleOpenModal();
                            }}
                        >
                            Add malfunction
                        </button>

                        <MalfunctionModal
                        isOpen={isModalOpen}
                        onClose={handleCloseModal}
                        onSave={handleSave}
                        formData={formData}
                        setFormData={setFormData}
                    />

                    </div>

                    
                </div>



                <div className="right-pane">
                    {/**OVDJE CE SE PRIKAZATI KVAROVI OD VOZILA */}
                    <MalfunctionsTable
                        data={malfunctions}
                        setSelectedMalfunctionId={setSelectedMalfunctionId}
                        selectedMalfunctionId={selectedMalfunctionId}
                    />
                    {malfunctions.length === 0 && <p>Nema kvarova za izabrano vozilo.</p>}
                </div>
            </div>
        
        </>

    );

}


export default OperatorMalfunctionPage;