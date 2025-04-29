import { Tab } from "react-bootstrap"
import BackButton from "../../components/BackButton"
import { useLocation, useNavigate } from "react-router-dom";

import backIcon from '../../back.png';


const HomeTab = ({
    createVehicleClicked,
    uploadCSVFile,
    fileInputRef,
    handleFileChange,
  })=>{
    
    const navigate = useNavigate();
    const location = useLocation();

    const userType = location.state?.userType || "administrator";
    
    const backToMainPage = () => {
      navigate("/mainPage"); // Preusmeri na specifičnu stranicu
    };
    
    

    return (
      <div className="tab-content">
        <div className="fullscreen-background"/>
        <div className="home-corner-element">
            <button type="button" className="details-button-text" onClick={() => navigate(userType === "manager" ? "/managerMainPage" : "/mainPage")}> {/** */}
              <img src={backIcon} alt="Back" width="20" height="20"/>
          </button>
        </div>
      <div className="buttons-container">
          {/* <button className="bottom-buttons" type="button" onClick={showManufacturers}>Manufacturers</button> */}
          <button className="bottom-buttons" type="button" onClick={createVehicleClicked}>Create vehicle</button>
          <button className="bottom-buttons" onClick={uploadCSVFile} type="button">Load CSV</button>
          <input
              type="file"
              accept=".csv"
              ref={fileInputRef}
              onChange={handleFileChange}
              style={{display: "none"}} //skriva input polje
          />
      </div>

      </div>
    )
}

export default HomeTab;