import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/login/LoginPage';
import MainPage from './pages/mainPage/MainPage';
import Registration from './pages/registration/Registration';
import VehiclesManagement from './pages/vehicles-menagement/VehiclesManagement';
import CreateVehicle from './pages/createVehicle/CreateVehicle';
import DetailsAndMalfunctions from './pages/detailAndMalfunctions/DetailsAndMalfunctions';
import Rentals from './pages/rentals/Rentals';
import Manufacturers from './pages/manufacturer-page/ManufacturersPage';
import UserProfile from './pages/user-profile/UserProfile';
import UsersManagement from './pages/users-management/UsersManagement';
import OperatorMainPage from './pages/operatorMainPage/OperatorMainPage';
import OperatorRentals from './pages/operatorRentals/OperatorRentals';
import MapView from './pages/operatorMapView/MapView';
import OperatorClientsReview from './pages/operatorClientsReview/OperatorClientsReview';
import OperatorMalfunctionPage from './pages/operatorMalfunctionPage/OperatorMalfunctionPage';
import ManagerMainPage from './pages/managerMainPage/ManagerMainPage';
import StatisticsReview from './pages/managerStatisticsReview/StatisticsReview';
import ManagerPriceDefining from './pages/managerPriceDefining/ManagerPriceDefininig';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/mainPage" element={<MainPage />} />
        <Route path="/vehiclesManagement" element={<VehiclesManagement />}/>
        <Route path="/createVehicle" element={<CreateVehicle />}/>
        <Route path="/showDetails" element={<DetailsAndMalfunctions />} />;
        <Route path="/rentals" element={<Rentals />} />
        <Route path="/manufacturers" element={<Manufacturers />} />;
        <Route path="/userProfile" element={<UserProfile />} />;
        <Route path="/usersManagement" element={<UsersManagement />} />;

        <Route path="/operatorMainPage" element={<OperatorMainPage />}/>;
        <Route path="/operatorRentals" element={<OperatorRentals />} />;
        <Route path="/rentalsMap" element={<MapView />} />;
        <Route path="/clientsReview" element={<OperatorClientsReview />} />;
        <Route path="/malfunctions" element={<OperatorMalfunctionPage />} />;

        <Route path="/managerMainPage" element={<ManagerMainPage />} />;
        <Route path="/statistics" element={<StatisticsReview />} />;
        <Route path="/priceDefining" element={<ManagerPriceDefining />} />;

      </Routes>
    </Router>
  );
}

export default App;