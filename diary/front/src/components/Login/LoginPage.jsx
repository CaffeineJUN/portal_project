import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import {loginUser} from '../../_actions/userAction'
import {useNavigate} from 'react-router-dom'

import styles from './LoginPage.module.css'

const LoginPage = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [state, setState] = useState({
        userId: '',
        password: '',
    })

    const {userId, password} = state

    const handleInput = e => {
        let {name, value} = e.target
        setState({...state, [name]: value})
    }

    const handleSubmit = e => {
        e.preventDefault()
        // console.log(state)
        dispatch(loginUser(state)).then(res => {
            console.log(res)
            navigate('/')
        })
    }

    return (
        <div className={styles.container}>
            <h1>로그인</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="">ID</label>
                <input type="text" name="userId" value={userId} onChange={handleInput} />

                <label htmlFor="">PASSWORD</label>
                <input type="password" name="password" value={password} onChange={handleInput} />

                <button type="submit">Login</button>
            </form>
        </div>
    )
}

export default LoginPage
