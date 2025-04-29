import React from "react";
import ReactDOM from "react-dom";
import './EmployeeModal.css';

const EmployeeModal = ({ isOpen, onClose, onSave, formData, setFormData, modalMode }) => {
    if(!isOpen) return null;
        
        return ReactDOM.createPortal(
            <div className="manufacturer-modal-overlay">
                <div className="manufacturer-modal-content">
                    <h2>{modalMode === "add" ? "Add new employee" : "Update employee"}</h2>
    
                    <div className="row">
                        <div className="col-12 col-md-6">
                            <div className="mb-2">
                                <label>
                                    Name:
                                    <input
                                        type="text"
                                        value={formData.name}
                                        onChange={(e) => setFormData({...formData, name: e.target.value})}
                                    />
                                </label>
                            </div>
    
                            <div className="mb-2">
                                <label>
                                    Surname:
                                    <input
                                        type="text"
                                        value={formData.surname}
                                        onChange={(e) => setFormData({...formData, surname: e.target.value})}
                                    />
                                </label>
                            </div>

                            <div className="mb-2">
                                <label>
                                    Role:
                                    <input
                                        type="text"
                                        value={formData.role}
                                        onChange={(e) => setFormData({...formData, role: e.target.value})}
                                    />
                                </label>
                            </div>
    
                            
                        </div>
    
                        <div className="col-12 col-md-6">
                        <div className="mb-2">
                                <label>
                                    Username:
                                    <input
                                        type="text"
                                        value={formData.username}
                                        onChange={(e) => setFormData({...formData, username: e.target.value})}
                                    />
                                </label>
                            </div>
    
                            <div className="mb-2">
                                {modalMode === "add" && (
                                <label>
                                    Password:
                                    <input
                                        type="password"
                                        value={formData.password}
                                        onChange={(e) => setFormData({...formData, password: e.target.value})}
                                    />
                                </label>
                                )}
                            </div>
    
                            
                        </div>
                    </div>
                    
                    
    
                    
                    
                    <div className="manufacturer-modal-actions">
                        <button onClick={onClose}>Cancel</button>
                        <button onClick={onSave}>{modalMode === "add" ? "Save" : "Update"}</button>
                    </div>
    
                </div>
            </div>,
            document.getElementById("employee-modal-root")
        );

};

export default EmployeeModal;