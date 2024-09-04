import { useState,useEffect } from 'react'
import React from 'react'
import './CheckInCheckOut.scss'
import axios from 'axios'

function CheckInCheckOut() {
  const [checkin,setCheckin] = useState('')
  const [checkout,setCheckout] = useState('')
  const [checkinErr, setCheckinErr] = useState('')
  const [checkoutErr, setCheckoutErr] = useState('')
  const [history,setHistory] = useState('')
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const employeeId = userInfo.employeeId;
  const checkInButton = () => {
    axios.post(`http://localhost:8080/api/timesheets/check-in/${employeeId}`)
    .then(res => {
     console.log(res)
      setCheckin(res.data)
      console.log(checkin)
    })
    .catch(err => setCheckinErr(err.response.data.message))
  }
  const checkOutButton = () => {
    axios.post(`http://localhost:8080/api/timesheets/check-out/${employeeId}`)
    .then(res => {
     console.log(res)
      setCheckout(res.data)
      console.log(checkout)
    })
    .catch(err => setCheckoutErr(err.response.data.message))
  }

  useEffect(() => {
    axios.get(`http://localhost:8080/api/timesheets/history/${employeeId}`)
    .then(res => {
      console.log(res)
      setHistory(res.data[0])
    })
    .catch(err => console.log(err))
  })

  return (
    <div className='checkincheckout-container'>
       <div className='checkincheckout-button'>
          <button className='checkinout-button' onClick={() => checkInButton()}>Check In</button>
            <div> Thời gian check in : {checkin}</div>
            <div className='checkin-err'>{checkinErr}</div>
          <button className='checkinout-button' onClick={() => checkOutButton()}>Check Out</button>
          <div> Thời gian check out : {checkout}</div>
          <div className='checkin-err'>{checkoutErr}</div>
       </div>
       <div className='checkincheckout-history'>
        <div className='history-title'>Lịch sử</div>
        <div className='history-checkinout'>Bạn đã check in vào lúc : {!history ? 'Bạn chưa check in' : history.checkInTime} </div>
        <div className='history-checkinout'>Bạn đã check out vào lúc : {!history ? 'Bạn chưa check out' : history.checkOutTime}</div>
       </div>
    </div>
  )
}

export default CheckInCheckOut