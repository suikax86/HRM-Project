import { useState, useEffect } from "react";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../ProfileForm/ProfileForm.scss";

const EditProfileForm = () => {
  const [employee, setEmployee] = useState({
    address: "",
    phoneNumber: "",
    bankName: "",
    bankNumber: "",
  });

  useEffect(() => {
    const fetchEmployeeData = async () => {
      const userInfo = JSON.parse(localStorage.getItem("userInfo"));
      const employeeId = userInfo?.employeeId;
      if (employeeId) {
        try {
          const response = await axios.get(
            `http://localhost:8080/api/employees/${employeeId}`
          );
          setEmployee(response.data);
        } catch (error) {
          console.error("Error fetching employee data:", error);
        }
      }
    };

    fetchEmployeeData();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee((prevEmployee) => ({
      ...prevEmployee,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userInfo = JSON.parse(localStorage.getItem("userInfo"));
    const employeeId = userInfo?.employeeId;
    try {
      const response = await axios.put(
        `http://localhost:8080/api/employees/${employeeId}?withPoints=false`,
        employee
      );
      if (response.status === 200) {
        toast.success("Update profile successful!");
      }
    } catch (error) {
      console.error(
        "Error updating employee data:",
        error.response.data.message
      );
      toast.error("Error updating employee data:");
    }
  };

  return (
    <>
      <form className="profile-form" onSubmit={handleSubmit}>
        <h1>Sửa hồ sơ của tôi</h1>
        <div>
          <label>Mã nhân viên:</label>
          <input
            type="text"
            name="employeeId"
            placeholder="1"
            value={employee.id}
            disabled
          />
        </div>
        <div>
          <label>Tên nhân viên:</label>
          <input
            type="text"
            name="employeeName"
            placeholder="Nguyễn Văn A"
            value={employee.name}
            disabled
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            placeholder="example@gmail.com"
            value={employee.email}
            disabled
          />
        </div>
        <div>
          <label>Số điện thoại:</label>
          <input
            type="text"
            name="phoneNumber"
            placeholder="01234123123"
            value={employee.phoneNumber}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Địa chỉ:</label>
          <input
            type="text"
            name="address"
            value={employee.address}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Tên ngân hàng:</label>
          <input
            type="text"
            name="bankName"
            value={employee.bankName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Số ngân hàng:</label>
          <input
            type="text"
            name="bankNumber"
            value={employee.bankNumber}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Update Profile</button>
      </form>
      <ToastContainer position="bottom-right" />
    </>
  );
};

export default EditProfileForm;
