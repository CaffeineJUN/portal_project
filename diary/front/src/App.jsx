import './App.css'
import {Routes, Route} from 'react-router-dom'

import LoginPage from './components/Login/LoginPage'
import HomePage from './components/Home/HomePage'
import RegisterPage from './components/Register/RegisterPage'
import DiaryPage from './components/Diary/DiaryPage'
import UpdatePage from './components/UserUpdate/UpdatePage'

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/diary" element={<DiaryPage />} />
                <Route path="/updateUser" element={<UpdatePage />} />
            </Routes>
        </div>
    )
}

export default App
