

import { useNavigate } from 'react-router-dom';
import MainPageComponent from '../../components/MainPageComponent';
import '../mainPage/MainPage.css';
import logoutPic from '../../images/logout.png';
import '../user-profile/UserProfile.css';


function OperatorMainPage() {

    const navigate = useNavigate();

    const showRentals = () => {
        navigate("/operatorRentals");
    };

    const showMap = () => {
        navigate("/rentalsMap");
    }

    const showMalfunctions = () => {
       navigate("/malfunctions");
    }

    const showClients = () => {
       navigate("/clientsReview");
    };

    const logout = () => {
        navigate("/");
    }

    return(
        <>
            <MainPageComponent 
                userType={"operator"}
                firstButtonClicked={showRentals}
                secondButtonClicked={showMap}
                thirdButtonClicked={showClients}
                fourthButtonClicked={showMalfunctions}
            />

            <div className="bottom-corner-element">
                <button type="button" className="profile-button-text" onClick={logout}>
                    <img src={logoutPic} alt="Logout" width="50" height="40"/>
                </button>
            </div>

        </>
    );

}

export default OperatorMainPage;