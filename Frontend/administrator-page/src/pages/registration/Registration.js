import { useForm } from "react-hook-form";
import { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios, { HttpStatusCode } from "axios";
import { useNavigate } from "react-router-dom";
import backIcon from '../../back.png';
import './Registration.css'


function Registration() {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        try{

            if ( data.role === "ADMINISTRATOR" ){

                const response = await axios.post("/administrators/registration", data);
                navigate("/");
                
            } else if ( data.role === "OPERATOR" ){

                const response = await axios.post("/operators/registration", data);
                navigate("/");
               
            } else if ( data.role === "MANAGER" ){

                const response = await axios.post("/managers/registration", data);
                navigate("/");
                
            } 
        }catch(error){
            alert("Registration failed, try again.")
           // document.querySelectorAll('input').forEach(input => input.value = '');
        }

    };

    return (
        <>
            <div className="container d-flex justify-content-center align-items vh-100">
            <div className="registration-background">
                    <div className="shape"></div>
                    <div className="shape"></div>
                </div>

                <form className="p-4 rounded shadow registration-form" onSubmit={handleSubmit(onSubmit)}>
                    
                {/* Roditeljski kontejner za dugme i naslov */}
                <div className="corner-element">
                    <button type="button" className="button-text" onClick={() => window.history.back()}>
                        <img src={backIcon} alt="Back" width="20" height="20"/>
                    </button>
                </div>

                <h3 className="registration-title">Registration</h3>
                
                <div className="row">
                        {/* Prva kolona */}
                        <div className="col-12 col-md-6">
                            <div className="mb-3">
                                 <label htmlFor="name" className="form-label">Name</label>
                                 <input type="text" id="name" className="form-control w-100  registration-input"
                                  {...register("name", {required: true})}/>
                                  {errors.name && <small className="text-danger">Name is required!</small>}
                                
                            </div>
                            <div className="mb-3">
                                <label htmlFor="username" className="form-label">Username</label>
                                <input type="text" id="username"  className="form-control w-100 registration-input"  {...register("username", {required: true})}/>
                                {errors.username && <small className="text-danger">Username is required!</small>}
                                
                            </div>
                           
                        </div>

                       

                        <div className="col-12 col-md-6">
                            <div className="mb-3">
                                <label htmlFor="surname" className="form-label">Surname</label>
                                <input type="text" id="surname" className="form-control w-100 registration-input" {...register("surname", {required: true})}/>
                                {errors.surname && <small className="text-danger">Surname is required!</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="password" className="form-label">Password</label>
                                <input type="password" id="password" className="form-control w-100 registration-input" {...register("password", {required: true})}/>
                                {errors.password && <small className="text-danger">Password is required!</small>}
                             </div>
                           
                            

                        </div>
                        <div className="mb-3">
              <label htmlFor="role" className="form-label">Role</label>
                <select
                  name="role"
                  className="form-control"
                  {...register("role", { required: true })}
                //   value={formData.role}
                //   onChange={handleChange}
                >
                <option value="">Choose role</option>
                <option value="ADMINISTRATOR">ADMINISTRATOR</option>
                <option value="OPERATOR">OPERATOR</option>
                <option value="MANAGER">MANAGER</option>
              </select>


              {errors.role && <small className="text-danger">Role is required</small>}
            </div>
                        <div className="mt-auto">
                                <button type="submit" className="button-text">Sign up</button>
                            </div>
                    </div>
                </form>
            </div>
        </>


    );



}

export default Registration;