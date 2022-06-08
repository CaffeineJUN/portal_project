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
            <h1>home</h1>
            <div>
                <a href="/login">login</a>
            </div>
            <div>
                <a href="/register">register</a>
            </div>
            <div>
                <a href="/diary">diary</a>
            </div>
            <button onClick={hadleLogout}>logout</button>
        </div>
    )
}

export default HomePage
