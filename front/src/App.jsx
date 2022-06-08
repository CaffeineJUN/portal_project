import './App.css'
import {Routes, Route} from 'react-router-dom'

import LoginPage from './components/Login/LoginPage'
import HomePage from './components/Home/HomePage'
import RegisterPage from './components/Register/RegisterPage'

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
            </Routes>
        </div>
    )
}

export default App
