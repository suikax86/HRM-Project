import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage/HomePage.jsx";
import Login from "./pages/Login/Login.jsx";
import MyProfile from "./pages/MyProfile/MyProfile.jsx";
import EditProfile from "./pages/EditProfile/EditProfile.jsx";
import CreateProfile from "./pages/CreateProfile/CreateProfile.jsx";
import DonXinNghi from "./pages/DonXinNghi/Don.jsx";
import DuyetDon from "./pages/DuyetDon/DuyetDon.jsx";
import VoucherPage from "./pages/VoucherPage/VoucherPage.jsx";
import HistoryApplication from "./pages/HistoryApplication/History.jsx";
import ApprovalHistoryPage from "./pages/ApprovalHistoryPage/ApprovalHistoryPage.jsx";
import CheckInCheckOutPage from "./pages/CheckInCheckOut/CheckInCheckOutPage.jsx";
import Page404 from "./pages/404/404.jsx";
import Page403 from "./pages/403/403.jsx";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route exac path="/HomePage" element={<HomePage />} />
        <Route exac path="/MyProfile" element={<MyProfile />} />
        <Route exac path="/EditProfile" element={<EditProfile />} />
        <Route exac path="/CreateProfile" element={<CreateProfile />} />
        <Route exac path="/DonXinNghi" element={<DonXinNghi />} />
        <Route exac path="/Voucher" element={<VoucherPage />} />
        <Route exac path="/History" element={<HistoryApplication />}/>
        <Route exac path="/DuyetDon" element={<DuyetDon />} />
        <Route exac path ="/ApprovalHistory" element={<ApprovalHistoryPage />} />
        <Route exac path="/CheckInCheckOut" element={<CheckInCheckOutPage />} />
        <Route exac path="/404" element={<Page404 />} />
        <Route exac path="/403" element={<Page403 />} />

      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
