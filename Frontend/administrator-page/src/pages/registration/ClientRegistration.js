import { useForm } from "react-hook-form";
import { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './Registration.css'
import axios, { HttpStatusCode } from "axios";

//OVO MOZDA CAK I NE TREBA, JER KORISNICI IMAJU JSP APLIKACIJU

function Registration() {
    const { register, handleSubmit, formState: { errors } } = useForm();

    const onSubmit = async (data) => {
        try{
           await axios.post("/administrators/registration", data);

        }catch(error){
            console.log("An error occured during registration " + error);
        }

    };




    return (

        <>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
        </head>
        <body>
            <div className="container d-flex justify-content-center align-items vh-100">
                <div className="background registration-background">
                    <div className="shape"></div>
                    <div className="shape"></div>
                </div>
                <form className="p-4 rounded shadow registration-form" onSubmit={handleSubmit(onSubmit)}>
                    <h3 className="text-center">Registration</h3>

                    <div className="row">
                        {/* Prva kolona */}
                        <div className="col-md-4">
                            <div className="mb-3">
                                 <label htmlFor="name" className="form-label">Name</label>
                                 <input type="text" id="name" className="form-control w-auto registration-input"
                                  {...register("name", {required: true})}
                                 
                                  />
                                  {errors.name && <small className="text-danger">Name is required!</small>}
                                
                            </div>
                            <div className="mb-3">
                                <label htmlFor="surnname" className="form-label">Surname</label>
                                <input type="text" id="surname"  className="form-control w-auto registration-input"  {...register("surname", {required: true})}
                                />
                                {errors.surname && <small className="text-danger">Surname is required!</small>}
                                
                            </div>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">Email</label>
                                <input type="text" id="email" className="form-control w-auto registration-input" {...register("email", {required: true})}/>
                                {errors.email && <small className="text-danger">Email is required!</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="phone" className="form-label">Phone</label>
                                <input type="text" id="phone" className="form-control w-auto registration-input" {...register("phone", {required: true})}/>
                                {errors.phone && <small className="text-danger">Phone is required!</small>}
                             </div>
                        </div>

                        <div className="col-md-4">
                            <div className="mb-3">
                                <label htmlFor="avatar" className="form-label">Avatar</label>
                                <input type="text" id="avatar" className="form-control w-auto registration-input" {...register("avatar", {required: true})}/>
                                {errors.avatar && <small className="text-danger">Avatar is required!</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="idCard" className="form-label">Identity card</label>
                                <input type="text" id="idCard" className="form-control w-auto registration-input" {...register("idCard", {required: true})}/>
                                {errors.idCard && <small className="text-danger">Identity card is required!</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="passport" className="form-label">Passport</label>
                                <input type="text" id="passport" className="form-control w-auto registration-input" {...register("passport", {required: false})}/>
                                {errors.driverLicense && <small className="text-danger">""</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="driverLicence" className="form-label">Driver license</label>
                                <input type="text" id="driverLicense" className="form-control w-auto registration-input" {...register("driverLicense", {required: false})}/>
                                {errors.driverLicense && <small className="text-danger">""</small>}
                             </div>
                        </div>

                        <div className="col-md-4">
                            <div className="mb-3">
                                <label htmlFor="username" className="form-label">Username</label>
                                <input type="text" id="username" className="form-control w-auto registration-input" {...register("username", {required: true})}/>
                                {errors.username && <small className="text-danger">Username is required!</small>}
                             </div>
                             <div className="mb-3">
                                <label htmlFor="password" className="form-label">Password</label>
                                <input type="text" id="password" className="form-control w-auto registration-input" {...register("password", {required: true})}/>
                                {errors.password && <small className="text-danger">Password is required!</small>}
                             </div>
                             <div className="d-flex flex-column" style={{ height: "39%", width: "78%" }}>
                                <div className="mt-auto">
                                    <button type="submit" className="btn btn-outline-light w-100 button-text">Sign up</button>
                                 </div>
                            </div>

                        </div>

                       
                    </div>
                </form>


            </div>
        </body>
        
        
        </>


    );



}

export default Registration;