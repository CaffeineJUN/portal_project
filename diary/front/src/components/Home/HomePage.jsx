import React from 'react'
import axios from 'axios'
import {useSelector} from 'react-redux'
import {useNavigate} from 'react-router-dom'

const HomePage = () => {
    const navigate = useNavigate()

    const hadleLogout = () => {
        axios.post('/api/logout').then(res => {
            console.log(res.data)
            navigate('/')
        })
    }

    const user = useSelector(state => state.user)
    console.log(user)

    return (
        <div>
            <h1>Home</h1>
            <div>
                <a href="/login">Login</a>
            </div>
            <div>
                <a href="/register">Register</a>
            </div>
            <div>
                <a href="/diary">Diary</a>
            </div>
            <button onClick={hadleLogout}>Logout</button>
        </div>
    )
}

export default HomePage
