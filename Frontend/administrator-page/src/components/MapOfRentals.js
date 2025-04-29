import React from "react";
import { APIProvider, Map, AdvancedMarker } from "@vis.gl/react-google-maps";

const center = { lat: 44.7866, lng: 20.4489 }; // Beograd

const MapOfRentals = () => {
  return (
    <APIProvider apiKey="AIzaSyAHjEEA_mPfyzJKdGoXIy2uRy7XhIOpDlI">
      <Map
        center={center}
        zoom={12}
        style={{ width: "100%", height: "100vh" }}
        mapId="TVOJ_MAP_ID" // Opcionalno ako koristiš prilagođene stilove
      >
        <AdvancedMarker position={center} />
      </Map>
    </APIProvider>
  );
};

export default MapOfRentals;
