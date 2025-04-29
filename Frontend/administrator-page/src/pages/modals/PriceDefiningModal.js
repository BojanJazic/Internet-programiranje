import React from "react";
import ReactDOM from 'react-dom';
import './PriceDefiningModal.css';


const PriceDefiningModal = ({ isOpen, onClose, onSave, formData, setFormData}) =>{

    if(!isOpen)
        return;

    return ReactDOM.createPortal(
       
        <div className="price-defining-modal-overlay">
            <div className="price-defining-modal-content">
                <h2>Add rental price</h2>
            
            
            <div className="row">
                <div className="mb-2">
                    <label>
                        Price:
                        <input
                            type="number"
                            value={formData.rentalPrice}
                            onChange={(e) => setFormData({...formData, rentalPrice: e.target.value})}
                        />
                    </label>

                    <button type="button" onClick={onSave}>Add</button>
                    <button type="button" onClick={onClose}>Cancel</button>
                </div>
            </div>
            </div>

            

        </div>,

        document.getElementById("price-defining-modal-root")
    );


}


export default PriceDefiningModal;