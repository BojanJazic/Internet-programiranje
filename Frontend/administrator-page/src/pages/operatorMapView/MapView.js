
import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";

// Popravka za prikaz ikonica markera
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import FetchData from "../../components/FetchData";

let DefaultIcon = L.icon({
  iconUrl: icon,
  shadowUrl: iconShadow,
  iconSize: [25, 41], // Velicina ikonice
  iconAnchor: [12, 41], // Tačka za pričvršćivanje markera
});

L.Marker.prototype.options.icon = DefaultIcon;

const MapOfRentals = () => {
  const center = [44.770971, 17.194208]; // Banja Luka
  const [activeLocation, setActiveLocation] = useState(null);
  const [rentals, setRentals] = useState([]);
  const [groupedRentals, setGroupedRentals] = useState({});

  useEffect(() => {
    const fetchRentals = async () => {
      try {
        const response = await FetchData({ endpoint: "/rentals" });
        if (response && Array.isArray(response)) {
          // Transformišite podatke
          const transformedRentals = response.map((rental) => ({
            ...rental,
            dropOffLocation: [rental.dropOffLocation.coordinateX, rental.dropOffLocation.coordinateY],
          }));
          setRentals(transformedRentals);

          // Grupišite vozila po lokaciji
          const grouped = transformedRentals.reduce((acc, rental) => {
            const locationKey = rental.dropOffLocation.join(","); // Pretvaramo niz u string za ključ
            if (!acc[locationKey]) {
              acc[locationKey] = [];
            }
            acc[locationKey].push(rental);
            return acc;
          }, {});
          setGroupedRentals(grouped);
        } else {
          console.error("Odgovor nije niz:", response);
        }
      } catch (error) {
        console.error("Greška pri učitavanju podataka:", error);
      }
    };

    fetchRentals();
  }, []);

  return (
    <div style={{ height: "100vh", width: "100vw" }}>
      <MapContainer center={center} zoom={12} style={{ height: "100vh", width: "100%" }}>
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        />

        {Object.entries(groupedRentals).map(([locationKey, rentalsAtLocation]) => {
          const location = locationKey.split(",").map(Number); // Pretvaramo string nazad u niz [lat, lng]
          return (
            <Marker
              key={locationKey}
              position={location}
              eventHandlers={{
                click: () => {
                  setActiveLocation(locationKey);
                },
              }}
            >
              {activeLocation === locationKey && (
                <Popup>
                  <div style={{ maxHeight: "200px", overflowY: "auto" }}>
                    <h3>Vozila na lokaciji:</h3>
                    <ul>
                      {rentalsAtLocation.map((rental) => (
                        <li key={rental.rentalId}>
                          <strong>{rental.manufacturer} {rental.model}</strong> ({rental.idVehicle})<br />
                          Iznajmio: {rental.name} {rental.surname}<br />
                          Cijena: {rental.price} KM
                        </li>
                      ))}
                    </ul>
                  </div>
                </Popup>
              )}
            </Marker>
          );
        })}
      </MapContainer>
    </div>
  );
};

export default MapOfRentals;
