import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Body from './Layout/Body';
import SessionStarted from './Layout/SessionStarted';
import SessionResults from './Layout/SessionResults';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/session-started" element={<SessionStarted />} />
                <Route path="/" element={<Body />} />
                <Route path="/session-results" element={<SessionResults />} />
            </Routes>
        </Router>
    );
}

export default App;
