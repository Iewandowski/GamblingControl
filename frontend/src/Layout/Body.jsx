import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './layout.css';
import logo from '../img/logo.png';

const Body = () => {

    const startSession = async () => {
        axios.post('http://localhost:8080/api/session/start')
        .then(response => {
            console.log('Session started successfully');
            window.location.href = '/session-started';
        })
        .catch(error => {
            console.error('Error starting session:', error);
        });
    }

    return (
    <div className='container'>
        <div className="content">
            <img src={logo} alt="GC Logo" className="logo" />
            <h1>Gambling Control</h1><br/><br/>
            <button className="btn" onClick={startSession}>Start Session</button>
        </div>
    </div>
    )
}
export default Body;
