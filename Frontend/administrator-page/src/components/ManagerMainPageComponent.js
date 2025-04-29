
//nije najbolja praksa ovako navoditi dugmad, ali...
const ManagerMainPageComponent = ({
    firstButtonClicked, 
    secondButtonClicked, 
    thirdButtonClicked, 
    fourthButtonClicked,
    fifthButtonClicked,
    sixthButtonClicked

}) => {

    return (
        <div className="container-fluid d-flex justify-content-center align-items-start min-vh-100">
            <div>
            <h2>Manager page</h2>
            <form>
                <div className="row">
                <div className="col-12 col-md-5 row-width">
                    <div className="mb-3">
                        <button type="button" className="custom-button" onClick={firstButtonClicked}>Vehicles management</button>
                    </div>
                    <div className="mb-3">
                    <button type="button" className="custom-button" onClick={secondButtonClicked}>Manufacturers management</button>
                    </div>
                    <div className="mb-3">
                    <button type="button" className="custom-button" onClick={thirdButtonClicked}>Clients management</button>
                    </div>
                </div>
                <div className="col-12 col-md-5 row-width">
                    <div className="mb-3">
                        <button type="button" className="custom-button" onClick={fourthButtonClicked}>Show map</button>
                    </div>
                    <div className="mb-3">
                    <button type="button" className="custom-button" onClick={fifthButtonClicked}>Statistics</button>
                    </div>
                    <div className="mb-3">
                    <button type="button" className="custom-button" onClick={sixthButtonClicked}>Price defining</button>
                    </div>
                </div>
                </div>
            </form>
            </div>
        </div>
    );



};

export default ManagerMainPageComponent;