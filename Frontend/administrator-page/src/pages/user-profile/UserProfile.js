import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './UserProfile.css';
import backIcon from '../../back.png';
import avatar from '../../images/download.png';
import logoutPic from '../../images/logout.png';
import axios from 'axios';

function ShowUserInformation() {
    const navigate = useNavigate();
    const location = useLocation();
    const username = location.state;

    
    console.log("Username: " + username);

    const [formData, setFormData] = useState({
        name: "",
        surname: "",
        username: "",
    });

    const logout = () => {
        navigate("/");
    };

    // Dohvatanje podataka za korisnika sa korisničkim imenom
    useEffect(() => {
        fetchData();
    }, [username]);

    const fetchData = async () => {
        try {
            const response = await axios.get(`/administrators/byUsername/${username}`);
            setFormData({
                name: response.data.name,
                surname: response.data.surname,
                username: response.data.username,
            });
        } catch (error) {
            console.error("Error fetching user data:", error);
        }
    };

    return (
        <div className="user-profile-background">
            <div className="corner-element">
                <button type="button" className="button-text" onClick={() => window.history.back()}>
                    <img src={backIcon} alt="Back" width="20" height="20"/>
                </button>
            </div>
            <div className="user-profile-card">
                <img src={avatar} alt="Slika"/>
            
                <label>Name:
                    <input type="text" value={formData.name} readOnly/>
                </label>

                <label>Surname:
                    <input type="text" value={formData.surname} readOnly/>
                </label>

                <label>Username:
                    <input type="text" value={formData.username} readOnly/>
                </label>
            </div>

            <div className="bottom-corner-element">
                <button type="button" className="profile-button-text" onClick={logout}>
                    <img src={logoutPic} alt="Logout" width="50" height="40"/>
                </button>
            </div>
        </div>
    );
}


export default ShowUserInformation;