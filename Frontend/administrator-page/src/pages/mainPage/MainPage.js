import { BrowserRouter as Router, Route, Routes, Link} from "react-router-dom";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios, { HttpStatusCode } from "axios";
import '../../App'
import VehiclesManagement from "../vehicles-menagement/VehiclesManagement";
import './MainPage.css';
import backIcon from '../../back.png';
import MainPageComponent from "../../components/MainPageComponent";

function MainPage (){
    const { register, handleSubmit, formState: { errors } } = useForm();

    const navigate = useNavigate();

    const location = useLocation();
    const username = location.state;

    const vehiclesManagementClicked = () => {
        navigate("/vehiclesManagement");
    };

    //prikaz proizvodjaca
    const showManufacturers = () => {
        navigate("/manufacturers");
    }

    const showUserInformation = () => {
        navigate("/userProfile", {state : username});
    }

    const showClients = () => {
        navigate("/usersManagement");
    };
    
    return (
        <>

            <MainPageComponent 
                userType={"administrator"}
                firstButtonClicked={vehiclesManagementClicked}
                secondButtonClicked={showManufacturers}
                thirdButtonClicked={showClients}
                fourthButtonClicked={showUserInformation}
            />

          {/* <div className="container-fluid d-flex justify-content-center align-items-start min-vh-100">
               <div>
                    <h2>Administrator page</h2>
                    <form>
                        <div className="row">
                            <div className="col-12 col-md-5 row-width">
                                <div className="mb-3">
                                    <button type="button" className="btn w-90" onClick={vehiclesManagementClicked}>Vehicles management</button>
                                </div>
                                <div className="mb-3">
                                <button type="button" className="btn w-90" onClick={showManufacturers}>Manufacturers management</button>
                                </div>
                            </div>
                            <div className="col-12 col-md-5 row-width">
                                <div className="mb-3">
                                    <button type="button" className="btn w-90" onClick={showClients}>Clients management</button>
                                </div>
                                <div className="mb-3">
                                <button type="button" className="btn w-90" onClick={showUserInformation}>User profile</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
          </div>
         */}
        
        </>
    );
}



export default MainPage;