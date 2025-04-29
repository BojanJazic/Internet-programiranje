import React from 'react';
import backIcon from '../back.png';


const RentalControls = ({ data, showTo }) => {
    return (
    <div className="rental-background">
                <div className="header">
                <div className="rental-corner-element">
                    <button type="button" className="rental-button-text" onClick={() => window.history.back()}>
                        <img src={backIcon} alt="Back" width="20" height="20"/>
                    </button>
                </div>

                <h2>Rentals</h2>
                </div>

                <div className="table-space">
                <div className="tab-content">
                    <div className="table-responsive table-container">
                        <table id="rentals" className="table table-striped table-bordered table-hover">
                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                {showTo === "operator" && (

                                                    <React.Fragment>
                                                    <th>idVehicle</th>
                                                    <th>Manufacturer</th>
                                                    <th>Model</th>
                                                    </React.Fragment>
                                                )}

                                                <th>Name</th>
                                                <th>Surname</th>
                                                <th>Date and time</th>
                                                <th>Duration[hours]</th>
                                                <th>Pick up location</th>
                                                <th>Drop off location</th>
                                                <th>Price[KM]</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {data.map((rental) => (
                                                <tr key={rental.rentalId}>
                                                    <td>{rental.rentalId}</td>
                                                    {showTo === "operator" && (
                                                        <React.Fragment>
                                                            <td>{rental.idVehicle}</td>
                                                            <td>{rental.manufacturer}</td>
                                                            <td>{rental.model}</td>
                                                        </React.Fragment>
                                                    )}
                                                    <td>{rental.name}</td>
                                                    <td>{rental.surname}</td>
                                                    <td>{rental.rentalDate}</td>
                                                    <td>{rental.rentalDuration}</td>
                                                    <td>{rental.pickupLocation.coordinateX}{', '}{rental.pickupLocation.coordinateY}</td>
                                                    <td>{rental.dropOffLocation.coordinateX}{', '}{rental.dropOffLocation.coordinateY}</td>
                                                    <td>{rental.price}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                        </table>

                    </div>
                </div>
                </div>
                   
                
                          




            </div>

    );
};


export default RentalControls;