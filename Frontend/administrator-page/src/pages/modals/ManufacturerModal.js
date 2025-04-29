import React from "react";
import ReactDOM from "react-dom";
import './ManufacturerModal.css';

const ManufacturerModal = ({ isOpen, onClose, onSave, formData, setFormData, modalMode }) => {
    
    if(!isOpen) return null;
    
    return ReactDOM.createPortal(
        <div className="manufacturer-modal-overlay">
            <div className="manufacturer-modal-content">
                <h2>{modalMode === "add" ? "Add new manufacturer" : "Update manufacturer"}</h2>

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
                                State:
                                <input
                                    type="text"
                                    value={formData.state}
                                    onChange={(e) => setFormData({...formData, state: e.target.value})}
                                />
                            </label>
                        </div>

                        <div className="mb-2">
                            <label>
                                Address:
                                <input
                                    type="text"
                                    value={formData.address}
                                    onChange={(e) => setFormData({...formData, address: e.target.value})}
                                />
                            </label>
                        </div>
                    </div>

                    <div className="col-12 col-md-6">
                        <div className="mb-2">
                            <label>
                                Phone:
                                <input
                                    type="text"
                                    value={formData.phone}
                                    onChange={(e) => setFormData({...formData, phone: e.target.value})}
                                />
                            </label>
                        </div>

                        <div className="mb-2">
                            <label>
                                Fax:
                                <input
                                    type="text"
                                    value={formData.fax}
                                    onChange={(e) => setFormData({...formData, fax: e.target.value})}
                                />
                            </label>
                        </div>

                        <div className="mb-2">
                            <label>
                                Email:
                                <input
                                    type="text"
                                    value={formData.email}
                                    onChange={(e) => setFormData({...formData, email: e.target.value})}
                                />
                            </label>
                        </div>
                    </div>
                </div>
                
                

                
                
                <div className="manufacturer-modal-actions">
                    <button onClick={onClose}>Cancel</button>
                    <button onClick={onSave}>{modalMode === "add" ? "Save" : "Update"}</button>
                </div>

            </div>
        </div>,
        document.getElementById("manufacturer-modal-root")
    );
};

export default ManufacturerModal;