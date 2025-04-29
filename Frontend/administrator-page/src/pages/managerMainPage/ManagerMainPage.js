import { useNavigate } from "react-router-dom";
import ManagerMainPageComponent from "../../components/ManagerMainPageComponent";
import logoutPic from '../../images/logout.png';


function ManufacturerMainPage () {

    const navigate = useNavigate();




    const showMap = () => {
        navigate("/rentalsMap");
    }

    const showManufacturers = () => {
        navigate("/manufacturers");
    }

    const showClients = () => {
        navigate("/usersManagement");
    };

    const showVehicles = () => {
        navigate("/vehiclesManagement", {state: {userType: "manager"}});
    };

    const logout = () => {
        navigate("/");
    }

    const showStatistics = () => {
        navigate("/statistics");
    }

    const priceDefining = () => {
        navigate("/priceDefining");
    }


    return(
        <>
            <ManagerMainPageComponent 
                firstButtonClicked={showVehicles}
                secondButtonClicked={showManufacturers}
                thirdButtonClicked={showClients}
                fourthButtonClicked={showMap}
                fifthButtonClicked={showStatistics}
                sixthButtonClicked={priceDefining}
            />


            <div className="bottom-corner-element">
                <button type="button" className="profile-button-text" onClick={logout}>
                    <img src={logoutPic} alt="Logout" width="50" height="40"/>
                </button>
            </div>
        </>
    );
    
}


export default ManufacturerMainPage;