import backIcon from '../back.png';

const BackButton = ({ onClick, to }) => {
    const handleClick = () => {
        if (onClick) {
            onClick(); // Ako je prosleđena custom funkcija, pozovi je
        } else if (to) {
            window.location.href = to; // Ako je prosleđena putanja, preusmeri na nju
        } else {
            window.history.back(); // Inače, koristi default ponašanje (nazad)
        }
    };

    return (
        <button type="button" className="home-button-text" onClick={handleClick}>
            <img src={backIcon} alt="Back" width="20" height="20" />
        </button>
    );
};

export default BackButton;