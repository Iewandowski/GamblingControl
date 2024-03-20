import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './layout.css';
import lose from '../img/lose.png';
import win from '../img/win.png';
import reward from '../img/bd.pdf';

const SessionResults = () => {
    const [listSorted, setListSorted] = useState([]);
    const [totalSorted, setTotalSorted] = useState(0);
    const [totalWinner, setTotalWinner] = useState(0);
    const [winnerList, setWinnerList] = useState([]);
    const [groupNumber, setGroupNumber] = useState([]);
    const [consultCpf, setConsultCpf] = useState('');
    const [userResult, setUserResult] = useState(null);
    const [userWin, setUserWin] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [listSortedRes, totalSortedRes, totalWinnerRes, winnersListRes, groupNumberRes] = await Promise.all([
                    axios.get('http://localhost:8080/api/prize/listSorted'),
                    axios.get('http://localhost:8080/api/prize/totalSorted'),
                    axios.get('http://localhost:8080/api/winner/totalWinner'),
                    axios.get('http://localhost:8080/api/winner/winnersList'),
                    axios.get('http://localhost:8080/api/ticket/groupNumber')
                ]);
                setListSorted(listSortedRes.data);
                setTotalSorted(totalSortedRes.data);
                setTotalWinner(totalWinnerRes.data);
                setWinnerList(winnersListRes.data);
                setGroupNumber(groupNumberRes.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();


        return () => {
            setListSorted([]);
            setTotalSorted(0);
            setTotalWinner(0);
            setWinnerList([]);
            setGroupNumber([]);
        };

    }, []);

    const handleConsultResult = () => {
        axios.get(`http://localhost:8080/api/winner/checkWin/${consultCpf}`)
            .then(response => {
                setUserResult(response.data);
                setUserWin(response.data);
            })
            .catch(error => {
                console.error('Error fetching user result:', error);
            });
    };

    const finishSession = () => {
        axios.post('http://localhost:8080/api/session/finish')
            .then(response => {
                console.log('Session finished successfully');
            })
            .catch(error => {
                console.error('Error finishing session:', error);
            });
    }

    return (
        <div className="container">
        <div className="content">
            <h1>Session Results:</h1>
            <div className="result-container">
                <h2>Sorted Numbers:</h2>
                <p>{listSorted.join(", ")}</p>
            </div>

            <div className="result-container">
                <h2>Total rounds in this Session:</h2>
                <p>{totalSorted}</p>
            </div>

            <div className="result-container">
            <h2>Quantity of bets wonned:</h2>
                {totalWinner ? (
                    <p>{totalWinner}</p>
                ) : (
                    <p>0</p>
                )}
            </div>

            <div className="result-container">
            <h2>Winners List:</h2>
            {winnerList.length === 0 ? (
                <p>There's no winners in this session</p>
            ) : (
                <ul>
                    {winnerList.map((result, index) => (
                        <li key={index}>
                            <p>Name: {result.name}</p>
                            <p>CPF: {result.cpf}</p>
                            <p>Numbers: {result.number1}, {result.number2}, {result.number3}, {result.number4}, {result.number5}</p>
                        </li>
                    ))}
                </ul>
            )}
            </div>

            <div className="result-container">
            <h2>All betted numbers list:</h2>
            <table>
                <thead>
                    <tr>
                        <th>Bet number</th>
                        <th>Bet Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {groupNumber.map((number, index) => (
                        <tr key={index}>
                            <td>{number[0]}</td>
                            <td>{number[1]}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
            <div className="result-container">
                <input className='input' type="text" value={consultCpf} onChange={(e) => setConsultCpf(e.target.value)} placeholder="Enter CPF" />
                <button className="btn start-btn" onClick={handleConsultResult}>Consult My Result</button>
            </div>
            {userResult !== null && (
                <div className="result-container">
                    {userResult !== null && (
                    <div className="result-container">
                        <h2>Your Result:</h2>
                        {userWin ? (
                            <a href={reward} download>
                            Click to get your reward!<br/>
                            <img src={win} alt="Win" />
                            </a>
                        ) : (
                            <img src={lose} alt="Lose" />
                        )}
                    </div>
                    )}
                </div>
            )}

            <div>
                <a href="/">
                    <button className="btn start-btn" onClick={finishSession}>Back to home page</button>
                </a>
            </div>
        </div>
    </div>
);
}

export default SessionResults;