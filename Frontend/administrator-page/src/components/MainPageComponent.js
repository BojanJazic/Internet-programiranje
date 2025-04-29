import '../pages/managerMainPage/ManagerMainPage.css';


//nije najbolja praksa ovako navoditi dugmad, ali...
const MainPageComponent = ({ userType, 
                             firstButtonClicked, 
                             secondButtonClicked, 
                             thirdButtonClicked, 
                             fourthButtonClicked }) => {

        return (
            <div className="container-fluid d-flex justify-content-center align-items-start min-vh-100">
            <div>
                 <h2>{userType === "administrator" ?  "Administrator page" : "Operator page"}</h2>
                 <form>
                     <div className="row">
                         <div className="col-12 col-md-5 row-width">
                             <div className="mb-3">
                                 <button type="button" className="btn w-90" onClick={firstButtonClicked}>{userType === "administrator" ?  "Vehicles management" : "Show rentals"}</button>
                             </div>
                             <div className="mb-3">
                             <button type="button" className="btn w-90" onClick={secondButtonClicked}>{userType === "administrator" ?  "Manufacturers management" : "Show map"}</button>
                             </div>
                         </div>
                         <div className="col-12 col-md-5 row-width">
                             <div className="mb-3">
                                 <button type="button" className="btn w-90" onClick={thirdButtonClicked}>{userType === "administrator" ?  "Clients management" : "Show clients"}</button>
                             </div>
                             <div className="mb-3">
                             <button type="button" className="btn w-90" onClick={fourthButtonClicked}>{userType === "administrator" ?  "User profile" : "Malfunctions"}</button>
                             </div>
                         </div>
                     </div>
                 </form>
             </div>
       </div>
        );



};

export default MainPageComponent;