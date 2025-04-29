

import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

import BackButton from '../../components/BackButton';
import '../managerStatisticsReview/StatisticsReview.css';
import backIcon from '../../back.png';
import ShowClientsButtons from '../../components/ShowClientsButtons';
import { useEffect, useState } from 'react';
import FetchData from '../../components/FetchData';
import axios from 'axios';
import ChartsView from '../../components/ChartsView';

function StatisticsReview() {

    const months = ["Januar",
                    "Februar",
                    "Mart",
                    "April",
                    "Maj",
                    "Jun",
                    "Jul",
                    "Avgust",
                    "Septembar",
                    "Oktobar",
                    "Novembar",
                    "Decembar"
    ];

    const years = [2015,
                   2016,
                   2017,
                   2018,
                   2019,
                   2020,
                   2021,
                   2022,
                   2023,
                   2024,
                   2025
    ];

    const [ selectedMonth, setSelectedMonth ] = useState("");
    const [ selectedYear, setSelectedYear ] = useState("");

    const [ totalIncome, setTotalIncome ] = useState([]);
    const [ malfunctionsNumber, setMalfunctionsNumber ] = useState([]);
    const [ monthIncome, setMonthIncome ] = useState([]);

    const [ view, setView ] = useState("");


    const fetchData = async () => {

       
        try{
            console.log("Current view = ", view);
            const response = await axios.get("/malfunctions/perVehicle");
            if(response.data){
                //console.log(response);
                const transformedData = Object.entries(response.data).map(([vehicle, numberOfMalfunctions]) => ({vehicle, numberOfMalfunctions}));
                
                setMalfunctionsNumber(transformedData);
                console.log(transformedData);
            }

        }catch(error){
            console.log(error);
        }
    };

    const fetchIncome = async () => {

        console.log("Current view = ", view);

        try{
            const response = await axios.get("/rentals/incomeByVehicleType");
            if(response.data){
                //console.log(response);
                const transformedData = Object.entries(response.data).map(([vehicle, totalIncome]) => ({vehicle, totalIncome}));
                
                setTotalIncome(transformedData);
                console.log(transformedData);
            }

        }catch(error){
            console.log(error);
        }
    };

    const fetchMonthIncome = async () => {

        console.log("Current view = ", view);

        try{
            const response = await axios.get(`/rentals/income/${selectedYear}/${selectedMonth}`);
            if(response.data){
                //console.log(response);
                const transformedData = Object.entries(response.data).map(([vehicle, dayIncome]) => ({vehicle, dayIncome}));
                
                setMonthIncome(transformedData);
                console.log(transformedData);
            }

        }catch(error){
            console.log(error);
        }
    };
    
    


    return(
        <>
            <div className="statistics-background">
                <div className="options-side">
                    <div className="corner-element">
                        <button type="button" className="button-text" onClick={() => window.history.back()}>
                            <img src={backIcon} alt="Back" width="20" height="20"/>
                        </button>
                    </div>

                    <div className="buttons-management">
                        <button type="button" onClick={() => {
                            fetchIncome();
                            setView("totalIncome");
                        }}>Total income by vehicle type</button>

                        <button type="button" onClick={()=> {
                            fetchData();
                            setView("malfunctions");
                        }}>Number of malfunctions by vehicle</button>

                        <button 
                            type="button"
                            disabled={!selectedMonth && !selectedYear}
                            onClick={() => {
                                fetchMonthIncome();
                                setView("monthIncome");
                            }}
                        >Total price during month</button>
                        <div className="select-container">
                            <select
                                name="choosenMonth"
                                className="form-control"
                                onChange={(e) => setSelectedYear(e.target.value)}

                            >
                                <option
                                    value=""
                                    >Choose year
                                </option>

                                {years.map((y) => (
                                    <option key={y} value={y}>
                                        {y}
                                    </option>
                                ))}

                            </select>
                            <select
                                name="choosenMonth"
                                className="form-control"
                                onChange={(e) => setSelectedMonth(e.target.value)}

                            >
                                <option
                                    value=""
                                    >Choose month
                                </option>

                                {months.map((m) => (
                                    <option key={m} value={m}>
                                        {m}
                                    </option>
                                ))}

                            </select>
                        </div>
                    </div>


                </div>

                <div className="charts">
                        
                    <div style={{ width: '100%', height: '100%' }}>
                            {view === "totalIncome" && (
                                <ChartsView 
                                    data={totalIncome}
                                    dataKey={"totalIncome"}    
                                />
                            )}

                            {view === "malfunctions" && (
                                <ChartsView 
                                    data={malfunctionsNumber}
                                    dataKey={"numberOfMalfunctions"}    
                                />
                            )}

                            {view === "monthIncome" && (
                                <ChartsView 
                                    data={monthIncome}
                                    dataKey={"dayIncome"}
                                />
                            )}
                    </div>

                </div>
            </div>
        </>
    );
}

export default StatisticsReview;