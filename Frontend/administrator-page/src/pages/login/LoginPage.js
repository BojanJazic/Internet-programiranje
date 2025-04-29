import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { useState } from 'react';
import {FaEye, FaEyeSlash} from 'react-icons/fa';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios, { HttpStatusCode } from 'axios';
import Registration from '../registration/Registration'
import MainPage from '../mainPage/MainPage';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';




function Login() {

    const { register, handleSubmit, formState: { errors } } = useForm();
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ username: "", password: "", role: "" });

    const togglePasswordVisibility = () => {
      setShowPassword(!showPassword);
    }

    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };


    /**
     * 
     * Axios tretira status 400 i vece kao greske i iz tog razloga kad su neispravni kredencijali baca gresku
     * u okviru greske se moze pristupiti statusu koji je dobijen: error.response.status 
     * 
     */


    const onSubmit = async (data) => {
      console.log("Kredencijali: " + data.username + " " + data.password + "  " + data.role);


      try{

        if( data.role === "ADMINISTRATOR"){

          const response = await axios.post("/administrators/login", data);

          console.log("Response:", response); // Proveri šta server tačno vraća
          
          if(response.status === 200){
            console.log("Username za slanje: " + data.username);
            navigate("/mainPage", {state :  data.username  });
            setFormData({ username: "", password: "", role: "" });
           
          }
        } else if( data.role === "OPERATOR" ){
        
          const response = await axios.post("/operators/login", data);
          console.log("Response:", response); // Proveri šta server tačno vraća
          

          if(response.status === 200){
            console.log("Username za slanje: " + data.username);
           
           //preusmjeravanje na drugu stranicu
            navigate("/operatorMainPage");
            setFormData({ username: "", password: "", role: "" });
           
          }

        } else if( data.role === "MANAGER" ){
          const response = await axios.post("/managers/login", data);
          console.log("Response:", response); // Proveri šta server tačno vraća
          
          if(response.status === 200){
            console.log("Username za slanje: " + data.username);
           
            navigate("/managerMainPage");
            setFormData({ username: "", password: "", role: "" });
           
          }
        } else {
          alert("CHOOSE ROLE");
        }
          
      }catch(error){
          console.error("Error:", error);
          alert("Check username or password");
          setFormData({ username: "", password: "", role: "" });
          
      }
    };

  return (
    <>
    <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
      <div className="container d-flex justify-content-center align-items vh-100">
        <div className="background login-background">
          <div className="shape"></div>
          <div className="shape"></div>
        </div>
        <form className="p-4 rounded shadow login-form" onSubmit={handleSubmit(onSubmit)}>
            <h3 className="text-center">LOGIN</h3>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input type="text" id="username" className="form-control login-input" {...register("username", { required: true })}
                   value={formData.username}
                   onChange={handleChange}
              />
              {errors.username && <small className="text-danger">Username is required</small>}
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <div className="password-wrapper">
                 <input type={showPassword ? "text" : "password" } id="password" className="form-control login-input" {...register("password", { required: true })} 
                     value={formData.password}
                     onChange={handleChange}
                 />
                 <span className="eye-icon" onClick={togglePasswordVisibility}>
                    {showPassword ? "🔑" : "👁️" }
                 </span>
              </div>

              {errors.password && <small className="text-danger">Password is required</small>}
            </div>
            <div className="mb-3">
              <label htmlFor="role" className="form-label">Role</label>
                <select
                  name="role"
                  className="form-control"
                  {...register("role", { required: true })}
                  value={formData.role}
                  onChange={handleChange}
                >
                <option value="">Choose role</option>
                <option value="ADMINISTRATOR">ADMINISTRATOR</option>
                <option value="OPERATOR">OPERATOR</option>
                <option value="MANAGER">MANAGER</option>
              </select>


              {errors.role && <small className="text-danger">Role is required</small>}
            </div>
            <div className="login-button">
              <button type="submit" className="button-text">Log in</button>
            </div>
           <label className="registration">
              <Link to="/register"><u>Create new account</u></Link>
            </label>
           {/* type="submit" className="btn btn-light w-100" */}
        </form>
        </div>
    </body>
    </>

  );
}

export default Login;
