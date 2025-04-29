import React from "react";
import ReactDOM from "react-dom";
import './MalfunctionModal.css';


const MalfunctionModal = ({ isOpen, onClose, onSave, formData, setFormData }) => {
    if(!isOpen) return null;

    return ReactDOM.createPortal(
        <div className="malfunction-modal-overlay">
            <div className="malfunction-modal-content">
                <h2>Malfunction details</h2>
                <label>
                    Description: 
                    <input 
                        type="text" 
                        value={formData.description} 
                        onChange={(e) => setFormData({...formData, description: e.target.value})} 
                    />
                </label>
                
                <label>
                    Date: 
                    <input 
                        type="datetime-local" 
                        value={formData.dateTime} 
                        onChange={(e) => setFormData({...formData, dateTime: e.target.value})} 
                    />
                </label>
                <div className="modal-actions">
                    <button onClick={onClose}>Cancel</button>
                    <button onClick={onSave}>Save</button>
                </div>
            </div>
        </div>,
        document.getElementById("modal-root") // Portal render u posebnom div-u
    );
};

export default MalfunctionModal;