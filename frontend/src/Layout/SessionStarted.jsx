import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './layout.css';

const SessionStarted = () => {
    const [showPopup, setShowPopup] = useState(false);
    const [showBetListPopup, setShowBetListPopup] = useState(false);
    const [betList, setBetList] = useState([]);
    const [resultsAvailable, setResultsAvailable] = useState(false);

    const [formData, setFormData] = useState({
        name: '',
        cpf: '',
        number1: '',
        number2: '',
        number3: '',
        number4: '',
        number5: ''
    });

    useEffect(() => {
        axios.get('http://localhost:8080/api/prize/listSorted')
            .then(response => {
                if (response.data.length > 0) {
                    setResultsAvailable(true);
                }
            })
            .catch(error => {
                console.error('Error fetching sorted list:', error);
            });
    }, []);

    const openPopup = () => {
        setShowPopup(true);
    };

    const closePopup = () => {
        setShowPopup(false);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleMakeBet = () => {
        if (resultsAvailable) {
            alert("Results have already been announced. You can't make new bets.");
        } else {
            openPopup();
        }
    };

    const handlePopupSubmit = () => {
        const { name, cpf, number1, number2, number3, number4, number5 } = formData;
        if (name && cpf && number1 && number2 && number3 && number4 && number5 && number1 > 0 && number1 < 51
            && number2 > 0 && number2 < 51 && number3 > 0 && number4 < 51 && number4 > 0 && number4 < 51
            && number5 > 0 && number5 < 51) {
        axios.post('http://localhost:8080/api/ticket/create', formData)
            .then(response => {
                console.log('Good luck!');
                closePopup();
            })
            .catch(error => {
                console.error('Error submitting bet:', error);
            });
        } else {
            alert('All fields must be filled and the numbers must have values between 1 and 50!');
        }
    };

    const handleSurprise = () => {
        const { name, cpf } = formData;
        if (name && cpf) {
            axios.post('http://localhost:8080/api/ticket/random', formData)
            .then(response => {
                console.log('Good luck!');
                closePopup();
            })
            .catch(error => {
                console.error('Error calling surprise endpoint:', error);
            });
        } else {
            alert('Fields name and cpf must be filled!');
        }
        
    };

    const sessionResults = async () => {
        axios.post('http://localhost:8080/api/session/apuration')
        .then(response => {
            console.log('successfully apuration');
            window.location.href = '/session-results';
        })
        .catch(error => {
            console.error('Error starting session:', error);
        });
    }

    const riggedDice = async () => {
        axios.post('http://localhost:8080/api/session/riggedDice')
        .then(response => {
            console.log('successfully apuration');
            window.location.href = '/session-results';
        })
        .catch(error => {
            console.error('Error starting session:', error);
        });
    }

    const openSessionListPopup = () => {
        axios.get('http://localhost:8080/api/ticket/listPerSession')
            .then(response => {
                setBetList(response.data);
                setShowBetListPopup(true);
            })
            .catch(error => {
                console.error('Error fetching bet results:', error);
            });
    };
    
    return (
        <div className='container'>
            <div className="content">
            <h1>what you want to do?</h1><br/><br/>
            <button onClick={handleMakeBet} className="btn">Make a Bet</button><br/><br/>
            <button onClick={openSessionListPopup} className="btn">Bet List</button><br/><br/>
            <button onClick={sessionResults} className="btn">End Session</button><br/><br/>
            {/*<button onClick={riggedDice} className="btn">Rigged Dice</button>*/}
            </div>

            {showPopup && (
                <div className="popup">
                    <form>
                        <label>
                            CPF:
                            <input type="text" name="cpf" value={formData.cpf} onChange={handleChange} />
                        </label>
                        <br/>
                        <label>
                            Name:
                            <input type="text" name="name" value={formData.name} onChange={handleChange} />
                        </label>    
                        <br/>
                        <label>
                            Number 1:
                            <input type="number" name="number1" value={formData.number1} onChange={handleChange} />
                        </label>
                        <br/>
                        <label>
                            Number 2:
                            <input type="number" name="number2" value={formData.number2} onChange={handleChange} />
                        </label>
                        <br/>
                        <label>
                            Number 3:
                            <input type="number" name="number3" value={formData.number3} onChange={handleChange} />
                        </label>
                        <br/>
                        <label>
                            Number 4:
                            <input type="number" name="number4" value={formData.number4} onChange={handleChange} />
                        </label>
                        <br/>
                        <label>
                            Number 5:
                            <input type="number" name="number5" value={formData.number5} onChange={handleChange} />
                        </label>
                        <br/>
                        <button type="button" onClick={handlePopupSubmit}>OK</button>
                        <button type="button" onClick={handleSurprise}>Surprise</button>
                        <button type="button" onClick={closePopup}>Cancel</button>
                    </form>
                </div>
            )}

            {showBetListPopup && (
                <div className="popup">
                    <h2>Bet List</h2>
                    <ul>
                        {betList.map((result, index) => (
                            <li key={index}>
                                <p>Name: {result.name}</p>
                                <p>CPF: {result.cpf}</p>
                                <p>Numbers: {result.number1}, {result.number2}, {result.number3}, {result.number4}, {result.number5}</p>
                            </li>
                        ))}
                    </ul>
                    <button onClick={() => setShowBetListPopup(false)}>Close</button>
                </div>
            )}
        </div>
    );
}

export default SessionStarted;